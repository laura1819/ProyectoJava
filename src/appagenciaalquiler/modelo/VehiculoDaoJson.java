package appagenciaalquiler.modelo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class VehiculoDaoJson implements VehiculoDao {
    public Path path;

    public VehiculoDaoJson(String path) {
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
        List<Vehiculo> lista=null;
        Type tipo = new TypeToken<List<Vehiculo>>(){}.getType();
        GsonBuilder builder=new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try(BufferedReader br=Files.newBufferedReader(path)){
            lista=gson.fromJson(br, tipo);
        }catch (IOException ex){
            throw new DaoException("Error");
        }
        return lista;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        Type tipo = new TypeToken<List<Vehiculo>>(){}.getType();
        GsonBuilder builder=new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        try(BufferedWriter bw=Files.newBufferedWriter(path)){
            gson.toJson(vehiculos,tipo,bw);
        }catch (IOException ex){
            throw new DaoException("Error");
        }
        return vehiculos.size();
    }
}
