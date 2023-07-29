package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {
    //管理数据加载图片的时候使用
    int[][] data = new int[4][4];
    //二维数组中“0”的位置
    int x = 0;
    int y = 0;

    //空参构造方法
    public GameJFrame() {

        //初始化界面
        initJFrame();

        //初始化菜单
        JMenuBar();

        //初始化数据(打乱)
        initData();
        //初始化图片
        initImage();
        //显示界面
        setVisible(true);
    }

    //初始化数据(打乱)
    private void initData() {
        int[] tmpArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tmpArray.length; i++) {
            int index = r.nextInt(16);
            int tmp = tmpArray[index];
            tmpArray[index] = tmpArray[i];
            tmpArray[i] = tmp;
        }
        //添加图片
        int index = 0;
        for (int i = 0; i < 16; i++) {
            if (tmpArray[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tmpArray[index++];
        }
    }

    //初始化图片
    private void initImage() {
        this.getContentPane().removeAll();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个图片ImageIcon的对象image/girl/girl1/1.jpg
                ImageIcon icon = new ImageIcon("image/animal/animal3/" + data[i][j] + ".jpg");
                //创建一个JLabel的对象(管理容器)
                JLabel jLabel = new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 143, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //添加图片
                getContentPane().add(jLabel);

            }
        }
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);

        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }

    //初始化菜单
    private void JMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的两个初始选项(功能 关于我们)
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");

        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("付款码");

        //把每个选项下的小条目关联到选项中
        functionJmenu.add(replayItem);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(accountItem);

        //将菜单里面的两个选项添加到菜单中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);

        //给整个界面设置菜单
        setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initJFrame() {
        //设置界面宽高
        setSize(603, 680);
        //设置标题
        setTitle("拼图单机版v1.0");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中
        setLayout(null);
        //添加键盘绑定事件
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //左： 37 上38右39 下40
        int code = e.getKeyCode();
        if (code == 37) {
            System.out.println("向左");
        } else if (code == 38) {
            System.out.println("向上");
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            initImage();
        } else if (code == 39) {
            System.out.println("向右");
        } else if (code == 40) {
            System.out.println("向下");
        }
    }
}
