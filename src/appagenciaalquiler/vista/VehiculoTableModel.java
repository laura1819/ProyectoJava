
package appagenciaalquiler.vista;

import appagenciaalquiler.modelo.Grupo;
import appagenciaalquiler.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VehiculoTableModel extends AbstractTableModel {

    private List<Vehiculo> lista;
    private String[] columnas;

    public VehiculoTableModel() {
        lista = new ArrayList<>();
        columnas = new String[]{"Matrícula", "Nombre", "Grupo", "Plazas", "Precio Alquiler"};
    }

    public List<Vehiculo> getLista() {
        return lista;
    }

    public void setLista(List<Vehiculo> lista) {
        this.lista = lista;
        this.fireTableDataChanged();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o = null;
        Vehiculo v=lista.get(rowIndex);
        switch(columnIndex){
            case 0: o=v.getMatricula();
                    break;
            case 1: o=v.getNombre();
                    break;
            case 2: o=v.getGrupo();
                    break;
            case 3: o=v.getPlazas();
                    break;
            case 4: o=v.getPrecioAlquiler();
                    break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase=null;
        switch(columnIndex){
            case 0: clase=String.class;
                    break;
            case 1: clase=String.class;
                    break;
            case 2: clase=Grupo.class;
                    break;
            case 3: clase=Integer.class;
                    break;
            case 4: clase=Float.class;
                    break;
        }
        return clase;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
