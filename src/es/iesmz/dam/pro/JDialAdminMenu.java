package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialAdminMenu extends JDialog {
    private JPanel adminPanel;
    private JButton buttonManageUsers;
    private JButton buttonManageMonitor;
    private JButton buttonManageActivities;
    private JButton buttonExit;

    public JDialAdminMenu() {
        setContentPane(adminPanel);
        setModal(true);
        buttonExit.setText("Exit");
        buttonManageActivities.setText("Manage Activities");
        buttonManageMonitor.setText("Manage Monitors");
        buttonManageUsers.setText("Manage Users");
        buttonManageMonitor.addActionListener(listenerMonitor());
        buttonManageActivities.addActionListener(listenerActivities());
        buttonExit.addActionListener(l -> dispose());
    }

    private ActionListener listenerMonitor() {
        return l ->{
            JDialog menuMonitor = new JDialMenuMonitor();
            menuMonitor.setSize(300,500);
            menuMonitor.setVisible(true);
        };
    }
    private ActionListener listenerActivities() {
        return l ->{
            JDialog menuActivities = new JDialMenuActivities();
            menuActivities.setSize(300,500);
            menuActivities.setVisible(true);
        };
    }
}
