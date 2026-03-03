package S1115.V3;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileUI {

    // 窗口对象
    JFrame jf;
    String dir = "D:\\Test\\";
    // 监听器对象
    FileListener fl = new FileListener();

    public FileUI() {

        initUI(dir);// 创建对象时就会调用
    }

    public void initUI(String path) {
        jf = new JFrame("文件管理器:" + path);
        jf.setSize(500, 500);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // 左对齐，组件间距20
        jf.setLocationRelativeTo(null); // 窗口在屏幕居中显示
        // 关闭窗口时终止应用程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 遍历根目录
        File file = new File(path);
        File[] files = file.listFiles();
        Dimension dim = new Dimension(80, 50);
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
//            System.out.println(f.getName());
            if (f.isHidden())
                continue;
            if (f.isDirectory()) {
                JButton btn = new JButton(f.getName());//设置的按钮上直接显示文本
                btn.setActionCommand(f.getAbsolutePath());// 设置为绝对路径
                btn.setPreferredSize(dim);
                btn.setBackground(Color.ORANGE);
                jf.add(btn);
                btn.setToolTipText(f.getName());// 显示不全的提示文本
                // 添加监听器
                btn.addActionListener(fl);
            } else if (f.isFile()) {
                JLabel jla = new JLabel(f.getName());
                jla.setPreferredSize(dim);
                jla.setToolTipText(f.getAbsolutePath());
                jla.setOpaque(true);
                jla.setBackground(Color.LIGHT_GRAY);
                jf.add(jla);
                jla.addMouseListener(fl);
            }
        }
        jf.setVisible(true);
        jf.addMouseListener(fl);
        fl.setFileUI(this);// 设置监听器类中的FileUI 属性为 this(在本类中指代创建的对象)
    }

    //程序入口
    public static void main(String[] args) {
        // FileUI fileui = new FileUI();
        // fileui.initUI();
        new FileUI();
    }
}