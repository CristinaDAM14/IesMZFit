package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialUpdtActivities extends JDialog {
    private JPanel mainPanelUpdt;
    private JPanel searchPanel;
    private JPanel dataPanel;
    private JPanel buttonsPanel;
    private JButton buttonUpdate;
    private JButton buttonExit;
    private JTextField textFieldID;
    private JButton buttonSearch;
    private JTextField textFieldName;
    private JTextField textFieldDuration;
    private JTextField textFieldSchedule;
    private JTextField textFieldTurn;
    private JTextField textFieldCalories;
    private JTextField textFieldCapacity;
    private JTextField textFieldDifficulty;

    public JDialUpdtActivities(){
        setContentPane(mainPanelUpdt);
        setModal(true);
        setTitle("Update Activities");
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
            int resp = JOptionPane.showOptionDialog(null, "Are you sure you want to update the Activity?", "Eliminar."
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null
                    , options, null);
            // Si pulsa si, intentamos actualizarlo
            if (resp == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(textFieldID.getText());
                int duration = Integer.parseInt(textFieldDuration.getText());
                int turn = Integer.parseInt(textFieldTurn.getText());
                int calories = Integer.parseInt(textFieldCalories.getText());
                int capacity = Integer.parseInt(textFieldCapacity.getText());
                String name = textFieldName.getText();
                String schedule = textFieldSchedule.getText();
                String difficulty =textFieldDifficulty.getText();
                Activity activity = new Activity(id,name,duration,schedule,turn,calories,capacity,difficulty);
                if (DBManager.updateActivity(activity)) {
                    JOptionPane.showMessageDialog(null, "Activity successfully updated", "Updated", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Something went wrong, impossible to update the activity"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener listenerSearch() {
        return l->{
            try {
                int id = Integer.parseInt(textFieldID.getText());
                Activity activity = DBManager.getActivity(id);
                if (activity != null) {
                    poblateTextFields(activity);
                }else {
                    JOptionPane.showMessageDialog(null,"There is not an activity matching that id"
                            ,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Introduce a numeric ID"
                        ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        };
    }
    private void poblateTextFields(Activity activity) {
        textFieldName.setText(activity.getName());
        textFieldDuration.setText(String.valueOf(activity.getDuration()));
        textFieldSchedule.setText(activity.getSchedule());
        textFieldDifficulty.setText(activity.getDifficulty());
        textFieldTurn.setText(String.valueOf(activity.getTurn()));
        textFieldCalories.setText(String.valueOf(activity.getCalories()));
        textFieldCapacity.setText(String.valueOf(activity.getCapacity()));
        dataPanel.setVisible(true);
        textFieldID.setEditable(false);
        this.setSize(400,600);
        buttonUpdate.setEnabled(true);
    }
}
