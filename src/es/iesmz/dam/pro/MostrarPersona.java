package es.iesmz.dam.pro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class MostrarPersona extends JDialog {
    private JPanel seeUsersPanel;
    private JScrollPane usersScrollPanel;
    private JTable tableMonitors;
    private JButton buttonExit;

    public MostrarPersona() {
        setContentPane(seeUsersPanel);
        setModal(true);
        setTitle("See Users");
        buttonExit.setText("Exit");
        buttonExit.addActionListener(l -> dispose());
        createTable();
    }
    public void createTable(){
        String id = "ID";
        String dni = "DNI";
        String name = "NAME";
        String lastName= "LAST NAME";
        String phone= "PHONE";
        String email= "E-MAIL";
        String paymentMethod= "PAYMENT METHOD";
        String subscriptionID= "SUBSCRIPTION ID";
        Object[] tableNames = {id, dni, name, lastName, phone, email, paymentMethod, subscriptionID};
        List<User> users = DBManager.getUsersList();
        Object[][] datosTabla = new Object[users.size()][tableNames.length];

        for (int i = 0; i < users.size(); i++) {
            datosTabla[i][0] = users.get(i).getId();
            datosTabla[i][1] = users.get(i).getName();
            datosTabla[i][2] = users.get(i).getLastName();
            datosTabla[i][3] = users.get(i).getPhone();
            datosTabla[i][4] = users.get(i).getEmail();
            datosTabla[i][5] = users.get(i).getPaymentMethod();
            datosTabla[i][6] = users.get(i).getSubscriptionID();
        }
        tableMonitors.setModel(new DefaultTableModel(datosTabla,tableNames));
        tableMonitors.setVisible(true);
    }

}
