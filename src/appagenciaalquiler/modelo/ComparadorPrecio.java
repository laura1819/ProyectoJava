package appagenciaalquiler.modelo;

import java.util.Comparator;

public class ComparadorPrecio implements Comparator<Vehiculo> {
    @Override
    public int compare(Vehiculo o1, Vehiculo o2) {
        int x;
        float p1, p2;
        p1=o1.getPrecioAlquiler();
        p2=o2.getPrecioAlquiler();
        if(p1<p2){
            x=-1;
        }else{
            if(p1>p2){
                x=1;
            }else{
                x=0;
            }
        }
        return x;
    }
}
