package Taller_base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField Usuario;
    public JPanel LoginPanel;
    private JButton AccederButton;
    private JPasswordField passwordField1;

    public Login(JFrame parentFrame) {
        AccederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user= "";
                String pass="";
                //optener los datos
                String usuarioIngresado = Usuario.getText();
                String passwordIngresado = new String(passwordField1.getPassword());
                // Validación de las credenciales
                if (usuarioIngresado.equals(user) && passwordIngresado.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "BIENVENIDO\n"+user+"\n Inicio de sesión exitoso");

                    Banner banner = new Banner();
                    banner.visualizar();
                    parentFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
