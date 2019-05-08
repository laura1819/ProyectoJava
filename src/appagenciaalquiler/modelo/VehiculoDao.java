package appagenciaalquiler.modelo;

import java.util.List;

public interface VehiculoDao {
    public List<Vehiculo> listar() throws DaoException;
    public int insertar(List<Vehiculo> vehiculos) throws DaoException;
}
