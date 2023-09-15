package ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Domain.User;
import cn.hutool.core.io.FileUtil;
import util.CodeUtil;

public class LoginJFrame extends JFrame implements MouseListener {
    ArrayList<User> allUsers = new ArrayList<>();

    //创建登陆和注册按钮
    JButton login = new JButton();
    JButton register = new JButton();
    //用户名 密码和验证码输入框
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    //显示验证码
    JLabel rightCode = new JLabel();

    public LoginJFrame() {
        //读取用户信息并添加到集合中
        readUserInfo();
        initFrame();
        initView();
        //显示界面
        setVisible(true);
    }

    private void readUserInfo() {
        List<String> list = FileUtil.readLines("C:\\Users\\xietian\\Desktop\\Java_jigsawgame\\userInfo.txt", "UTF-8");
        for (String s : list) {
            String rightUsername = s.split("&")[0].split("=")[1];
            String rightPassword = s.split("&")[1].split("=")[1];
            User user = new User(rightUsername, rightPassword);
            allUsers.add(user);
        }
    }

    private void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);


        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);


        String codeStr = CodeUtil.getCode();
        //设置内容
        rightCode.setText(codeStr);
        //绑定鼠标事件
        rightCode.addMouseListener(this);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        //给登录按钮绑定鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    private void initFrame() {
        //设置界面宽高
        setSize(488, 430);
        //设置标题
        setTitle("拼图登录");
        //设置界面置顶
        setAlwaysOnTop(true);
        //设置界面居中
        setLocationRelativeTo(null);
        //设置关闭模式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消内部默认布局
        setLayout(null);
    }
    private boolean contains(User userInput) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (userInput.getUsername().equals(allUsers.get(i).getUsername()) && userInput.getPassword().equals(allUsers.get(i).getPassword())) return true;
        }
        return false;
    }
    private void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       //包括三方面 登陆注册和更换验证码
        //登陆
        if (e.getSource() == login) {
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String codeInput = code.getText();
            User userInput = new User(usernameInput, passwordInput );
            if (codeInput.length() == 0) {
                showJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");
            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码输入错误");
            } else if (contains(userInput)) {
                showJDialog("登陆成功");
                //关闭当前登录界面
                this.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                try {
                    new GameJFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                showJDialog("用户名或密码错误");
            }
        } else if (e.getSource() == register) {
            this.setVisible(false);
            new RegisterJFrame(allUsers);
        } else if (e.getSource() == rightCode ) {
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按下.png"));
        } else if (e.getSource() == register) {
                register.setIcon((new ImageIcon(":image/login/注册按下.png")));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
