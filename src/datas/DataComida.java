package datas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadComida;
import javax.swing.JOptionPane;

/**
 *
 * @author louis
 */
public class DataComida {

    private Connection con = null;

    public DataComida() {
    }

    //Agregar nuevas comidas:
    //INSERT INTO comidas (nombre, receta, calorias, estado, peso) VALUES ('Ensalada de Atún y Garbanzos', 'Atún enlatado, garbanzos, tomate, cebolla roja, aceite de oliva', 320, 1, 280);
    public EntidadComida agregarComidas(EntidadComida comida) {
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
            comida.setIdComida(rs.getInt(1));
          }
        cerrarRecursos(ps, rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al intentar agregar una nueva comida" + ex.getMessage());
        }
        return comida;
    }
  
    //Deshabilitar una comida:
    // UPDATE comidas SET estado = 0 WHERE idComida = 8;
    public void deshabilitarComida(int idComida) {
        con = Conexion.getConexion();
        try {
            String sql = "UPDATE comidas SET estado = 0 WHERE idcomida = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idComida);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se deshabilito correctamente la comida");
            }
            ps.close();
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") || e.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, " Error al intentar deshabilitar la comida, pertenece a una dieta");
            }
        }
    }
    
     //Habilitar una comida:
    // UPDATE comidas SET estado = 1 WHERE idComida = 6;
    public void habilitarComida(int idComida) {
        con = Conexion.getConexion();
        try {
            String sql = "UPDATE comidas SET estado = 1 WHERE idcomida = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idComida);
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, " Se habilito correctamente la comida");
            }
            ps.close();
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(null, " Error al intentar habilitar la comida");
            }
        }
    

    //Listar todas las comidas habilitadas
    // SELECT * FROM comidas WHERE estado = 1;
    public List<EntidadComida> obtenerComidasHabilitadas(boolean estado) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidashab = new ArrayList<>();
        String sql = "SELECT * FROM comidas WHERE estado = 1";
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

                comidashab.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas habilitadas " + ex.getMessage());
        }
        return comidashab;
    }
    
    //Listar todas las comidas 
    // SELECT * FROM comidas;
    public List<EntidadComida> obtenerTodaslasComidas() {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> todascomidas = new ArrayList<>();
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

                todascomidas.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas" + ex.getMessage());
        }
        return todascomidas;
    }
    
     //Listar todas las comidas no habilitadas
    // SELECT * FROM comidas WHERE estado = 0;
    public List<EntidadComida> obtenerComidasNoHabilitadas(boolean estado) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasnohab = new ArrayList<>();
        String sql = "SELECT * FROM comidas WHERE estado = 0";
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

                comidasnohab.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas no habilitadas " + ex.getMessage());
        }
        return comidasnohab;
    }
//    
     //➢➢	Consultar la búsqueda de comidas por nombre
//SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE nombre LIKE '%ensalada%';
    public List<EntidadComida> obtenerComidasxNombre(String nombre) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasxnombre = new ArrayList<>();
        String sql = "SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE nombre LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, "%" + nombre + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidasxnombre.add(comida);
                 System.out.println(comidasxnombre);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas con el nombre deseado" + ex.getMessage());
        }   
        return comidasxnombre;
       
    }
        
    //➢➢	Consultar la búsqueda de comidas por id 
 //SELECT nombre, receta, calorias, estado, peso FROM comidas WHERE idcomida = 2;
    public List<EntidadComida> obtenerComidasxidComida(int idComida) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasxid = new ArrayList<>();
        String sql = "SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE idcomida = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idComida);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidasxid.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas por su id" + ex.getMessage());
        }  
        return comidasxid;
    }
    
     //➢➢	Consultar la búsqueda de comidas por peso 
 //SELECT nombre, receta, calorias, estado, peso FROM comidas WHERE peso = 280;
    public List<EntidadComida> obtenerComidasxpeso(double peso) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasxpeso = new ArrayList<>();
        String sql = "SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE peso = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, peso);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidasxpeso.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas por su peso" + ex.getMessage());
        }  
        return comidasxpeso;
    }
    
     //➢➢	Consultar la búsqueda de comidas por estado bien que esten deshabilitadas o no
     public List<EntidadComida> obtenerComidasxEstado(boolean estado) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasestado = new ArrayList<>();
        String sql = "SELECT * FROM comidas WHERE estado = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidasestado.add(comida);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas por su estado" + ex.getMessage());
        }  
        return comidasestado;
    }
    
    //modificar comidas
    public void modificarComidas(EntidadComida comida) {
        con = Conexion.getConexion();
        String sql = "UPDATE comidas SET nombre = ?, receta = ?, calorias = ?, estado = ?, peso = ? WHERE idcomida = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        ps.setString(1, comida.getNombreComida());
        ps.setString(2, comida.getReceta());
        ps.setInt(3, comida.getCalorias());
        ps.setBoolean(4, comida.isEstado());
        ps.setDouble(5, comida.getPeso());
        ps.setInt(6, comida.getIdComida());
            int fila = ps.executeUpdate();
            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente la comida");
            } else if (fila == 0) {
                JOptionPane.showMessageDialog(null, "La comida no existe en la base de datos");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la comida " + ex.getMessage());
        }
    }

//➢➢	Consultar la búsqueda de comidas que tengan una cantidad menor de un determinado número de calorías
    //SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE calorias < 400;
    public List<EntidadComida> obtenerComidasxCalorias(int calorias) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidas = new ArrayList<>();
        String sql = "SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE calorias < ?";
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
        } 
        return comidas;
    }
 //➢➢	Consultar la búsqueda de comidas por receta
//SELECT idcomida, receta, calorias, estado, peso FROM comidas WHERE receta LIKE '%aguacate%';
    public List<EntidadComida> obtenerComidasxReceta(String receta) {
        con = Conexion.getConexion();
        ArrayList<EntidadComida> comidasxreceta = new ArrayList<>();
        String sql = "SELECT idcomida, nombre, receta, calorias, estado, peso FROM comidas WHERE receta LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + receta + "%");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                EntidadComida comida = new EntidadComida();
                comida.setIdComida(resultSet.getInt("idcomida"));
                comida.setNombreComida(resultSet.getString("nombre"));
                comida.setReceta(resultSet.getString("receta"));
                comida.setCalorias(resultSet.getInt("calorias"));
                comida.setEstado(resultSet.getBoolean("estado"));
                comida.setPeso(resultSet.getDouble("peso"));

                comidasxreceta.add(comida);
                 System.out.println(comidasxreceta);
            }
            cerrarRecursos(ps, resultSet);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recuperar el listado de comidas con la receta deseada" + ex.getMessage());
        }   
        return comidasxreceta;
       
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
