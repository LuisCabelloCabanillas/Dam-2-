import java.io.Serializable;

public class TipoElemental implements Serializable {
    private String elemento;
    private String debilidades;

    public TipoElemental(String elemento, String debilidades) {
        this.elemento = elemento;
        this.debilidades = debilidades;
    }
    public String getElemento() {
        return elemento;
    }
    public String getDebilidades() {
        return debilidades;
    }
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
    public void setDebilidades(String debilidades) {
        this.debilidades = debilidades;
    }
}
