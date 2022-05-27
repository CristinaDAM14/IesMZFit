package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class RegistrarMonitor extends JDialog {
    private JPanel panelPrincipal;
    private JButton btnCancelar;
    private JButton btnRegistrar;
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

    public RegistrarMonitor() {
        setContentPane(panelPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(btnCancelar);

        ButtonGroup genero = new ButtonGroup();
        genero.add(rbtnHombre);
        genero.add(rbtnMujer);

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRegistrar();
            }
        });


    }

    private void onCancelar() {

        dispose();
    }

    private void onRegistrar() {

    }

    public static void main(String[] args) {
        RegistrarMonitor dialog = new RegistrarMonitor();
        dialog.setSize(600,400);
        dialog.setVisible(true);
    }
}
