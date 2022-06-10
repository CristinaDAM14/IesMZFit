package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialUserMenu extends JDialog {
    private JPanel userPanel;
    private JButton buttonSeeActivities;
    //private JButton buttonJoinActivities;
    private JButton buttonChangeSubscription;
    private final String user;

    public JDialUserMenu(String user) {
        setContentPane(userPanel);
        setModal(true);
        this.user = user;
        buttonSeeActivities.setText("Show activities");
        //buttonJoinActivities.setText("Join activity");
        buttonChangeSubscription.setText("Change subscription");
        buttonSeeActivities.addActionListener(listenerSeeActivities());
        buttonChangeSubscription.addActionListener(listenerChangeSub());
        // Se podria implementar un boton extra para unirse a actividades usando el login(habria que enlazarlo a un id de usuario
        //buttonJoinActivities.addActionListener(listenerJoinActivity());
    }

    private ActionListener listenerChangeSub() {
        return l ->{
            JDialog eligeSuscripcion = new Suscripciones();
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


}
