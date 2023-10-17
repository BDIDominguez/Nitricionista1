package datas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadDieta_Comida;

/**
 * @author DIEGO G.
 */
public class DataDieta_Comida {

    private Connection con;

    public DataDieta_Comida() {
    }

    public void GuardarDietaComida(EntidadDieta_Comida dietaComida) throws SQLException {
        String sql = "INSERT INTO dieta_comida (id_dieta, id_comida, porcion, horario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dietaComida.getIdDieta());
            ps.setInt(2, dietaComida.getIdComida());
            ps.setInt(3, (int) dietaComida.getPorcion());
            ps.setString(4, dietaComida.getHorario().toString());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean eliminarDietaComida(int idDietaComida) throws SQLException {
        boolean vResp = false;
        String sql = "DELETE FROM dieta_comida WHERE id_dieta_comida = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idDietaComida);
        ps.executeUpdate();
        ps.close();
        vResp=true;
        ps.close();
        return vResp;
    }

    //Obtener todas las comidas de una dieta
    public EntidadDieta_Comida obtenerDietaComidaPorId(int idDietaComida) throws SQLException {
        String sql = "SELECT * FROM dieta_comida WHERE id_dieta_comida = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idDietaComida);
        ResultSet rs = ps.executeQuery();

        EntidadDieta_Comida dietaComida = null;

        if (rs.next()) {
            int idDieta = rs.getInt("id_dieta");
            int idComida = rs.getInt("id_comida");
            int porcion = rs.getInt("porcion");
            String horarioStr = rs.getString("horario");
            EntidadDieta_Comida.HorarioComida horario = EntidadDieta_Comida.HorarioComida.valueOf(horarioStr);

            dietaComida = new EntidadDieta_Comida(idDietaComida, idDieta, idComida, porcion, horario);
        }

        rs.close();
        ps.close();

        return dietaComida;
    }

    
    public List<EntidadDieta_Comida> obtenerDietasComidaPorDieta(int idDieta) throws SQLException {
        List<EntidadDieta_Comida> dietasComida = new ArrayList<>();
        String sql = "SELECT * FROM dieta_comida WHERE id_dieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idDieta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int idDietaComida = rs.getInt("id_dieta_comida");
            int idComida = rs.getInt("id_comida");
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
