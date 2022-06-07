package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class MostrarPersona extends JDialog {
    private JPanel panelPrincipal;
    private JButton btnEliminar;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellidos;
    private JTextField txtApellidos;
    private JLabel lblDNI;
    private JTextField txtDNI;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JLabel lblCorreo;
    private JTextField txtCorreo;
    private JLabel lblFechaNacimiento;
    private JTextField txtFechaNacimiento;
    private JLabel lblMetodoPago;
    private JRadioButton rbtnEfectivo;
    private JRadioButton rbtnTarjeta;
    private JLabel lblDNIBuscar;
    private JTextField txtBuscarDNI;
    private JButton btnBuscar;

    public MostrarPersona() {
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
        String dni = txtBuscarDNI.getText();
        if (dni.matches("[0-9]{8}[a-zA-Z]")){

        }

    }

}
