package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //管理数据加载图片的时候使用
    int[][] data = new int[4][4];
    //定义胜利数组
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //二维数组中“0”的位置
    int x = 0;
    int y = 0;
    //定义走的总步数
    int count = 0;
    String path = "image/girl/girl3";

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("推广码");

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
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数:" + count);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个图片ImageIcon的对象image/girl/girl1/1.jpg
                ImageIcon icon = new ImageIcon(path + "/" + data[i][j] + ".jpg");
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

        //把每个选项下的小条目关联到选项中
        functionJmenu.add(replayItem);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(accountItem);

        //关联鼠标器
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
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

    //按下不松时会调用该方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //删除界面中的所有图片
            this.getContentPane().removeAll();
            //显示完整图片
            JLabel jLabel = new JLabel(new ImageIcon(path + "/all.jpg"));
            jLabel.setBounds(83, 134, 420, 420);
            jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            //添加图片
            this.getContentPane().add(jLabel);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);

            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利 如果胜利 直接结束
        if (victory()) {
            return;
        }
        //左： 37 上38右39 下40
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 3) return;
            System.out.println("向左");
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            count++;
            initImage();
        } else if (code == 38) {
            if (x == 3) return;
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            count++;
            initImage();
        } else if (code == 39) {
            if (y == 0) return;
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            count++;
            initImage();
        } else if (code == 40) {
            if (x == 0) return;
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            count++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            //重新开始游戏
            //计数器清零
            count = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (source == reLoginItem) {
            //退回登陆界面咯
            //先隐藏当前界面
            this.setVisible(false);
            //新建界面
            new LoginJFrame();
        } else if (source == closeItem) {
            System.exit(0);

        }
    }
}
