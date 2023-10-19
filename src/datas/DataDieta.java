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
        String sql = "INSERT INTO dietas (nombre, idpaciente, fecinicio, pesoinicial, pesofinal, fecfinal, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entidadDieta.getNombre());
        ps.setInt(2, entidadDieta.getPaciente());
        ps.setDate(3, java.sql.Date.valueOf(entidadDieta.getFechaInicial()));
        ps.setDouble(4, entidadDieta.getPesoInicial());
        ps.setDouble(5, entidadDieta.getPesoFinal());
        ps.setDate(6, java.sql.Date.valueOf(entidadDieta.getFechaFinal()));
        ps.setBoolean(7, entidadDieta.isEstado());
        ps.executeUpdate();
        ps.close();
        return entidadDieta;
    }

    public boolean modificarDieta(EntidadDieta entidadDieta) throws SQLException {
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "UPDATE dietas SET nombre = ?,fecinicio = ?, pesoinicial = ?, pesofinal = ?, fecfinal = ?, estado = ? WHERE iddieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, entidadDieta.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(entidadDieta.getFechaInicial()));
        ps.setDouble(3, entidadDieta.getPesoInicial());
        ps.setDouble(4, entidadDieta.getPesoFinal());
        ps.setDate(5, java.sql.Date.valueOf(entidadDieta.getFechaFinal()));
        ps.setBoolean(6, entidadDieta.isEstado());
        ps.setInt(7, entidadDieta.getIdDieta());
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }

    public boolean eliminarDieta(int id) throws SQLException {
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "UPDATE dietas SET estado = 0 WHERE iddieta = ?";
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
            d.setIdDieta(rs.getInt("iddieta"));
            d.setNombre(rs.getString("nombre"));
            d.setPaciente((rs.getInt("idpaciente"))); // Obtener el paciente por su ID
            d.setFechaInicial(rs.getDate("fecinicio").toLocalDate());
            d.setFechaFinal(rs.getDate("fecfinal").toLocalDate());
            d.setPesoInicial(rs.getDouble("pesoinicial"));
            d.setPesoFinal(rs.getDouble("pesofinal"));
            d.setEstado(rs.getBoolean("estado"));
            dietas.add(d);
        }
        ps.close();
        return dietas;
    }

    public EntidadDieta obtenerDietaPorId(int id) throws SQLException {
        EntidadDieta d = new EntidadDieta();
        con = Conexion.getConexion();
        String sql = "SELECT * FROM dietas WHERE iddieta = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            d.setIdDieta(rs.getInt("iddieta"));
            d.setNombre(rs.getString("nombre"));
            d.setPaciente((rs.getInt("idpaciente"))); 
            d.setFechaInicial(rs.getDate("fecinicio").toLocalDate());
            d.setFechaFinal(rs.getDate("fecfinal").toLocalDate());
            d.setPesoInicial(rs.getDouble("pesoinicial"));
            d.setPesoFinal(rs.getDouble("pesofinal"));
            d.setEstado(rs.getBoolean("estado"));
        }
        ps.close();
        return d;
    }
    
    public boolean definirDietaUnica (int idpaciente, int iddieta) throws SQLException {
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "UPDATE dietas set estado = 0 where idpaciente = ? and iddieta != ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idpaciente);
        ps.setInt(2, iddieta);
        ps.executeUpdate();    
        vRespuesta = true;
        return vRespuesta;
    }
}
