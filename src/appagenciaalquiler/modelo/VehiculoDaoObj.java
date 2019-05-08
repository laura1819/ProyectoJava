package appagenciaalquiler.modelo;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDaoObj implements VehiculoDao {

    public Path path;

    public VehiculoDaoObj(String path) {
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
        ObjectInputStream fichero = null;
        List<Vehiculo> lista = new ArrayList<>();
        Vehiculo vehiculo = null;
        try {
            fichero = new ObjectInputStream(Files.newInputStream(path));
            while (true) {
                vehiculo = (Vehiculo) fichero.readObject();
                lista.add(vehiculo);
            }
        } catch (EOFException eofe) {
        } catch (IOException ioe) {
            System.out.println(ioe.toString());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                fichero.close();
            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            }
        }
        return lista;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        ObjectOutputStream fichero = null;
        try {
            fichero = new ObjectOutputStream(Files.newOutputStream(path));
            for (Vehiculo vehiculo : vehiculos) {
                fichero.writeObject(vehiculo);
            }
        } catch (IOException ex) {
            throw new DaoException(ex.toString());
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return vehiculos.size();
    }
}
