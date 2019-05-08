package appagenciaalquiler.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vehiculo implements Comparable<Vehiculo>, Serializable {

    private String matricula;
    private String nombre;
    private Grupo grupo;
    private int plazas;

    public Vehiculo(String matricula, String nombre, Grupo grupo, int plazas) throws MatriculaException {
        String patron = "([0-9]{4})([B-Z&&[^EIOU]]{3})";
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(matricula);
        if (!m.matches()) {
            throw new MatriculaException("Formato de Matrícula Incorrrecto");
        }
        this.matricula = matricula;
        this.nombre = nombre;
        this.grupo = grupo;
        if (plazas > 0) {
            this.plazas = plazas;
        } else {
            throw new IllegalArgumentException("Número de plazas incorrecto");
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) throws MatriculaException {
        boolean valido = false;
        String patron = "([0-9]{4})([B-Z&&[^EIOU]]{3})";
        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(matricula);
        if (m.matches()) {
            valido = true;
        }
        if (valido) {
            this.matricula = matricula;
        } else {
            throw new MatriculaException("Formato de Matrícula Incorrrecto");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        if (plazas > 0) {
            this.plazas = plazas;
        } else {
            throw new IllegalArgumentException("Número de plazas incorrecto");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Matrícula: ").append(this.getMatricula()).append("\n");
        sb.append("Nombre: ").append(this.getNombre()).append("\n");
        sb.append("Grupo: ").append(this.getGrupo()).append("\n");
        sb.append("Plazas: ").append(this.getPlazas()).append("\n");

        return sb.toString();
    }

    @Override
    public int compareTo(Vehiculo v) {
        return this.matricula.compareTo(v.matricula);
    }

    public float getPrecioAlquiler() {
        return getGrupo().getValor();
    }

}
