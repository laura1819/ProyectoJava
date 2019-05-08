package appagenciaalquiler.modelo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class VehiculoDaoXml implements VehiculoDao {
    public Path path;

    public VehiculoDaoXml(String path) {
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
        XStream xstream=new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypeHierarchy(Vehiculo.class);
        lista=(List<Vehiculo>)xstream.fromXML(path.toFile());
        return lista;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        XStream xstream=new XStream(new DomDriver());
        try(BufferedWriter bw=Files.newBufferedWriter(path)){
            bw.write(xstream.toXML(vehiculos));
        }catch (IOException ex){
            throw new DaoException("Error");
        }
        return vehiculos.size();
    }
}
