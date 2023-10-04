/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import datas.DataDieta;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.sql.Connection;
import vistas.VistaDieta;
import vistas.VistaPantallaPrincipal;

/**
 *
 * @author Matias
 */
public class ControladorDieta implements ActionListener, KeyListener{
    
    private Connection con;
    private final VistaDieta vista;
    private final DataDieta data;
    private final VistaPantallaPrincipal menu;
    private int idDieta;
    
}
