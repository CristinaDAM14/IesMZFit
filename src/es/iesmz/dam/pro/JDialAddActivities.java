package es.iesmz.dam.pro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JDialAddActivities extends JDialog {
    private JPanel addPanel;
    private JButton buttonExit;
    private JTextField textFieldName;
    private JButton buttonSave;
    private JButton buttonClean;
    private JTextField textFieldDuration;
    private JTextField textFieldSchedule;
    private JTextField textFieldTurn;
    private JTextField textFieldCalories;
    private JTextField textFieldCapacity;
    private JTextField textFieldDifficulty;


    public JDialAddActivities() {
        setContentPane(addPanel);
        setModal(true);
        setTitle("Add Activities");
        buttonExit.addActionListener(l -> dispose());
        buttonClean.addActionListener(listenerClean());
        buttonSave.addActionListener(listenerSave());

    }

    private ActionListener listenerSave() {
        return l ->{
            int duration = Integer.parseInt(textFieldDuration.getText());
            int turn = Integer.parseInt(textFieldTurn.getText());
            int calories = Integer.parseInt(textFieldCalories.getText());
            int capacity = Integer.parseInt(textFieldCapacity.getText());
            String schedule = textFieldSchedule.getText();
            String difficulty = textFieldDifficulty.getText();
            String name = textFieldName.getName();
            Activity activity = new Activity(name,duration,schedule,turn,calories,capacity,difficulty);
            if(DBManager.addActivity(activity)){
                JOptionPane.showMessageDialog(null,"The activity has been added correctly"
                        ,"Adding Activities",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(null,"Something went wrong, impossible to add activity"
                        ,"Error",JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private ActionListener listenerClean() {
        return l -> {
            textFieldCalories.setText("");
            textFieldCapacity.setText("");
            textFieldDifficulty.setText("");
            textFieldDuration.setText("");
            textFieldName.setText("");
            textFieldSchedule.setText("");
            textFieldTurn.setText("");
        };
    }
}
