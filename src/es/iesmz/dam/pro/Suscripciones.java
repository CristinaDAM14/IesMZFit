package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;

public class Suscripciones extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel panelGeneral;
    private JPanel panelBronze;
    private JPanel panelSilver;
    private JPanel panelGold;
    private JPanel panelPlatinum;
    private JRadioButton eligeBronze;
    private JRadioButton eligeSilver;
    private JRadioButton eligeGold;
    private JRadioButton eligePlatinum;
    private JButton buttonCancel;

    public Suscripciones() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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
        Suscripciones dialog = new Suscripciones();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
