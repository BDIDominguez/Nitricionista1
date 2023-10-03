package datas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadComida;
//import ulp.nutricionista.entidades.EntidadDietaComida;      por crear clases
import javax.swing.JOptionPane;

/**
 *EN PRUEBAS 
 * @author louis
 * notas:
 * decidir si se usan sout para el main 
 * ver cantidad de calorías por gramo
 * cambiar nombre por nombrecomida
 * cambiar tipo int por string horario se asigna, esto es (enum) {“Desayuno”, “Almuerzo”, ”Merienda”, ”Cena”, “Snack”}
 */
public class DataComida {

    private Connection con;

    public DataComida() {
    }

    public void agregarComidas(EntidadComida comida) {
        con = Conexion.getConexion();
        String sql = "INSERT INTO comidas (nombreComida, receta, calorias, tipo) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comida.getNombreComida());
            ps.setString(2, comida.getReceta());
            ps.setInt(2, comida.getTipo());
            ps.setInt(3, comida.getCalorias());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) { // 
                comida.setIdComida(rs.getInt("idComida"));
                JOptionPane.showMessageDialog(null, "Comida agregada correctamente");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar agegar una comida" + ex.getMessage());
        }

    }

    public void eliminarComida(int idComida, int iddietacomida) {
        //ver si hay resctriccion para borrar sino eliminar iddietacomida. ver logica table llena con box
        con = Conexion.getConexion();
        try {
            String sql = "DELETE FROM comidas WHERE idComida = ?  AND iddietacomida = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idComida);
           // ps.setInt(1, idDietacomida); por crear clases
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se elimino la comida");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al intentar eliminar la comida");
        }
    }
        public void modificarPorcionComida(int idcomida, int iddietacomida, int porcion) {
        con = Conexion.getConexion();
        //no existe porcion en gramos hay que crearlo en la bd etc
        //METODO modificar las porciones de comida,  a una dieta en particular.
        //DECIDIR SI ES UNA VISTA NV
        String sql = "UPDATE comidas SET porcion = ? WHERE idComida = ? AND idDietacomida = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, idcomida);
            ps.setInt(2, iddietacomida);
            ps.setInt(3, porcion);
            ps.executeUpdate();
            int fila = ps.executeUpdate();
            if (fila == 1) {
            JOptionPane.showMessageDialog(null, "Porcion de comida actualizada");
            } else {
            JOptionPane.showMessageDialog(null, "La comida no existe");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la comida " + ex.getMessage());
        }
    }
        
        public List<EntidadComida> obtenerComidas() {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT * FROM comidas";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
              EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombreComida"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setTipo(resultSet.getInt("tipo"));
                // comida.setPorcion(resultSet.getInt("porcion"));

                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas " + ex.getMessage());
        }
        return comidas;  
    }
            
        //➢	Se necesita saber las comidas incluidas en una dieta específica 
        public List<EntidadComida> obtenerComidasxDieta() {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT comidas.idComida, nombreComida, receta, calorias, tipo, porcion FROM comidas JOIN dietacomidas ON comidas.idComida = dietacomidas.idComida WHERE dietacomidas.idDietacomidas = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
              EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombreComida"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setTipo(resultSet.getInt("tipo"));
                // comida.setPorcion(resultSet.getInt("porcion"));

                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas de la dieta elegida  " + ex.getMessage());
        } ////////pensar como la elige en la vista
        return comidas;  
    }
        
       //➢➢	Consultar la búsqueda de comidas que tengan una cantidad menor de un determinado número de calorías.
        public List<EntidadComida> obtenerComidasxCalorias(int calorias) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT comidas.idComida, nombreComida, receta, tipo, porcion FROM comidas WHERE calorias < ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, calorias);
             ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
              EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombreComida"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setTipo(resultSet.getInt("tipo"));
                // comida.setPorcion(resultSet.getInt("porcion"));
                           
                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas con menor calorias a la cantidad elegida" + ex.getMessage());
        } ////////pensar como la buscar por caloria en la vista
        return comidas;  
    }
          
                // metodo auxiliar para cerrar tanto resultSet como prepare statement
    private void cerrarRecursos(PreparedStatement ps, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (ps != null) {
            ps.close();
        }
    }
        
    }
    
    
    

