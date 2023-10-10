/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.EntidadDieta;
import entidades.EntidadPaciente;

/**
 *
 * @author Matias
 */
public class DataDieta {

    private Connection con;

    public DataDieta() {
    }

    public EntidadDieta crearDieta(EntidadDieta entidadDieta) throws SQLException {
        con = Conexion.getConexion();
        String sql = "INSERT INTO dietas (id_dieta, nombre, id_paciente, fecha_inicial, peso_inicial, peso_final, fecha_final) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, entidadDieta.getIdDieta());
        ps.setString(2, entidadDieta.getNombre());
        ps.setInt(3, entidadDieta.getPaciente().getIdpaciente());
        ps.setDate(4, java.sql.Date.valueOf(entidadDieta.getFechaInicial()));
        ps.setDouble(5, entidadDieta.getPesoInicial());
        ps.setDouble(6, entidadDieta.getPesoFinal());
        ps.setDate(7, java.sql.Date.valueOf(entidadDieta.getFechaFinal()));
        ps.executeUpdate();
        ps.close();
        return entidadDieta;
    }

    public boolean modificarDieta(EntidadDieta entidadDieta) throws SQLException {
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "UPDATE dietas SET nombre = ?, id_paciente = ?, fecha_inicial = ?, peso_inicial = ?, peso_final = ?, fecha_final = ? WHERE id_dieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entidadDieta.getNombre());
        ps.setInt(2, entidadDieta.getPaciente().getIdpaciente());
        ps.setDate(3, java.sql.Date.valueOf(entidadDieta.getFechaInicial()));
        ps.setDouble(4, entidadDieta.getPesoInicial());
        ps.setDouble(5, entidadDieta.getPesoFinal());
        ps.setDate(6, java.sql.Date.valueOf(entidadDieta.getFechaFinal()));
        ps.setInt(7, entidadDieta.getIdDieta());
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }

    public boolean eliminarDieta(int id) throws SQLException {
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "UPDATE dietas SET estado = 0 WHERE id_dieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }

    public List<EntidadDieta> listarDietas() throws SQLException {
        List<EntidadDieta> dietas = new ArrayList<>();
        con = Conexion.getConexion();
        String sql = "SELECT * FROM dietas ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            EntidadDieta d = new EntidadDieta();
            DataPaciente p = new DataPaciente();
            d.setIdDieta(rs.getInt("iddieta"));
            d.setNombre(rs.getString("nombre"));
            d.setPaciente(p.pacienteID(rs.getInt("idpaciente"))); // Obtener el paciente por su ID
            d.setFechaInicial(rs.getDate("fecinicio").toLocalDate());
            d.setFechaFinal(rs.getDate("fecfinal").toLocalDate());
            d.setPesoInicial(rs.getDouble("pesoinicial"));
            d.setPesoFinal(rs.getDouble("pesofinal"));
            dietas.add(d);
        }
        ps.close();
        return dietas;
    }

    public EntidadDieta obtenerDietaPorId(int id) throws SQLException {
        EntidadDieta d = new EntidadDieta();
        DataPaciente p = new DataPaciente();
        con = Conexion.getConexion();
        String sql = "SELECT * FROM dietas WHERE id_dieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            d.setIdDieta(rs.getInt("id_dieta"));
            d.setNombre(rs.getString("nombre"));
            d.setPaciente(p.pacienteID(rs.getInt("idpaciente"))); 
            d.setFechaInicial(rs.getDate("fecinicio").toLocalDate());
            d.setFechaFinal(rs.getDate("fecfinal").toLocalDate());
            d.setPesoInicial(rs.getDouble("pesoinicial"));
            d.setPesoFinal(rs.getDouble("pesofinal"));
        }
        ps.close();
        return d;
    }
}
