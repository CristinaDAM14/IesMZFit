package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialMenuActivities extends JDialog {
    private JPanel panelActivities;
    private JButton buttonSeeActivities;
    private JButton buttonDelActivities;
    private JButton buttonUpdtActivities;
    private JButton buttonAddActivities;
    private JButton buttonExit;


    public JDialMenuActivities() {
        setContentPane(panelActivities);
        setModal(true);
        setText();
        buttonExit.addActionListener(l -> dispose());
        buttonSeeActivities.addActionListener(listenerSeeActivities());
        buttonAddActivities.addActionListener(listenerAddActivities());
        buttonDelActivities.addActionListener(listenerDelActivities());
        buttonUpdtActivities.addActionListener(listenerUpdtActivities());

    }

    private ActionListener listenerUpdtActivities() {
        return l-> {
            JDialog updtActivities = new JDialUpdtActivities();
            updtActivities.setSize(400, 300);
            updtActivities.setVisible(true);
        };
    }

    private ActionListener listenerDelActivities() {
        return l ->{
            JDialog delActivities = new JDialDelActivities();
            delActivities.setSize(400,300);
            delActivities.setVisible(true);
        };
    }


    private ActionListener listenerAddActivities() {
        return l ->{
            JDialog addActivities = new JDialAddActivities();
            addActivities.setSize(600,500);
            addActivities.setVisible(true);
        };
    }

    private ActionListener listenerSeeActivities() {
        return l -> {
            JDialog seeActivities = new JDialSeeActivities();
            seeActivities.setSize(600,500);
            seeActivities.setVisible(true);
        };
    }



    private void setText() {
        buttonAddActivities.setText("Add activities");
        buttonSeeActivities.setText("See activities");
        buttonDelActivities.setText("Delete activities");
        buttonUpdtActivities.setText("Update activities");
        buttonExit.setText("Exit");
    }
}
