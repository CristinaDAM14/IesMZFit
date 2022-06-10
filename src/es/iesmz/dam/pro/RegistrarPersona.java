package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistrarPersona extends JDialog {
    private JPanel panelPrincipal;
    private JButton btnCancelar;
    private JButton btnRegistrar;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JRadioButton rbtnEfectivo;
    private JRadioButton rbtnTarjeta;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblDNI;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JLabel lblMetodoPago;

    public RegistrarPersona() {
        setContentPane(panelPrincipal);
        setModal(true);
        setTitle("Add Users");
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

        String metodo="";

        if (rbtnTarjeta.isSelected()){
            metodo="Tarjeta";
        }
        if (rbtnEfectivo.isSelected()) {
            metodo="Efectivo";
        }
        int randSubID = (int) (1 + Math.random() * (100));
        User user = new User(txtDNI.getText(), txtNombre.getText(), txtApellidos.getText(), txtTelefono.getText(), txtCorreo.getText(), metodo,randSubID);

        if (DBManager.insertUsuario(user)){

            JOptionPane.showMessageDialog(null, "Se a guardado correctamente");

            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "NO se a guardado correctamente");
        }

    }



}
