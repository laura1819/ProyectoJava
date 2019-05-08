package appagenciaalquiler.modelo;

import java.util.ArrayList;
import java.util.List;

public class AgenciaAlquiler {
    private String nombre;
    private List<Vehiculo> flota;
    private VehiculoDao vehiculoDao;

    public AgenciaAlquiler(String nombre) {
        this.nombre = nombre;
        flota=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vehiculo> getFlota() {
        return flota;
    }

    public void setFlota(List<Vehiculo> flota) {
        this.flota = flota;
    }

    public VehiculoDao getVehiculoDao() {
        return vehiculoDao;
    }

    public void setVehiculoDao(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }
    
    public boolean incluirVehiculo(Vehiculo v) {
        boolean incluido=false;
        if(!flota.contains(v)){
            flota.add(v);
            incluido=true;
        }
        return incluido;
    }
    
    public boolean eliminarVehiculo(String matricula) {
        int posicion=-1;
        for(int i=0;i<flota.size() && posicion==-1;i++){
            if(flota.get(i).getMatricula().equals(matricula)){
                posicion=i;
            }
        }
        if(posicion==-1){
            return false;
        }
        flota.remove(posicion);
        return true;
    }
        
    public Vehiculo getVehiculo(String matricula){
        Vehiculo v = null;
        for(int i=0;i<flota.size() && v==null ;i++){
            if(matricula.equals(flota.get(i).getMatricula())){
                v=flota.get(i);
            }
        }
        return v;
    }
    
    public int guardarVehiculos() throws DaoException{
        int n=0;
        if(vehiculoDao!=null){
            n=vehiculoDao.insertar(flota);
        }
        return n;
    }

    public int cargarVehiculos() throws DaoException{
        int n=0;
        if(vehiculoDao!=null){
            flota=vehiculoDao.listar();
            n=flota.size();
        }
        return n;
    }
}
