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



/**
 *
 * @author Dario
 */
public class DataControl {
    public EntidadControl crearPaciente(EntidadControl pa) throws SQLException{
        Connection con = Conexion.getConexion();
        String sql = "insert into controles (idControl,idPaciente,fecha,peso,altura,cintura,gasenergetico,IMC,proximacita) values (?,?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1,pa.getIdControl());
        ps.setInt(2,pa.getIdPaciente());
        ps.setDate(3,Date.valueOf(pa.getFecha()));
        ps.setDouble(4, pa.getPeso());
        ps.setDouble(5,pa.getAltura());
        ps.setDouble(6, pa.getCintura());
        ps.setDouble(7,pa.getGasenergetico());
        ps.setDouble(8, pa.getIMC());
        ps.setDate(9,Date.valueOf(pa.getProximacita()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();  // Regresa los ID generados por la insercion anterior
        if (rs.next()) { // 
            pa.setIdControl(rs.getInt(1));
        }
        ps.close();
        return pa;
    }
    
   // public
    
}
