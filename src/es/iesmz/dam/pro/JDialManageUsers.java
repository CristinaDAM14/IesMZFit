package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialManageUsers extends JDialog {
    private JPanel panelUser;
    private JButton buttonSeeUsers;
    private JButton buttonDelUsers;
    private JButton buttonUpdtUsers;
    private JButton buttonAddUsers;
    private JButton buttonExit;


    public JDialManageUsers() {
        setContentPane(panelUser);
        setModal(true);
        setTitle("Manage Users");
        setText();
        buttonExit.addActionListener(l -> dispose());
        buttonSeeUsers.addActionListener(listenerSeeUsers());
        buttonAddUsers.addActionListener(listenerAddUsers());
        buttonDelUsers.addActionListener(listenerDelUsers());
        buttonUpdtUsers.addActionListener(listenerUpdtUsers());

    }

    private ActionListener listenerUpdtUsers() {
        return l-> {
            JDialog updtPerson = new JDialUpdtPerson();
            updtPerson.setSize(400, 300);
            updtPerson.setVisible(true);
        };
    }

    private ActionListener listenerDelUsers() {
        return l ->{
            JDialog delUsers = new JDialDelPerson();
            delUsers.setSize(400,300);
            delUsers.setVisible(true);
        };
    }


    private ActionListener listenerAddUsers() {
        return l ->{
            JDialog addUser = new RegistrarPersona();
            addUser.setSize(600,500);
            addUser.setVisible(true);
        };
    }

    private ActionListener listenerSeeUsers() {
        return l -> {
            JDialog seeUsers = new MostrarPersona();
            seeUsers.setSize(600,500);
            seeUsers.setVisible(true);
        };
    }



    private void setText() {
        buttonAddUsers.setText("Add monitors");
        buttonSeeUsers.setText("See monitors");
        buttonDelUsers.setText("Delete monitors");
        buttonUpdtUsers.setText("Update monitors");
        buttonExit.setText("Exit");
    }
}

