package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialMenuMonitor extends JDialog {
    private JPanel panelMonitor;
    private JButton buttonSeeMonitors;
    private JButton buttonDelMonitors;
    private JButton buttonUpdtMonitors;
    private JButton buttonAddMonitors;
    private JButton buttonExit;


    public JDialMenuMonitor() {
        setContentPane(panelMonitor);
        setModal(true);
        setText();
        buttonExit.addActionListener(l -> dispose());
        buttonSeeMonitors.addActionListener(listenerSeeMonitors());
        buttonAddMonitors.addActionListener(listenerAddMonitors());
        buttonDelMonitors.addActionListener(listenerDelMonitors());
        buttonUpdtMonitors.addActionListener(listenerUpdtMonitors());

    }

    private ActionListener listenerUpdtMonitors() {
        return l-> {
            JDialog updtActivities = new JDialUpdtActivities();
            updtActivities.setSize(400, 300);
            updtActivities.setVisible(true);
        };
    }

    private ActionListener listenerDelMonitors() {
        return l ->{
            JDialog delActivities = new JDialDelActivities();
            delActivities.setSize(400,300);
            delActivities.setVisible(true);
        };
    }


    private ActionListener listenerAddMonitors() {
        return l ->{
            JDialog addMonitor = new RegistrarMonitor();
            addMonitor.setSize(600,500);
            addMonitor.setVisible(true);
        };
    }

    private ActionListener listenerSeeMonitors() {
        return l -> {
            JDialog seeMonitors = new MostrarMonitor();
            seeMonitors.setSize(600,500);
            seeMonitors.setVisible(true);
        };
    }



    private void setText() {
        buttonAddMonitors.setText("Add monitors");
        buttonSeeMonitors.setText("See monitors");
        buttonDelMonitors.setText("Delete monitors");
        buttonUpdtMonitors.setText("Update monitors");
        buttonExit.setText("Exit");
    }
}

