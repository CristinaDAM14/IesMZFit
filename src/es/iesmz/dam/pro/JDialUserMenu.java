package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialUserMenu extends JDialog {
    private JPanel userPanel;
    private JButton buttonSeeSesions;
    private JButton buttonJoinSesion;
    private JButton buttonChangeSubscription;
    private String user;
    private String password;

    public JDialUserMenu(String user, String password) {
        setContentPane(userPanel);
        setModal(true);
        this.user = user;
        this.password = password;
        buttonSeeSesions.setText("Show sessions");
        buttonJoinSesion.setText("Join session");
        buttonChangeSubscription.setText("Change subscription");
        buttonSeeSesions.addActionListener(listenerSeeActivities());
        buttonChangeSubscription.addActionListener(listenerChangeSub());
    }

    private ActionListener listenerChangeSub() {
        return l ->{
            JDialog eligeSuscripcion = new Suscripciones(user,password);
            eligeSuscripcion.setVisible(true);
            eligeSuscripcion.setSize(400,600);
        };
    }

    private ActionListener listenerJoinActivity() {
        return l ->{
            JDialog usuarioActividades = new UsuarioActividades();
            usuarioActividades.setVisible(true);
            usuarioActividades.setSize(400,600);
        };
    }

    private ActionListener listenerSeeActivities() {
        return l ->{

        };
    }

}
