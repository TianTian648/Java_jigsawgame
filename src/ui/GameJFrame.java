package ui;

import Domain.GameInfo;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;
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

    JMenu saveJMenu = new JMenu("存档");
    JMenu loadJMenu = new JMenu("读档");

    JMenuItem saveItem0 = new JMenuItem("存档0(空)");
    JMenuItem saveItem1 = new JMenuItem("存档1(空)");
    JMenuItem saveItem2 = new JMenuItem("存档2(空)");
    JMenuItem saveItem3 = new JMenuItem("存档3(空)");
    JMenuItem saveItem4 = new JMenuItem("存档4(空)");

    JMenuItem loadItem0 = new JMenuItem("读档0(空)");
    JMenuItem loadItem1 = new JMenuItem("读档1(空)");
    JMenuItem loadItem2 = new JMenuItem("读档2(空)");
    JMenuItem loadItem3 = new JMenuItem("读档3(空)");
    JMenuItem loadItem4 = new JMenuItem("读档4(空)");


    JMenuItem accountItem = new JMenuItem("推广码");

    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    //空参构造方法
    public GameJFrame() throws IOException, ClassNotFoundException {

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
    private void JMenuBar() throws IOException, ClassNotFoundException {

        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的两个初始选项(功能 关于我们)和一个子功能 更换图片
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");

        //更换图片功能
        JMenu changeImage = new JMenu("更换图片");

        //把5个存档，添加到saveJMenu中
        saveJMenu.add(saveItem0);
        saveJMenu.add(saveItem1);
        saveJMenu.add(saveItem2);
        saveJMenu.add(saveItem3);
        saveJMenu.add(saveItem4);

        //把5个读档，添加到loadJMenu中
        loadJMenu.add(loadItem0);
        loadJMenu.add(loadItem1);
        loadJMenu.add(loadItem2);
        loadJMenu.add(loadItem3);
        loadJMenu.add(loadItem4);
        //把每个选项下的小条目关联到选项中
        functionJmenu.add(changeImage);
        functionJmenu.add(replayItem);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);
        functionJmenu.add(saveJMenu);
        functionJmenu.add(loadJMenu);

        aboutJmenu.add(accountItem);

        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);
        //关联鼠标器
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        saveItem0.addActionListener(this);
        saveItem1.addActionListener(this);
        saveItem2.addActionListener(this);
        saveItem3.addActionListener(this);
        saveItem4.addActionListener(this);
        loadItem0.addActionListener(this);
        loadItem1.addActionListener(this);
        loadItem2.addActionListener(this);
        loadItem3.addActionListener(this);
        loadItem4.addActionListener(this);
        //关联ActionListener
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);
        //将菜单里面的两个选项添加到菜单中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);

        getNameInfo();
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
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
        Random r = new Random();
        int index = 0;
        Object source = e.getSource();
        if (source == replayItem) {
            //重新开始游戏
            //计数器清零
            remake();
        } else if (source == reLoginItem) {
            //退回登陆界面咯
            //先隐藏当前界面
            this.setVisible(false);
            //新建界面
            new LoginJFrame();
        } else if (source == closeItem) {
            System.exit(0);
        } else if (source == accountItem) {
            Properties properties = new Properties();
            File file = new File("game.properties");
            try {
                FileReader fr = new FileReader(file);
                properties.load(fr);
                fr.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String account = (String) properties.get("account");

            //新建对象
            JDialog jDialog = new JDialog();
            //新建容器对象
            JLabel jLabel = new JLabel(new ImageIcon(account));
            //设置容器大小和位置
            jLabel.setBounds(0, 0, 258, 258);
            //添加label
            jDialog.getContentPane().add(jLabel);
            //设置插件大小
            jDialog.setSize(344, 344);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //让弹框不关闭就无法操作
            jDialog.setModal(true);
            //显示弹框
            jDialog.setVisible(true);
        } else if (source == girl) {
            //girl 13选1
            // 照片的序号
            index = r.nextInt(13) + 1;
            //更改path
            path = "image/girl/girl" + index;
            remake();
        } else if (source == animal) {
            // animal 8选1
            // 照片的序号
            index = r.nextInt(8) + 1;
            //更改path
            path = "image/animal/animal" + index;
            //重新开始游戏
            //计数器清零
            remake();
        } else if (source == sport) {
            //sport 10选1
            // 照片的序号
            index = r.nextInt(10) + 1;
            //更改path
            path = "image/sport/sport" + index;
            //重新开始游戏
            //计数器清零
            remake();
        } else if (source == saveItem0 || source == saveItem1 || source == saveItem2 || source == saveItem3 || source == saveItem4) {
            JMenuItem item = (JMenuItem) source;
            //System.out.println(item.getText());
            String s = item.getText();
            int x = Integer.parseInt(String.valueOf(s.charAt(2)));
            String str = "存档" + x + "(" + count + ")";
            item.setText(str);
            loadJMenu.getItem(x).setText("读档" + x + "(" + count + ")");
            GameInfo gameInfo = new GameInfo(data, x, y, count, path);
            try {
                save(gameInfo, x);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (source == loadItem0 || source == loadItem1 || source == loadItem2 || source == loadItem3 || source == loadItem4) {
            JMenuItem item = (JMenuItem) source;
            String s = item.getText();
            int x = Integer.parseInt(String.valueOf(s.charAt(2)));
            GameInfo gameInfo;
            try {
                gameInfo = load(x);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            this.path = gameInfo.getPath();
            this.count = gameInfo.getCount();
            this.x = gameInfo.getX();
            this.y = gameInfo.getY();
            this.data = gameInfo.getData();
            initImage();
        }
    }

    private static GameInfo load(int x) throws IOException, ClassNotFoundException {
        File file = new File("save/" + x + ".data");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        GameInfo gameInfo = (GameInfo) ois.readObject();
        ois.close();
        return gameInfo;
    }

    private void save(GameInfo gameInfo, int x) throws IOException {
        File file = new File("save/" + x + ".data");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(gameInfo);
        oos.close();
    }

    private void remake() {
        //重新开始游戏
        //计数器清零
        count = 0;
        //重新打乱数据
        initData();
        //重新加载图片
        initImage();
    }

    //获取存档信息
    private void getNameInfo() throws IOException, ClassNotFoundException {
        File file = new File("save");
        File[] files = file.listFiles();
        for (File file1 : files) {
            int i = Integer.parseInt(file1.getName().split("\\.")[0]);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
            GameInfo gameInfo = (GameInfo) ois.readObject();
            int count = gameInfo.getCount();
            loadJMenu.getItem(i).setText("读档" + count + "(" + count + ")");
            saveJMenu.getItem(i).setText("存档" + count + "(" + count + ")");
        }
    }
}
