package datas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadComida;
import entidades.EntidadDieta_Comida;
import javax.swing.JOptionPane;

/**
 * EN PRUEBAS
 *
 * @author louis
 */
public class DataComida {

    private Connection con;

    public DataComida() {
    }

    public void agregarComidas(EntidadComida comida) {
        con = Conexion.getConexion();
        String sql = "INSERT INTO comidas (nombre, receta, calorias, estado, peso) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, comida.getNombreComida());
            ps.setString(2, comida.getReceta());
            ps.setInt(3, comida.getCalorias());
            ps.setBoolean(4, comida.isEstado());
            ps.setDouble(5, comida.getPeso());
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

    public void eliminarComida(int nombre) {
        con = Conexion.getConexion();
        try {
            String sql = "UPDATE comidas SET estado = 0 WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nombre);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se elimino la comida");
            }
            ps.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") || e.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, " Error al intentar eliminar la comida existe una dieta");
            }
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
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));
          
                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas " + ex.getMessage());
        }
        return comidas;
    }

    //modificar las porciones de comida, a una dieta en particular
    public void modificarComidas(String nombre, String receta, int calorias, boolean estado, double peso) {
        con = Conexion.getConexion();
        String sql = "UPDATE comidas SET nombre = ?, receta = ?, calorias = ?, estado = ?, peso = ? WHERE idComida = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, receta);
            ps.setInt(3, calorias);
            ps.setBoolean(3, estado);
            ps.setDouble(3, peso);
            ps.executeUpdate();
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Porcion de comida actualizada");
            } else {
                JOptionPane.showMessageDialog(null, "La comida no esta disponible");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la comida " + ex.getMessage());
        }
    }
    
     //➢➢	Consultar la búsqueda de comidas que tengan una cantidad menor de un determinado número de calorías.
    public List<EntidadComida> obtenerComidasxCalorias(int calorias) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT comidas.idComida, nombre, receta, tipo, porcion FROM comidas WHERE calorias < ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, calorias);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas con menor cantidad de calorias a la indicada" + ex.getMessage());
        } ////////pensar como la buscar por caloria en la vista
        return comidas;
    }
    
    //modificar las porciones de comida, a una dieta en particular
    public void modificarPorcionComida(String nombre, int iddietacomida, int porcion) {
        con = Conexion.getConexion();
        String sql = "UPDATE comidas SET porcion = ? WHERE nombre = ? AND idDietacomidas  = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, iddietacomida);
            ps.setInt(3, porcion);
            ps.executeUpdate();
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Porcion de comida actualizada");
            } else {
                JOptionPane.showMessageDialog(null, "La comida no esta disponible");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la comida " + ex.getMessage());
        }
    }
    
    //➢ Se necesita saber las comidas incluidas en una dieta específica 
    public List<EntidadComida> obtenerComidasxDieta() {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT comidas.idComida, nombre, receta, calorias, tipo, porcion FROM comidas JOIN dietacomidas ON comidas.idComida = dietacomidas.idComida WHERE dietacomidas.idDietacomidas = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas de la dieta elegida  " + ex.getMessage());
        } ////////pensar como la elige en la vista
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
