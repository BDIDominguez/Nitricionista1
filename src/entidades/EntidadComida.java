package entidades;

/**
  * @author louis
 */
public class EntidadComida {
private int idComida;
private String nombreComida;
private String receta;
private int calorias; 
private int tipo;

    public EntidadComida(int idComida, String nombreComida, String receta, int calorias, int tipo) {
        this.idComida = idComida;
        this.nombreComida = nombreComida;
        this.receta = receta;
        this.calorias = calorias;
        this.tipo = tipo;
    }

    public EntidadComida(String nombreComida, String receta, int calorias, int tipo) {
        this.nombreComida = nombreComida;
        this.receta = receta;
        this.calorias = calorias;
        this.tipo = tipo;
    }

    public EntidadComida() {
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
 
    @Override
    public String toString() {
        return "EntidadComida{" + "idComida=" + idComida + ", nombreComida=" + nombreComida + ", receta=" + receta + ", calorias=" + calorias + '}';
    }

    }
