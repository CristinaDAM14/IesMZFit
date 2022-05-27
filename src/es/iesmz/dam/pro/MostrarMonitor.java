package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class MostrarMonitor extends JDialog {
    private JPanel panelPrincipal;
    private JButton btnEliminar;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellidos;
    private JTextField txtApellidos;
    private JLabel lblFechaNacimiento;
    private JTextField txtFechaNacimiento;
    private JLabel lblEspecialidad;
    private JTextField txtEspecialidad;
    private JLabel lblFechaInicio;
    private JTextField txtFechaInicio;
    private JLabel lblGenero;
    private JRadioButton rbtnHombre;
    private JRadioButton rbtnMujer;
    private JTextField txtCodigoBucar;
    private JButton btnBuscar;
    private JLabel lblCodigoBuscar;

    public MostrarMonitor() {
        setContentPane(panelPrincipal);
        setModal(true);

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEliminar();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onBuscar();
            }
        });


    }

    private void onEliminar() {

        dispose();
    }

    private void onBuscar() {

    }

    public static void main(String[] args) {
        MostrarMonitor dialog = new MostrarMonitor();
        dialog.setSize(600,400);
        dialog.setVisible(true);
    }
}
