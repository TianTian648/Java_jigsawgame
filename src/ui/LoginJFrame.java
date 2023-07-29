package ui;

import javax.swing.*;


public class LoginJFrame extends JFrame {
    public LoginJFrame() {
        //设置界面宽高
        setSize(488,430);
        //设置标题
        setTitle("拼图登录");
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
