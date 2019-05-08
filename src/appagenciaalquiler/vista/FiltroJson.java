package appagenciaalquiler.vista;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FiltroJson extends FileFilter {

    @Override
    public boolean accept(File f) {
        String n = f.getName();
        boolean b = false;
        if (f.isDirectory() || n.endsWith(".json")) {
            b = true;
        }
        return b;
    }

    @Override
    public String getDescription() {
        return "Documento json (.json)";
    }
}
