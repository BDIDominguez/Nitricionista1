package datas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadDieta_Comida;

/**
 * * @author DIEGO G.
 */
public class DataDieta_Comida {

    private Connection con;

    public DataDieta_Comida() {
    }

    public void GuardarDietaComida(EntidadDieta_Comida dietaComida) throws SQLException {
        con = Conexion.getConexion();
        String sql = "INSERT INTO dietacomidas (iddieta, idcomida, porcion, horario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dietaComida.getIdDieta());
            ps.setInt(2, dietaComida.getIdComida());
            ps.setInt(3, dietaComida.getPorcion());
            ps.setString(4, dietaComida.getHorario().toString());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarDietaDeTabla(int idDietaComida) throws SQLException {

        boolean eliminacionOK = false;
        con = Conexion.getConexion();
        String sql = "DELETE FROM dietacomidas WHERE iddietacomida = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idDietaComida);
            ps.executeUpdate();
            ps.close();

//            if (filasAfectadas > 0 =) {
//                boolean eliminacionOK = true;
//            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return eliminacionOK;
    }

    public List<EntidadDieta_Comida> obtenerDietasComidaPorDieta(int idDieta) throws SQLException {
        con = Conexion.getConexion();
        List<EntidadDieta_Comida> dietasComida = new ArrayList<>();
        String sql = "SELECT * FROM dietacomidas WHERE iddieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idDieta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idDietaComida = rs.getInt("iddietacomida");
            int idComida = rs.getInt("idcomida");
            int porcion = rs.getInt("porcion");
            String horarioStr = rs.getString("horario");
            EntidadDieta_Comida.HorarioComida horario = EntidadDieta_Comida.HorarioComida.valueOf(horarioStr);

            EntidadDieta_Comida dietaComida = new EntidadDieta_Comida(idDietaComida, idDieta, idComida, porcion, horario);
            dietasComida.add(dietaComida);
        }

        rs.close();
        ps.close();

        return dietasComida;
    }
}
