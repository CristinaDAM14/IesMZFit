package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class RegistrarPersona extends JDialog {
    private JPanel panelPrincipal;
    private JButton btnCancelar;
    private JButton btnRegistrar;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtFechaNacimiento;
    private JRadioButton rbtnEfectivo;
    private JRadioButton rbtnTarjeta;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblDNI;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblFechaNacimiento;
    private JLabel lblMetodoPago;

    public RegistrarPersona() {
        setContentPane(panelPrincipal);
        setModal(true);
        getRootPane().setDefaultButton(btnCancelar);

        ButtonGroup metodosPago = new ButtonGroup();
        metodosPago.add(rbtnEfectivo);
        metodosPago.add(rbtnTarjeta);

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


}
