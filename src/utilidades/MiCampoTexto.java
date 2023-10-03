package utilidades;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author Dario
 */
public class MiCampoTexto extends JTextField {

    private boolean tienePunto = false;

    public MiCampoTexto(int tipo) {
        super();
        addKeyListener(new KeyAdapter() { 
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                switch (tipo) {
                    case 1: // es un numero entero sin nada mas que numero del 0 al 9
                        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                            e.consume();
                        }
                        break;
                    case 2: // es un campo texto acepta tanto letras como numeros
                        if (!(Character.isDigit(c) || c == ',' || c == KeyEvent.VK_SPACE || (Character.isLetter(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                            e.consume();
                        }
                        break;
                    case 3: // acepta numero y punto decimal
                        if (c == '.') {
                            if (tienePunto) {
                                e.consume();
                            } else {
                                tienePunto = true;
                            }
                        } else if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                            e.consume();
                        }
                        break;
                        case 4: // es un numero entero sin nada mas que numero del 0 al 9
                        if (!(Character.isDigit(c) || c == '+' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                            e.consume();
                        }
                        break;
                }
            }
        });
        // Seleccionar todo el contenido cuando se lo selecciona
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                selectAll();
            }
        });

        // Evitar la opcion de pegar texto
        setTransferHandler(null);
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        tienePunto = t.contains(".");
    }

}
