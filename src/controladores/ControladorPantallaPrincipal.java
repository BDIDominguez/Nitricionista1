package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datas.DataPaciente;
import vistas.VistaPacientes;
import vistas.VistaPantallaPrincipal;

/**
 * @author Dario
 */
public class ControladorPantallaPrincipal implements ActionListener {

    private VistaPantallaPrincipal menu;

    public ControladorPantallaPrincipal(VistaPantallaPrincipal menu) {
        this.menu = menu;
        menu.btSalir.addActionListener(this);
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

    } // Fin metodo actionPerformed

}
