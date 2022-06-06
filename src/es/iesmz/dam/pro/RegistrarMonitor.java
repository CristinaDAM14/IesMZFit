package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        String gene="";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate fechaNac= LocalDate.parse(txtFechaNacimiento.getText(), formatter);

        if (rbtnHombre.isSelected()){
            gene="H";
        }
        if (rbtnMujer.isSelected()) {
            gene="M";
        }

        if (DBManager.insertMonitor(txtNombre.getText(), txtApellidos.getText(), fechaNac, gene)){

            JOptionPane.showMessageDialog(null, "Se a guardado correctamente");

            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "NO se a guardado correctamente");
        }

    }

    public static void main(String[] args) {
        RegistrarMonitor dialog = new RegistrarMonitor();
        dialog.setSize(600,400);
        dialog.setVisible(true);
    }
}
