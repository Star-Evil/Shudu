import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class ShuD extends JFrame
{
    private static final long serialVersionUID = 5952689219411916553L; //序列化字段
    private static JTextField a[][] = new JTextField[9][9];  //存储文本框中的数字
    static int ans[][] = new int[9][9];  //存储输入后的两位数组
    SudokuPuzzleGenerator example = new SudokuPuzzleGenerator();
    public int right[][] = example.generatePuzzleMatrix();
    public int rightans[][];
    clearing cl = new clearing();

    public ShuD() {

        try {
            File file = new File("shudu.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            PrintStream printStream = new PrintStream(fileOutputStream);
            Container c = getContentPane();
            c.setLayout(new BorderLayout(2, 1));  //边框布局
            JMenuItem jmiExplain = new JMenuItem("详情");
            JMenuItem jmiOk = new JMenuItem("提交");  //定义菜单
            JMenuItem jmiC = new JMenuItem("答案");
            JMenuItem jmiN = new JMenuItem("下一局");
            JMenuItem jmiMessage = new JMenuItem("信息");

            JPanel panel = new JPanel();  //定义一个容器
            panel.add(jmiOk);     //将菜单在容器内显示
            panel.add(jmiC);
            panel.add(jmiN);
            panel.add(jmiExplain);
            panel.add(jmiMessage);

            JPanel p1 = new JPanel(new GridLayout(9, 9, 5, 5));  //定义9行9列的网格布局
            add(panel, BorderLayout.NORTH);   //将菜单放置在北面
            add(p1, BorderLayout.CENTER);   //将数字放置在正中间
            rightans = cl.clear(right);

            for (int k = 0; k < 9; k++)
            {
                for (int n = 0; n < 9; n++)
                {
                    int ccc = rightans[k][n];
                    if (rightans[k][n] != 0)
                    {
                        a[k][n] = new JTextField("" + rightans[k][n]);
                        a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                        a[k][n].setEditable(false);   //只可显示不可修改
                        p1.add(a[k][n]);     //添加文本框
                        printStream.print(ccc + "\t");
                    } else {
                        a[k][n] = new JTextField();
                        a[k][n].setHorizontalAlignment(JTextField.CENTER);
                        p1.add(a[k][n]);
                        printStream.print("");
                    }
                }
                printStream.println();
            }
            printStream.close();
            add(p1);   //将数字面板显示在容器里}

            jmiOk.addActionListener(new ActionListener()
            {//匿名创建事件监听器
                public void actionPerformed(ActionEvent e)
                {
                    if (gettext() == 1)
                    {
                        if (judge() == true)
                        {
                            JOptionPane.showMessageDialog(null, "Your answer is right!", "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Your answer is wrong!", "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            });
            jmiC.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    for (int k = 0; k < 9; k++)
                    {
                        for (int n = 0; n < 9; n++)
                        {
                            a[k][n].setText(String.valueOf(right[k][n]));
                        }
                    }


                }
            });
            explainListener listener = new explainListener();
            jmiExplain.addActionListener(listener);
            messageListener listener1 = new messageListener();
            jmiMessage.addActionListener(listener1);

            jmiN.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {


                    right = example.generatePuzzleMatrix();
                    rightans = cl.clear(right);

                    for (int k = 0; k < 9; k++)
                    {
                        for (int n = 0; n < 9; n++)
                        {
                            int ccc = rightans[k][n];
                            if (rightans[k][n] != 0)
                            {
                                a[k][n].setEditable(true);
                                a[k][n].setText(String.valueOf(rightans[k][n]));
                                a[k][n].setHorizontalAlignment(JTextField.CENTER);//将数字水平居中
                                a[k][n].setEditable(false);   //只可显示不可修改
                                //p1.add(a[k][n]);     //添加文本框
                                printStream.print(ccc + "\t");
                            } else {
                                a[k][n].setEditable(true);
                                a[k][n].setText("");
                                a[k][n].setHorizontalAlignment(JTextField.CENTER);
                                //p1.add(a[k][n]);
                                printStream.print("");
                            }
                        }
                        printStream.println();
                    }
                    printStream.close();
                    add(p1);   //将数字面板显示在容器里}
                }
            });


        } catch (IOException e)
        {

        }
    }
    //获取文本框的文字
    public int gettext()
    {
        int i, j;
        for (i = 0; i < 9; i++)
        {
            for (j = 0; j < 9; j++)
            {
                ans[i][j] = 0;
            }
        }
        for (int k = 0; k < 9; k++)
        {
            for (int n = 0; n < 9; n++)
            {
                //异常处理
                try {
                    //将答案类型转换之后传给ans
                    ans[k][n] = Integer.parseInt(a[k][n].getText());
                    int num = ans[k][n];
                } catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(null, " 输入不符合要求，重新输入");
                    return 0;
                }
            }
        }
        return 1;
    }
    //判断输入的答案是否正确
    public static boolean judge()
    {
        int i, j, k;
        int[][] answer = ans;
        judging g = new judging();
        for (i = 0; i < 9; i++)
        {
            //判断每列是否有重复数字
            if (g.judge9(answer[i]) == false)
                return false;
        }
        //判断每行是否有重复数字
        for (j = 0; j < 9; j++)
        {
            int[] newAnswerColumn = new int[9];
            for (i = 0; i < 9; i++)
            {
                newAnswerColumn[i] = answer[i][j];
            }
            if (g.judge9(newAnswerColumn) == false)
                return false;
        }
        //判断每个小九宫格内是否有重复数字
        for (i = 0; i < 3; i++)
        {
            for (j = 0; j < 3; j++)
            {
                k = 0;
                int[] newAnswer = new int[9];
                for (int m = i * 3; m < i * 3 + 3; m++)
                {
                    for (int n = j * 3; n < j * 3 + 3; n++)
                    {
                        newAnswer[k] = answer[m][n];
                        k++;
                    }
                }
                if (g.judge9(newAnswer) == false)
                {
                    return false;
                }
            }
        }
        return true;
    }
}