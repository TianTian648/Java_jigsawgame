package DemoTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Test3 {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        //设置界面宽高
        jframe.setSize(603,680);
        //设置标题
        jframe.setTitle("事件演示");
        //设置界面置顶
        jframe.setAlwaysOnTop(true);
        //设置界面居中
        jframe.setLocationRelativeTo(null);
        //设置关闭模式
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置界面居中
        jframe.setLayout(null);
        //创建按钮对象
        JButton jtb = new JButton("点我啊");
        //设置位置和宽高
        jtb.setBounds(0,0,100,50);
        //给按钮添加动作监听

//        jtb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("我被点击了");
//            }
//        });
        jtb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Released");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("Exited");
            }
        });
        jframe.getContentPane().add(jtb);
        //显示界面
        jframe.setVisible(true);
    }
}
