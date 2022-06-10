package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

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
        setTitle("Subscription");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertaSuscripcion();
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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void insertaSuscripcion(){
        LocalDate prox = LocalDate.now();
        if(eligeBronze.isSelected()){
            DBManager.insertSuscripciones("Bronze", LocalDate.now(), LocalDate.now(), prox.plusMonths(1));
        }
        else if(eligeSilver.isSelected()){
            DBManager.insertSuscripciones("Silver", LocalDate.now(), LocalDate.now(), prox.plusMonths(3));
        }
        else if(eligeGold.isSelected()){
            DBManager.insertSuscripciones("Gold", LocalDate.now(), LocalDate.now(), prox.plusMonths(6));
        }
        else if(eligePlatinum.isSelected()){
            DBManager.insertSuscripciones("Platinum", LocalDate.now(), LocalDate.now(), prox.plusYears(1));
        }
    }

}