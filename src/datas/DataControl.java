/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import entidades.EntidadControl;
import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Dario
 */
public class DataControl {
    public EntidadControl crearControl(EntidadControl co) throws SQLException{
        Connection con = Conexion.getConexion();
        String sql = "insert into controles (idPaciente,fecha,peso,altura,cintura,gasenergetico,IMC,proximacita,estado,obs) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,co.getIdPaciente());
        ps.setDate(2,Date.valueOf(co.getFecha()));
        ps.setDouble(3, co.getPeso());
        ps.setDouble(4,co.getAltura());
        ps.setDouble(5, co.getCintura());
        ps.setDouble(6,co.getGasenergetico());
        ps.setDouble(7, co.getIMC());
        ps.setDate(8,Date.valueOf(co.getProximacita()));
        ps.setBoolean(9, co.isEstado());
        ps.setString(10, co.getObs());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();  // Regresa los ID generados por la insercion anterior
        if (rs.next()) { // 
            co.setIdControl(rs.getInt(1));
        }
        ps.close();
        return co;
    }
    public boolean modificarControl(EntidadControl co) throws SQLException{
        boolean vRespuesta = false;
        Connection con = Conexion.getConexion();
        String sql = "update controles set idPaciente = ?, fecha= ?, peso= ?, altura = ?, cintura= ?, gasenergetico= ?, IMC= ?, proximacita= ?, estado=?, obs = ? where idControl = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,co.getIdPaciente());
        ps.setDate(2,Date.valueOf(co.getFecha()));
        ps.setDouble(3, co.getPeso());
        ps.setDouble(4,co.getAltura());
        ps.setDouble(5, co.getCintura());
        ps.setDouble(6,co.getGasenergetico());
        ps.setDouble(7, co.getIMC());
        ps.setDate(8,Date.valueOf(co.getProximacita()));
        ps.setBoolean(9, co.isEstado());
        ps.setString(10, co.getObs());
        ps.setInt(11,co.getIdControl());
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }
    
    
    public boolean eliminarControl(int id) throws SQLException{
        boolean vRespuesta = false;
        Connection con = Conexion.getConexion();
        String sql = "update controles set estado = 0 where idControl = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }
    
    public List<EntidadControl> listarControles() throws SQLException{
        List<EntidadControl> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String sql = "select * from controles where estado = 1 order by fecha";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            EntidadControl a = new EntidadControl();
            a.setIdControl(rs.getInt("idControl"));
            a.setIdPaciente(rs.getInt("idPaciente"));
            a.setFecha(rs.getDate("fecha").toLocalDate());
            a.setPeso(rs.getDouble("peso"));
            a.setAltura(rs.getDouble("altura"));
            a.setCintura(rs.getDouble("cintura"));
            a.setGasenergetico(rs.getDouble("gasenergetico"));
            a.setIMC(rs.getDouble("IMC"));
            a.setProximacita(rs.getDate("proximacita").toLocalDate());
            a.setEstado(rs.getBoolean("estado"));
            a.setObs(rs.getString("obs"));
            lista.add(a);
        }
        ps.close();
        return lista;
    }
    
    public List<EntidadControl> listarControlesPacientes(int id) throws SQLException{
        List<EntidadControl> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String sql = "select * from controles where estado = 1 and idPaciente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            EntidadControl a = new EntidadControl();
            a.setIdControl(rs.getInt("idControl"));
            a.setIdPaciente(rs.getInt("idPaciente"));
            a.setFecha(rs.getDate("fecha").toLocalDate());
            a.setPeso(rs.getDouble("peso"));
            a.setAltura(rs.getDouble("altura"));
            a.setCintura(rs.getDouble("cintura"));
            a.setGasenergetico(rs.getDouble("gasenergetico"));
            a.setIMC(rs.getDouble("IMC"));
            a.setProximacita(rs.getDate("proximacita").toLocalDate());
            a.setEstado(rs.getBoolean("estado"));
            a.setObs(rs.getString("obs"));
            lista.add(a);
        }
        ps.close();
        return lista;
    }
   public List<EntidadControl> listarControlesID(int id) throws SQLException{
        List<EntidadControl> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String sql = "select * from controles where estado = 1 and idControl = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            EntidadControl a = new EntidadControl();
            a.setIdControl(rs.getInt("idControl"));
            a.setIdPaciente(rs.getInt("idPaciente"));
            a.setFecha(rs.getDate("fecha").toLocalDate());
            a.setPeso(rs.getDouble("peso"));
            a.setAltura(rs.getDouble("altura"));
            a.setCintura(rs.getDouble("cintura"));
            a.setGasenergetico(rs.getDouble("gasenergetico"));
            a.setIMC(rs.getDouble("IMC"));
            a.setProximacita(rs.getDate("proximacita").toLocalDate());
            a.setEstado(rs.getBoolean("estado"));
            a.setObs(rs.getString("obs"));
            lista.add(a);
        }
        ps.close();
        return lista;
    }
    
   public EntidadControl ControlxID(int id) throws SQLException{
        EntidadControl a = new EntidadControl();
        Connection con = Conexion.getConexion();
        String sql = "select * from controles where idControl = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            a.setIdControl(rs.getInt("idControl"));
            a.setIdPaciente(rs.getInt("idPaciente"));
            a.setFecha(rs.getDate("fecha").toLocalDate());
            a.setPeso(rs.getDouble("peso"));
            a.setAltura(rs.getDouble("altura"));
            a.setCintura(rs.getDouble("cintura"));
            a.setGasenergetico(rs.getDouble("gasenergetico"));
            a.setIMC(rs.getDouble("IMC"));
            a.setProximacita(rs.getDate("proximacita").toLocalDate());
            a.setEstado(rs.getBoolean("estado"));
            a.setObs(rs.getString("obs"));
        }
        ps.close();
        return a;
    }
   
    
}
