import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class explainListener implements ActionListener { //事件监听器
    public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null, "填入数字保证每行每列及每个小的九宫格内数字无重复","Explain",JOptionPane.INFORMATION_MESSAGE);
    }
}