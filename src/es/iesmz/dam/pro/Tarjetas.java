package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class Tarjetas extends JDialog {
    private JPanel PanelGeneral;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel datosPanel;
    private JTextField NomText;
    private JTextField ApellText;
    private JTextField DNIText;
    private JTextField IDText;
    private JLabel NomLabel;
    private JLabel ApellLabel;
    private JLabel DNILabel;
    private JLabel IDLabel;
    private JPanel PanelPago;
    private JComboBox Opciones;
    private JTextField TitularText;
    private JTextField TarjetaText;
    private JTextField CVVText;
    private JSpinner DataText;
    private JButton pagarButton;
    private JLabel ImagenLabel;
    private JLabel TarjetaLabel;
    private JLabel TitularLabel;
    private JLabel CVVLabel;
    private JLabel DataLabel;
    private JPanel MetodoPanel;
    private JButton CreditoButton;
    private JButton paypalButton;
    private JPanel PanelBotton;
    private JPanel PanelDatos;

    public Tarjetas() {
        setContentPane(PanelGeneral);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        PanelGeneral.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ImagenLabel.setIcon(new ImageIcon("img\\PagosTarjeta.PNG"));
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Tarjetas dialog = new Tarjetas();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
