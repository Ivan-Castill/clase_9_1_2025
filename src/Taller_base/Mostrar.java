package Taller_base;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mostrar extends JFrame{
    private JTable table1;
    private JPanel MostrarPanel;
    private JTextField textField1;
    private JButton buscarButton;
    private JButton menuButton;
    private JButton mostrarTodosLosDatosButton;

    public Mostrar() {
        setSize(800,500);
        setPreferredSize(new Dimension(800,500));
        pack();
        setTitle("Transacciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(MostrarPanel);
        setLocationRelativeTo(null);

        // Configurar el layout principal
        MostrarPanel = new JPanel();
        MostrarPanel.setLayout(new BorderLayout());
        setContentPane(MostrarPanel);

        // Panel superior para los botones y campo de texto
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Botones alineados a la izquierda

        JLabel labelID = new JLabel("ID:");
        textField1 = new JTextField(10); // Campo de texto con ancho de 10 columnas
        buscarButton = new JButton("Buscar");
        menuButton = new JButton("Menu");
        mostrarTodosLosDatosButton = new JButton("Mostrar Todos los Datos");

        // Agregar los componentes al panel superior
        topPanel.add(labelID);
        topPanel.add(textField1);
        topPanel.add(buscarButton);
        topPanel.add(menuButton);
        topPanel.add(mostrarTodosLosDatosButton);

        // Agregar el panel superior al área norte del BorderLayout
        MostrarPanel.add(topPanel, BorderLayout.NORTH);

        // Configurar la tabla con encabezados y datos vacíos
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{}, // Datos iniciales vacíos
                new String[]{"COD", "NOMBRE", "APELLIDO", "EDAD", "CORREO"} // Encabezados de la tabla
        );
        table1 = new JTable(model);

        // Agregar la tabla dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table1);

        // Agregar el JScrollPane al área central del BorderLayout
        MostrarPanel.add(scrollPane, BorderLayout.CENTER);

        // Cargar los datos al inicio
        cargarTodosLosDatos();

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Banner banner = new Banner();
                banner.visualizar();
                dispose();
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //optener datos COD
                String textoBusqueda= textField1.getText().trim();

                if(!textoBusqueda.isEmpty()){
                    buscarYMostrarporCOD(textoBusqueda);
                }else{
                    JOptionPane.showMessageDialog(null,"Por favor, ingrese un COD valido.","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        mostrarTodosLosDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTodosLosDatos();//cargar todo los datos de la base de datos
            }
        });
    }

    private void  cargarTodosLosDatos(){
        //coneccion a la base de datos
        String url = "jdbc:mysql://localhost:3307/consurso";
        String user = "root";
        String password = "1234";
        String query = "SELECT *FROM inscripciones";

        try(Connection connection = DriverManager.getConnection(url,user,password)){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            //obtener el modelo de la tabla para actualizarl
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);//limpiar datos encotrados a la tabla

            while (resultSet.next()){
                //Agregar los datos encontrados a la tabla
                model.addRow(new Object[]{
                        resultSet.getInt("COD"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("APELLIDO"),
                        resultSet.getInt("EDAD"),
                        resultSet.getString("CORREO")
                });
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos.","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarYMostrarporCOD(String COD){
        //DATOS DE LA CONECCION A LA BASE DE DATOS
        String url = "jdbc:mysql://localhost:3307/consurso";
        String user = "root";
        String password = "1234";
        String quary = "SELECT * FROM inscripciones WHERE COD = ?";

        try (Connection connection = DriverManager.getConnection(url,user,password)){
            PreparedStatement statement = connection.prepareStatement(quary);
            statement.setString(1,COD);
            ResultSet resultSet = statement.executeQuery();

            //optener el modelo de la tabla para actualizarla
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0);

            if (resultSet.next()){
                //agregar los datos encotrados a la tabla
                model.addRow(new Object[]{
                        resultSet.getInt("COD"),
                        resultSet.getString("NOMBRE"),
                        resultSet.getString("APELLIDO"),
                        resultSet.getInt("EDAD"),
                        resultSet.getString("CORREO")
                });
            }else {
                //Mostrar mensaje si no se encuentran datos
                JOptionPane.showMessageDialog(null,"No se entraron datos con el COD proporcionado","Sin resultados",JOptionPane.WARNING_MESSAGE);
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Erro al conectar con la base de datos.","ERROR",JOptionPane.ERROR_MESSAGE);
        }

    }
    public void visualizar() {
        setVisible(true);
    }
}
