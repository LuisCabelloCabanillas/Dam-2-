import java.io.Serializable;

public class Ataque implements Serializable {
    private String ataque;
    private int damage;

    public Ataque(String ataque, int damage) {
        this.ataque = ataque;
        this.damage = damage;
    }
    public String getAtaque() {
        return ataque;
    }
    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
