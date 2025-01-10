package Taller_base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private JTextField Usuario;
    public JPanel LoginPanel;
    private JButton AccederButton;
    private JPasswordField passwordField1;

    public Login(JFrame parentFrame) {
        AccederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //optener los datos
                String usuarioIngresado = Usuario.getText();
                String passwordIngresado = new String(passwordField1.getPassword());
                // Validación de las credenciales
                if (validarCredenciales(usuarioIngresado,passwordIngresado)){
                    JOptionPane.showMessageDialog(null,"Bienvenido \n"+usuarioIngresado+"\n Inicio de sesion exitosa");

                    //abre la siguiente pantalla
                    Banner banner = new Banner();
                    banner.visualizar();
                    parentFrame.dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    //Metodo para validar las credenciales
    private boolean validarCredenciales(String usuarioIngresado,String passwordIngresado){
        //Datos de Conexion
        String url = "jdbc:mysql://localhost:3307/consurso";
        String user = "root";
        String password = "1234";
        String query = "SELECT CORREO,Pass FROM usuarios WHERE CORREO = ? AND Pass = ?";
        try (Connection connection = DriverManager.getConnection(url,user,password)){
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, usuarioIngresado);
            statement.setString(2, passwordIngresado);
            ResultSet resultSet=statement.executeQuery();

            return resultSet.next();
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos","ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
