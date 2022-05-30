package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialUserMenu extends JDialog {
    private JPanel userPanel;
    private JButton buttonSeeSesions;
    private JButton buttonJoinSesion;
    private JButton buttonChangeSubscription;

    public JDialUserMenu() {
        setContentPane(userPanel);
        setModal(true);
        buttonSeeSesions.setText("Show sessions");
        buttonJoinSesion.setText("Join session");
        buttonChangeSubscription.setText("Change subscription");
        buttonSeeSesions.addActionListener(listenerSeeSessions());
        buttonChangeSubscription.addActionListener(listenerChangeSub());
    }

    private ActionListener listenerChangeSub() {
        return l ->{

        };
    }

    private ActionListener listenerSeeSessions() {
        return l ->{

        };
    }

}
