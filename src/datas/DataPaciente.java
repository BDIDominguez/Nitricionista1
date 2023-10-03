/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidades.EntidadPaciente;

/**
 *
 * @author Dario
 */
public class DataPaciente {
    private Connection con;

    public DataPaciente() {
    }
    
    public EntidadPaciente crearPaciente(EntidadPaciente pa) throws SQLException{
        con = Conexion.getConexion();
        String sql = "insert into pacientes (nombre,dni,domicilio,telefono) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1,pa.getNombre());
        ps.setInt(2, pa.getDni());
        ps.setString(3, pa.getDomicilio());
        ps.setString(4, pa.getTelefono());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();  // Regresa los ID generados por la insercion anterior
        if (rs.next()) { // 
            pa.setIdpaciente(rs.getInt(1));
        }
        ps.close();
        return pa;
    }
    public boolean modificarPaciente(EntidadPaciente pa) throws SQLException{
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "update pacientes set nombre = ?,dni = ?, domicilio=?, telefono=? where idpaciente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,pa.getNombre());
        ps.setInt(2, pa.getDni());
        ps.setString(3, pa.getDomicilio());
        ps.setString(4, pa.getTelefono());
        ps.setInt(5, pa.getIdpaciente());
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }
    
    public boolean eliminarPaciente(int id) throws SQLException{
        boolean vRespuesta = false;
        con = Conexion.getConexion();
        String sql = "delete from paciente where idpaciente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        vRespuesta = true;
        ps.close();
        return vRespuesta;
    }
    
    public List<EntidadPaciente> listarPacientes() throws SQLException{
        List<EntidadPaciente> pacientes = new ArrayList<>();
        con = Conexion.getConexion();
        String sql = "Select * from pacientes";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()){
            EntidadPaciente a = new EntidadPaciente();
            a.setIdpaciente(rs.getInt("idpaciente"));
            a.setDni(rs.getInt("dni"));
            a.setNombre(rs.getString("nombre"));
            a.setDomicilio(rs.getString("domicilio"));
            a.setTelefono(rs.getString("telefono"));
            pacientes.add(a);
        }
        ps.close();
        return pacientes;
    }
    
    public EntidadPaciente pacienteDNI(int dni) throws SQLException{
        EntidadPaciente a = new EntidadPaciente();
        con = Conexion.getConexion();
        String sql = "Select * from pacientes where dni = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, dni);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()){
            a.setIdpaciente(rs.getInt("idpaciente"));
            a.setDni(rs.getInt("dni"));
            a.setNombre(rs.getString("nombre"));
            a.setDomicilio(rs.getString("domicilio"));
            a.setTelefono(rs.getString("telefono"));
        }
        ps.close();
        return a;
    }
    public EntidadPaciente pacienteID(int ID) throws SQLException{
        EntidadPaciente a = new EntidadPaciente();
        con = Conexion.getConexion();
        String sql = "Select * from pacientes where idpaciente = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, ID);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()){
            a.setIdpaciente(rs.getInt("idpaciente"));
            a.setDni(rs.getInt("dni"));
            a.setNombre(rs.getString("nombre"));
            a.setDomicilio(rs.getString("domicilio"));
            a.setTelefono(rs.getString("telefono"));
        }
        ps.close();
        return a;
    }
    
    
} // Fin de la Clase
