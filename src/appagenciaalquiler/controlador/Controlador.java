package appagenciaalquiler.controlador;

import appagenciaalquiler.modelo.AgenciaAlquiler;
import appagenciaalquiler.modelo.ComparadorPrecio;
import appagenciaalquiler.modelo.DaoException;
import appagenciaalquiler.modelo.Grupo;
import static appagenciaalquiler.modelo.Grupo.A;
import static appagenciaalquiler.modelo.Grupo.B;
import static appagenciaalquiler.modelo.Grupo.C;
import appagenciaalquiler.modelo.MatriculaException;
import appagenciaalquiler.modelo.Vehiculo;
import appagenciaalquiler.modelo.VehiculoDao;
import appagenciaalquiler.modelo.VehiculoDaoCsv;
import appagenciaalquiler.modelo.VehiculoDaoJson;
import appagenciaalquiler.modelo.VehiculoDaoObj;
import appagenciaalquiler.modelo.VehiculoDaoXml;
import appagenciaalquiler.vista.VehiculoTableModel;
import appagenciaalquiler.vista.Ventana;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Controlador {

    private Ventana ventana;
    private AgenciaAlquiler agenciaAlquiler;
    private VehiculoTableModel vehiculoTableModel;

    public Controlador(Ventana ventana, AgenciaAlquiler agenciaAlquiler) {
        this.ventana = ventana;
        this.agenciaAlquiler = agenciaAlquiler;
        vehiculoTableModel = new VehiculoTableModel();
        this.ventana.setTableModel(vehiculoTableModel);
    }

    public void listarVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        List<Vehiculo> flota = agenciaAlquiler.getFlota();
        String filtro = ventana.getFiltroGrupo();
        Grupo grupo;
        if (!filtro.equals("TODOS")) {
            grupo = Grupo.valueOf(filtro);
            for (Vehiculo v : flota) {
                if (v.getGrupo().equals(grupo)) {
                    lista.add(v);
                }
            }
        } else {
            lista = flota;
        }
        if (ventana.getOrden() == "Matricula") {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, new ComparadorPrecio());
        }
        vehiculoTableModel.setLista(lista);
    }

    public void crearVehiculo() {
        String matricula, nombre;
        String grupo;
        int plazas;
        Vehiculo v;
        matricula = ventana.getMatricula();
        nombre = ventana.getNombre();
        grupo = ventana.getGrupo();
        plazas = ventana.getPlazas();
        try {
            v = new Vehiculo(matricula, nombre, Grupo.valueOf(grupo), plazas);
            if (agenciaAlquiler.incluirVehiculo(v)) {
                ventana.mostrarMensaje("Vehículo añadido");
            } else {
                ventana.mostrarMensaje("No se pudo añadir el vehículo");
            }
        } catch (MatriculaException ex) {
            ventana.mostrarMensaje("Formato de matrícula incorrecto");
            ventana.limpiarCampos();
        }
    }

    public void buscarVehiculo() {
        try {
            String matricula = ventana.getMatricula();
            Vehiculo v = agenciaAlquiler.getVehiculo(matricula);
            ventana.mostrarMatricula(v.getMatricula());
            ventana.mostrarNombre(v.getNombre());
            ventana.mostrarGrupo(v.getGrupo().toString());
            ventana.mostrarPlazas(v.getPlazas());
            ventana.mostrarPrecioAlquiler(v.getPrecioAlquiler());
        } catch (NullPointerException npe) {
            ventana.limpiarCampos();
            ventana.mostrarMensaje("Vehículo no encontrado");
        }
    }

    public void borrarVehiculo() {
        String matricula = ventana.getMatricula();
        Vehiculo v = agenciaAlquiler.getVehiculo(matricula);
        ventana.mostrarMatricula(v.getMatricula());
        ventana.mostrarNombre(v.getNombre());
        ventana.mostrarGrupo(v.getGrupo().toString());
        ventana.mostrarPlazas(v.getPlazas());
        ventana.mostrarPrecioAlquiler(v.getPrecioAlquiler());
        if (ventana.solicitarConfirmacion()) {
            if (agenciaAlquiler.eliminarVehiculo(matricula)) {
                ventana.mostrarMensaje("Vehículo eliminado con éxito");
                ventana.limpiarCampos();
                listarVehiculos();
            } else {
                ventana.mostrarMensaje("No se pudo eliminar el vehículo");
            }
        }
    }

    public void modificarVehiculo() {
        String matricula = ventana.getMatricula();
        String grupo = null;
        int plazas = 0;
        Vehiculo v = agenciaAlquiler.getVehiculo(matricula);
        try {
            String nombre = JOptionPane.showInputDialog("Introduce nombre del vehículo");
            grupo = JOptionPane.showInputDialog("Introduce grupo del vehículo");
            try {
                plazas = Integer.parseInt(JOptionPane.showInputDialog("Introduce plazas del vehículo"));
                if (nombre.trim().length() != 0) {
                    v.setNombre(nombre);
                }
                v.setGrupo(Grupo.valueOf(grupo));
                v.setPlazas(plazas);
                ventana.mostrarGrupo(grupo);
                ventana.mostrarPlazas(plazas);
                ventana.mostrarNombre(nombre);
                ventana.mostrarPrecioAlquiler(v.getPrecioAlquiler());
                listarVehiculos();
                ventana.mostrarMensaje("Datos cambiados correctamente");
            } catch (NumberFormatException nfe) {
            } catch (IllegalArgumentException iae) {
                ventana.mostrarMensaje("Número de plazas incorrecto");
            }
        } catch (IllegalArgumentException iae) {
            ventana.mostrarMensaje("El grupo introducido es incorrecto");
        }
    }

    public void cargarVehiculos() {
        VehiculoDao vehiculoDao = null;
        String nombreArchivo;
        nombreArchivo = ventana.getNombreArchivo();
        String formato = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
        switch (formato) {
            case "obj":
                vehiculoDao = new VehiculoDaoObj(nombreArchivo);
                break;
            case "csv":
                vehiculoDao = new VehiculoDaoCsv(nombreArchivo);
                break;
            case "json":
                vehiculoDao = new VehiculoDaoJson(nombreArchivo);
                break;
            case "xml":
                vehiculoDao = new VehiculoDaoXml(nombreArchivo);
                break;
            default:
                ventana.mostrarMensaje("Extensión incorrecta");
        }
        agenciaAlquiler.setVehiculoDao(vehiculoDao);
        try {
            agenciaAlquiler.cargarVehiculos();
            listarVehiculos();
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.getMessage());
        }
    }

    public void guardarVehiculos() {
        VehiculoDao vehiculoDao = null;
        String nombreArchivo = ventana.getNombreArchivo();
        String formato = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);
        switch (formato) {
            case "obj":
                vehiculoDao = new VehiculoDaoObj(nombreArchivo);
                break;
            case "csv":
                vehiculoDao = new VehiculoDaoCsv(nombreArchivo);
                break;
            case "json":
                vehiculoDao = new VehiculoDaoJson(nombreArchivo);
                break;
            case "xml":
                vehiculoDao = new VehiculoDaoXml(nombreArchivo);
                break;
            default:
                ventana.mostrarMensaje("Extensión incorrecta");
        }
        agenciaAlquiler.setVehiculoDao(vehiculoDao);
        try {
            int n = agenciaAlquiler.guardarVehiculos();
            ventana.mostrarMensaje("Se han guardado " + n + " cuentas");
        } catch (DaoException ex) {
            ventana.mostrarMensaje(ex.toString());
        }
    }
}
