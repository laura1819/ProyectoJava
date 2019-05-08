package appagenciaalquiler.modelo;

public enum Grupo {
    A(40), B(60), C(75);
    private final int valor;

    private Grupo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
