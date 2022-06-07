package es.iesmz.dam.pro;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDialUpdtMonitors extends JDialog {
    private JPanel mainPanelUpdt;
    private JPanel searchPanel;
    private JPanel dataPanel;
    private JPanel buttonsPanel;
    private JButton buttonUpdate;
    private JButton buttonExit;
    private JTextField textFieldID;
    private JButton buttonSearch;
    private JTextField textFieldName;
    private JTextField textFieldLastName;
    private JTextField textFieldBirthDate;
    private JRadioButton radioButtonFemale;
    private JRadioButton radioButtonMale;

    public JDialUpdtMonitors(){
        setContentPane(mainPanelUpdt);
        setModal(true);
        setTitle("Update Monitors");
        ButtonGroup genderButtons = new ButtonGroup();
        genderButtons.add(radioButtonFemale);
        genderButtons.add(radioButtonMale);
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
            int resp = JOptionPane.showOptionDialog(null, "Are you sure you want to update the Monitor?", "Update."
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null
                    , options, null);
            // Si pulsa si, intentamos actualizarlo
            if (resp == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(textFieldID.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDate birth =LocalDate.parse(textFieldBirthDate.getText(),formatter);
                String gender = radioButtonFemale.isSelected()?"F":"M";
                String name = textFieldName.getText();
                String lastName = textFieldLastName.getText();
                Monitor monitor = new Monitor(id,name,lastName, birth, gender);

                if (DBManager.updateMonitor(monitor)) {
                    JOptionPane.showMessageDialog(null, "Monitor successfully updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong, impossible to update the Monitor"
                            , "Error updating", JOptionPane.ERROR_MESSAGE);
                }


            }
        };
    }

    private ActionListener listenerSearch() {
        return l->{
            try {
                int id = Integer.parseInt(textFieldID.getText());;
                Monitor monitor = DBManager.getMonitor(id);
                if (monitor != null) {
                    poblateTextFields(monitor);
                }else {
                    JOptionPane.showMessageDialog(null,"There is not a monitor matching that id"
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
        textFieldBirthDate.setText(monitor.getBirthDate().toString());
        String gender = monitor.getGender();
        if (gender.matches("F")){
            radioButtonFemale.setSelected(true);
        }
        if (gender.matches("M")){
            radioButtonMale.setSelected(true);
        }
        dataPanel.setVisible(true);
        textFieldID.setEditable(false);
        this.setSize(400,600);
        buttonUpdate.setEnabled(true);
    }
}
