package Taller_base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Registro extends JFrame {
    private JTextField textField1; // Campo para COD
    private JTextField textField2; // Campo para Nombre
    private JTextField textField3; // Campo para Apellido
    private JTextField textField4; // Campo para Edad
    private JTextField textField5; // Campo para Correo
    private JPanel panelRegistro;
    private JButton registrarButton;
    private JButton menuButton;

    public Registro() {
        setSize(500, 300);
        setPreferredSize(new Dimension(500, 300));
        pack();
        setTitle("Registro de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelRegistro);
        setLocationRelativeTo(null);

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDatos(); // Llama al método para registrar datos
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Banner banner = new Banner();
                banner.visualizar();
                dispose();
            }
        });
    }

    private void registrarDatos() {
        // Obtén los valores de los campos de texto
        String cod = textField1.getText().trim();
        String nombre = textField2.getText().trim();
        String apellido = textField3.getText().trim();
        String edad = textField4.getText().trim();
        String correo = textField5.getText().trim();

        // Validación simple de los campos
        if (cod.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar que la edad sea un número
        try {
            Integer.parseInt(edad);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3307/consurso";
        String user = "root";
        String password = "1234";
        String query = "INSERT INTO inscripciones (COD, NOMBRE, APELLIDO, EDAD, CORREO) VALUES (?, ?, ?, ?, ?)";

        // Conexión y ejecución de la consulta
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(cod));
            statement.setString(2, nombre);
            statement.setString(3, apellido);
            statement.setInt(4, Integer.parseInt(edad));
            statement.setString(5, correo);

            int rowsInserted = statement.executeUpdate(); // Ejecuta la consulta

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Registro exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos(); // Limpia los campos después de registrar
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public void visualizar() {
        setVisible(true);
    }
}
