package S1115.V2;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SFileSystemUI {

    JFrame jf;
    FileListener fl = new FileListener();

    public void showUI() {
        jf= new JFrame("文件管理系统");
        jf.setSize(500, 500);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // 左对齐，组件间距20
        jf.setLocationRelativeTo(null); // 窗口在屏幕居中显示
        // 关闭窗口时终止应用程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 遍历目录
        File dir = new File("D:\\Test\\");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // 是文件：用JLabel显示（仅展示名称）
                    JLabel fileJla = new JLabel(file.getName());
                    fileJla.setPreferredSize(new Dimension(100, 50)); // 固定组件大小（150x40）
                    jf.add(fileJla);
                } else if (file.isDirectory()) {
                    // 是文件夹：用JButton显示（可点击）
                    JButton dirbtn = new JButton(file.getName());
                    dirbtn.setPreferredSize(new Dimension(100, 50)); // 固定组件大小
                    jf.add(dirbtn);
                }
            }
        }
        jf.setVisible(true);
        jf.addMouseListener(fl);
        fl.fui = this;
    }

    public static void main(String[] args) {
        SFileSystemUI fileSystemUI = new SFileSystemUI();
        fileSystemUI.showUI();
    }
}