package appagenciaalquiler.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class VehiculoDaoCsv implements VehiculoDao {

    public Path path;

    public VehiculoDaoCsv(String path) {
        this.path = Paths.get(path);
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    
    @Override
    public List<Vehiculo> listar() throws DaoException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String linea;
        String[] l;
        Vehiculo v = null;
        try {
            BufferedReader fichero = Files.newBufferedReader(path);
            linea = fichero.readLine();
            while (linea != null) {
                l = linea.split(",");
                v = new Vehiculo(l[0], l[1], Grupo.valueOf(l[2]), Integer.parseInt(l[3]));
                vehiculos.add(v);
                linea = fichero.readLine();
            }
        } catch (IOException ioe) {
            throw new DaoException(ioe.toString());
        } catch (MatriculaException me) {
            throw new DaoException("Error en matrícula");
        }
        return vehiculos;
    }
 
    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        BufferedWriter fichero = null;
        int n = 0;
        ListIterator<Vehiculo> li = null;
        li = vehiculos.listIterator();
        try {
            fichero = Files.newBufferedWriter(path);
            while (li.hasNext()) {
                fichero.write(li.next().toString());
                fichero.newLine();
                n++;
            }
        } catch (IOException ex) {
            throw new DaoException(ex.toString());
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException ex) {
                throw new DaoException(ex.toString());
            }
        }
        return vehiculos.size();
    }
}
