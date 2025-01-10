package Taller_base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Banner extends JFrame{
    private JButton mostrarDatosButton;
    public JPanel panelBanner;
    private JButton registrarButton;

    public Banner() {
        setSize(500,300);
        setPreferredSize(new Dimension(500,300));
        pack();
        setTitle("Transacciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelBanner);
        setLocationRelativeTo(null);

        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             Mostrar mostrar= new Mostrar();
             mostrar.visualizar();
             dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro registro= new Registro();
                registro.visualizar();
                dispose();
            }
        });
    }

    public void visualizar() {
        setVisible(true);
    }
}
