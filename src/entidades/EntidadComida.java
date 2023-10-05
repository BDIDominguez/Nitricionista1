package entidades;

/**
  * @author louis
 */
public class EntidadComida {
private int idComida;
private String nombre;
private String receta;
private int calorias; 
private boolean estado;
private double peso;

    public EntidadComida(int idComida, String nombre, String receta, int calorias, boolean estado, double peso) {
        this.idComida = idComida;
        this.nombre = nombre;
        this.receta = receta;
        this.calorias = calorias;
        this.estado=estado;
        this.peso = peso;
    }

    public EntidadComida(String nombre, String receta, int calorias, boolean estado, double peso) {
         this.nombre = nombre;
        this.receta = receta;
        this.calorias = calorias;
        this.estado=estado;
        this.peso = peso; 
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
        return nombre;
    }

    public void setNombreComida(String nombre) {
        this.nombre = nombre;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "EntidadComida{" + "idComida=" + idComida + ", nombre=" + nombre + ", receta=" + receta + ", calorias=" + calorias + ", estado=" + estado + ", peso=" + peso + '}';
    }

      
    }
 
 


    
