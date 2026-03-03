package S1115.V3;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileSystemUI {
    // 窗口对象
    JFrame jf;
    String dir = "D:\\Test\\";
    // 监听器对象
    FileListener1 fl = new FileListener1();

    // 构造方法：创建对象时直接初始化界面，默认加载D盘根目录
    public FileSystemUI() {
        initUI(dir);
    }

    // 界面初始化方法：加载对应目录的文件
    public void initUI(String path) {
        // 创建窗口，设置标题（显示当前目录路径）、大小、居中
        jf = new JFrame("文件管理系统"+path );
        jf.setSize(700, 600);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20)); // 左对齐，组件间距20
        jf.setLocationRelativeTo(null); // 窗口在屏幕居中显示
        // 关闭窗口时终止应用程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // 读取指定路径下的所有文件和文件夹
        File file = new File(path);
        File[] files = file.listFiles();

        // 设置组件大小（文件夹按钮和文件标签统一大小）
        Dimension dim = new Dimension(80, 50);

        // 遍历所有文件/文件夹，分别显示为按钮或标签
        if (files != null) {
            for (File f : files) {
                // 跳过隐藏文件
                if (f.isHidden())
                    continue;
    
                // 如果是文件夹：显示为橙色按钮
                if (f.isDirectory()) {
                    JButton btn = new JButton(f.getName());//设置的按钮上直接显示文本
                    btn.setActionCommand(f.getAbsolutePath());// 设置为绝对路径
                    btn.setPreferredSize(dim);
                    btn.setBackground(Color.ORANGE);
                    // 给按钮添加点击监听器
                    btn.addActionListener(fl);
                    jf.add(btn);
                    btn.setToolTipText(f.getName());// 显示不全的提示文本
                }
                // 如果是文件：显示为灰色标签
                else if (f.isFile()) {
                    JLabel jla = new JLabel(f.getName());
                    //  tooltip显示文件绝对路径
                    jla.setToolTipText(f.getAbsolutePath());
                    jla.setPreferredSize(dim);
                    jla.setOpaque(true);
                    jla.setBackground(Color.LIGHT_GRAY);
                    // 给文件标签添加鼠标监听器
                    jla.addMouseListener(fl);
                    jf.add(jla);
                }
            }
        }

        // 给窗口添加右键菜单监听器
        jf.addMouseListener(fl);
        // 关键：把当前界面对象传给监听器，让监听器能操作窗口
        fl.setFileSystemUI(this);
        // 窗口显示
        jf.setVisible(true);
    }

    // 程序入口：运行就启动文件管理器
    public static void main(String[] args) {

        new FileSystemUI();
    }
}
