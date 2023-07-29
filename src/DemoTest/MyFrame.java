package DemoTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener, MouseListener, KeyListener {
    JButton jbt1 = new JButton("点我啊");
    JButton jbt2 = new JButton("再点我啊");
    JButton jbt3 = new JButton("鼠标点击事件触发");

    public MyFrame() {
        //设置界面的宽高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //事件绑定
       //jbt1.addActionListener(this);
       // jbt2.addActionListener(this);
        //jbt3.addMouseListener(this);
        this.addKeyListener(this);
        //设置位置和宽高

        //jbt1.setBounds(0, 0, 100, 50);
        //jbt2.setBounds(200, 0, 100, 50);
        //jbt3.setBounds(300, 0, 100, 50);
        //this.getContentPane().add(jbt1);
        //this.getContentPane().add(jbt2);
        //this.getContentPane().add(jbt3);

        //显示界面
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random r = new Random();
        //获取当前被操作的按钮对象
        Object source = e.getSource();
        if (source == jbt1) {
            jbt1.setSize(r.nextInt(200), r.nextInt(200));
        } else if (source == jbt2) {
            jbt2.setLocation(r.nextInt(200), r.nextInt(200));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("单击");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("鼠标按压不松");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("松开");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("划入");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("划出");
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下按键");

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("松开按键");
        int code = e.getKeyCode();
        if (code == 65) {
            System.out.println("现在按下的是A");
        }
    }
}
