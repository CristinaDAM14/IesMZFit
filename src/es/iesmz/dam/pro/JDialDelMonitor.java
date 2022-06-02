package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialDelMonitor extends JDialog {
    private JPanel mainPanelDel;
    private JPanel searchPanel;
    private JPanel dataPanel;
    private JPanel buttonsPanel;
    private JButton buttonDelete;
    private JButton buttonExit;
    private JTextField textFieldID;
    private JButton buttonSearch;
    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldBirth;
    private JTextField textFieldGender;
    private JTextField textFieldBirthDate;


    public JDialDelMonitor(){
        setContentPane(mainPanelDel);
        setModal(true);
        setTitle("Delete Activities");
        buttonExit.addActionListener(l ->dispose());
        buttonSearch.addActionListener(listenerSearch());
        buttonDelete.addActionListener(listenerDelete());
    }

    private ActionListener listenerDelete() {
        return l -> {
            Object[] options = new Object[2];
            options[0] = "YES";
            options[1] = "NO";
            // Dialogo de confirmacion para que el usuario decida si quiere borrarlo o no.
            int resp = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the Monitor?", "Eliminar."
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null
                    , options, null);
            // Si pulsa si, intentamos eliminarlo, en caso de que no exista saldra un mensaje diciendo que no existe.
            /*
            if (resp == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(textFieldID.getText());
                if (DBManager.deleteMonitor(id)) {
                    JOptionPane.showMessageDialog(null, "Monitor successfully deleted", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong, impossible to delete the Monitor"
                            , "Error deleting", JOptionPane.ERROR_MESSAGE);
                }
            }

             */
        };
    }

    private ActionListener listenerSearch() {
        return l->{
            try {
                int id = Integer.parseInt(textFieldID.getText());
                Monitor monitor = null;
                //Monitor monitor = DBManager.getMonitor(id);
                if (monitor != null) {
                    poblateTextFields(monitor);
                }else {
                    JOptionPane.showMessageDialog(null,"There is not a Monitor matching that id"
                            ,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Introduce a numeric ID"
                        ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        };
    }
    private void poblateTextFields(Monitor monitor) {
        textFieldName.setText(monitor.getName());
        textFieldLastName.setText(monitor.getLastName());
        textFieldBirth.setText(monitor.getBirthDate().toString());
        textFieldGender.setText(monitor.getGender());
        dataPanel.setVisible(true);
        textFieldID.setEditable(false);
        this.setSize(400,600);
        buttonDelete.setEnabled(true);
    }
}

