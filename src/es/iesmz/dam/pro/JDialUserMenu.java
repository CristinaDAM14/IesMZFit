package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialUserMenu extends JDialog {
    private JPanel userPanel;
    private JButton buttonSeeActivities;
    private JButton buttonJoinActivities;
    private JButton buttonChangeSubscription;
    private final String user;
    private final String password;

    public JDialUserMenu(String user, String password) {
        setContentPane(userPanel);
        setModal(true);
        this.user = user;
        this.password = password;
        buttonSeeActivities.setText("Show activities");
        buttonJoinActivities.setText("Join activity");
        buttonChangeSubscription.setText("Change subscription");
        buttonSeeActivities.addActionListener(listenerSeeActivities());
        buttonChangeSubscription.addActionListener(listenerChangeSub());
        buttonJoinActivities.addActionListener(listenerJoinActivity());
    }

    private ActionListener listenerChangeSub() {
        return l ->{
            JDialog eligeSuscripcion = new Suscripciones(user,password);
            eligeSuscripcion.setVisible(true);
            eligeSuscripcion.setSize(400,600);
        };
    }

    private ActionListener listenerSeeActivities() {
        return l ->{
            JDialog usuarioActividades = new UsuarioActividades();
            usuarioActividades.setVisible(true);
            usuarioActividades.setSize(400,600);
        };
    }

    private ActionListener listenerJoinActivity() {
        return l ->{

        };
    }

}
