package entidades;

/**
 * * @author DIEGO G.
 */
public class EntidadDieta_Comida {

    private int idDieta_Comida;     // Declaración de atributo para el ID de la relación Dieta-Comida
    private int idDieta;            // Declaración de atributo para el ID de la dieta relacionada
    private int idComida;           // Declaración de atributo para el ID de la comida relacionada
    private HorarioComida horario;  // Declaración de atributo para el horario en que se asigna la comida
    private int porcion;         // Añadido para representar la cantidad en gramos
    private String nombreComida;

    public EntidadDieta_Comida() {
    }
    
    public enum HorarioComida {     // Enumerado para representar los horarios
        Desayuno, Almuerzo, Merienda, Cena, Snack
    }

    // Creo el constructor que recibe todos los atributos de la relación
    public EntidadDieta_Comida(int idDieta_Comida, int idDieta, int idComida, int porcion, HorarioComida horario) {
        this.idDieta_Comida = idDieta_Comida;
        this.idDieta = idDieta;
        this.idComida = idComida;
        this.porcion = porcion;
        this.horario = horario;
    }

    public EntidadDieta_Comida(int idDieta, int idComida, int porcion, HorarioComida horario) {
        this.idDieta = idDieta;
        this.idComida = idComida;
        this.horario = horario;
    }

    public int getIdDieta_Comida() {
        return idDieta_Comida;
    }

    public void setIdDieta_Comida(int idDieta_Comida) {
        this.idDieta_Comida = idDieta_Comida;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public HorarioComida getHorario() {
        return horario;
    }

    public void setHorario(HorarioComida horario) {
        this.horario = horario;
    }

    public int getPorcion() {
        return porcion;
    }

    public void setPorcion(int porcion) {
        this.porcion = porcion;
    }
    
   
    @Override
    public String toString() {
        return "EntidadDieta_Comida{"
                + "idDieta_Comida=" + idDieta_Comida
                + ", idDieta=" + idDieta
                + ", idComida=" + idComida
                + ", porcion=" + porcion
                + ", horario=" + horario + '\''
                + '}';
    }
}