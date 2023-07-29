package ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        //设置界面宽高
        setSize(488,500);
        //设置标题
        setTitle("拼图注册");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //显示界面
        setVisible(true);
    }
}
