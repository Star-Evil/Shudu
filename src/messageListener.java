import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class messageListener implements ActionListener {


    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "made by xxx", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}