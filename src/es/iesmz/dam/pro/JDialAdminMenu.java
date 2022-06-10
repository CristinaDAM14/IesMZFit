package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialAdminMenu extends JDialog {
    private JPanel adminPanel;
    private JButton buttonManageUsers;
    private JButton buttonManageMonitor;
    private JButton buttonManageActivities;
    private JButton buttonExit;
    private JButton buttonManageCards;

    public JDialAdminMenu() {
        setContentPane(adminPanel);
        setModal(true);
        buttonExit.setText("Exit");
        setTitle("Admin Menu");
        buttonManageActivities.setText("Manage Activities");
        buttonManageMonitor.setText("Manage Monitors");
        buttonManageUsers.setText("Manage Users");
        buttonManageCards.setText("Manage Cards");
        buttonManageMonitor.addActionListener(listenerMonitor());
        buttonManageActivities.addActionListener(listenerActivities());
        buttonManageUsers.addActionListener(listenerUsers());
        buttonExit.addActionListener(l -> dispose());
        buttonManageCards.addActionListener(listenerCards());
    }

    private ActionListener listenerUsers() {
        return l ->{
            JDialog menuUser = new JDialManageUsers();
            menuUser.setSize(400,600);
            menuUser.setVisible(true);
        };
    }

    private ActionListener listenerMonitor() {
        return l ->{
            JDialog menuMonitor = new JDialMenuMonitor();
            menuMonitor.setSize(400,600);
            menuMonitor.setVisible(true);
        };
    }
    private ActionListener listenerActivities() {
        return l ->{
            JDialog menuActivities = new JDialMenuActivities();
            menuActivities.setSize(400,600);
            menuActivities.setVisible(true);
        };
    }
    private ActionListener listenerCards()  {
        return l->{
            JDialog menuCard = new Tarjetas();
            menuCard.setSize(700,400);
            menuCard.setVisible(true);
        };
    }
}
