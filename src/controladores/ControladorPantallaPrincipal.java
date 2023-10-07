package controladores;

import datas.DataDieta;
import datas.DataDieta_Comida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datas.DataPaciente;
import vistas.VistaDieta;
import vistas.VistaDieta_Comida;
import vistas.VistaPacientes;
import vistas.VistaPantallaPrincipal;
import controladores.ControladorDieta_Comida;

/**
 * @author Dario
 */
public class ControladorPantallaPrincipal implements ActionListener {

    private VistaPantallaPrincipal menu;

    public ControladorPantallaPrincipal(VistaPantallaPrincipal menu) {
        this.menu = menu;
        menu.btSalir.addActionListener(this);
        menu.btDieta.addActionListener(this);
        menu.btPacientes.addActionListener(this);

    }

    public void iniciar() {
        menu.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btSalir) {
            menu.dispose();
        }
        if (e.getSource() == menu.btPacientes) { // Iniciar el jInternalFrame de Pacientes
            VistaPacientes vista = new VistaPacientes();
            DataPaciente data = new DataPaciente();
            ControladorVistaPacientes ctrl = new ControladorVistaPacientes(menu,vista,data);
            
            ctrl.iniciar();
        }
        if (e.getSource() == menu.btDieta){
            VistaDieta vista = new VistaDieta();
            DataDieta data = new DataDieta();
            ControladorDieta ctrl = new ControladorDieta(menu, vista, data);
            ctrl.iniciar();
            
            
        }
        if (e.getSource() == menu.btDietaComida){
            VistaDieta_Comida vista = new VistaDieta_Comida();
            DataDieta_Comida data = new DataDieta_Comida();
            ControladorDieta_Comida ctrl = new ControladorDieta_Comida(menu,vista,data);
            ctrl.iniciar();

        }
        if (e.getSource() == menu.btComida){
            
            
        }

    } // Fin metodo actionPerformed

}
