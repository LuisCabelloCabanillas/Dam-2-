import java.io.Serializable;

public class Pokemon implements Serializable {
    private int numeroPokedex;
    private String nombre;
    private TipoElemental tipo;
    private Ataque ataque;

    public Pokemon(int numeroPokedex, String nombre, TipoElemental tipo, Ataque ataque) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
    }
    public int getNumeroPokedex() {
        return numeroPokedex;
    }
    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public TipoElemental getTipo() {
        return tipo;
    }
    public void setTipo(TipoElemental tipo) {
        this.tipo = tipo;
    }
    public Ataque getAtaque() {
        return ataque;
    }
    public void setAtaque(Ataque ataque) {
        this.ataque = ataque;
    }
}
