/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista;

import controladores.ControladorPantallaPrincipal;
import vistas.VistaPantallaPrincipal;

/**
 *
 * @author Dario
 */
public class Nutricionista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       VistaPantallaPrincipal vista = new VistaPantallaPrincipal();
        ControladorPantallaPrincipal ctrl = new ControladorPantallaPrincipal(vista);
        ctrl.iniciar();
    }
    
}
