package appagenciaalquiler;

import appagenciaalquiler.controlador.Controlador;
import appagenciaalquiler.modelo.AgenciaAlquiler;
import appagenciaalquiler.vista.Ventana;

public class AppAgenciaAlquiler {
    public static void main(String[] args) {
        Ventana ventana=new Ventana();
        AgenciaAlquiler agenciaAlquiler=new AgenciaAlquiler("Agencia");
        Controlador controlador=new Controlador(ventana, agenciaAlquiler);
        ventana.setControlador(controlador);
        ventana.setVisible(true);
    }
    
}
