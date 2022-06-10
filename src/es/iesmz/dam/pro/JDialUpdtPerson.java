package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialUpdtPerson extends JDialog {
    private JPanel mainPanelDel;
    private JPanel searchPanel;
    private JPanel dataPanel;
    private JPanel buttonsPanel;
    private JButton buttonUpdate;
    private JButton buttonExit;
    private JTextField textFieldID;
    private JButton buttonSearch;
    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldPayment;
    private JTextField textFieldSubscription;
    private JTextField textFieldDNI;


    public JDialUpdtPerson(){
        setContentPane(mainPanelDel);
        setModal(true);
        setTitle("Update Users");
        buttonExit.addActionListener(l ->dispose());
        buttonSearch.addActionListener(listenerSearch());
        buttonUpdate.addActionListener(listenerUpdate());
    }

    private ActionListener listenerUpdate() {
        return l -> {
            Object[] options = new Object[2];
            options[0] = "YES";
            options[1] = "NO";
            // Dialogo de confirmacion para que el usuario decida si quiere actualizarlo o no.
            int resp = JOptionPane.showOptionDialog(null, "Are you sure you want to update the User?", "Update."
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null
                    , options, null);

            // Si pulsa si, intentamos actualizarlo, en caso de que no exista saldra un mensaje diciendo que no existe.
            if (resp == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(textFieldID.getText());
                String name = textFieldName.getText();
                String lastname= textFieldLastName.getText();
                String phone= textFieldPhone.getText();
                String email= textFieldEmail.getText();
                String payment= textFieldPayment.getText();
                int idSubscription = Integer.parseInt(textFieldSubscription.getText());
                String dni = textFieldDNI.getText();
                User user = new User(id,dni,name,lastname,phone,email,payment,idSubscription);
                if (DBManager.updateUser(user)) {
                    JOptionPane.showMessageDialog(null, "User successfully updated", "updated", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong, impossible to update the User"
                            , "Error updating", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener listenerSearch() {
        return l->{
            try {
                int id = Integer.parseInt(textFieldID.getText());
                User user = DBManager.getUser(id);
                if (user != null) {
                    dataPanel.setVisible(true);
                    poblateTextFields(user);
                }else {
                    JOptionPane.showMessageDialog(null,"There is not a User matching that id"
                            ,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Introduce a numeric ID"
                        ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        };
    }
    private void poblateTextFields(User user) {
        textFieldName.setText(user.getName());
        textFieldLastName.setText(user.getLastName());
        textFieldPhone.setText(user.getPhone());
        textFieldEmail.setText(user.getEmail());
        textFieldPayment.setText(user.getPaymentMethod());
        textFieldSubscription.setText(String.valueOf(user.getSubscriptionID()));
        buttonUpdate.setEnabled(true);
    }
}

