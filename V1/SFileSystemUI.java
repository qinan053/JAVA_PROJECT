package S1115.V1;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//public class SFileSystemUI {
//
//    ImageIcon iconDir = new ImageIcon("files/dir.png");
//    ImageIcon iconFile = new ImageIcon("files/file.png");
//    JFrame jf;
////    FileListener f1 = new FileListener;
//    public void showUI(){
//        jf = new JFrame("文件管理系统");
//        jf.setSize(500,500);
//        jf.setLayout((new FlowLayout()));
//
//        //遍历目录
//        File dir = new File("D:\\Test");
//        File[] files = dir.listFiles();
//        for(int i = 0;i < files.length;i++){
//            File file2 = files[i];
//            if(file2.isFile()){
//                JLabel fileJla = new JLabel(file2.getName());
//
//                jf.add(fileJla);
//                fileJla.setPreferredSize((new Dimension(100,50)));
//            }else if(file2.isDirectory()){
//                JButton btn = new JButton(file2.getName());
//
//                jf.add(btn);
//                btn.setPreferredSize(new Dimension(100,50));
//            }
//        }
//
//        jf.setVisible(true);
//
////        jf.addMouseListener(f1);
////        f1.fui = this;
//    }
//
//    public static void main(String[] args) {
//        SFileSystemUI fileSystemUI = new SFileSystemUI();
//        fileSystemUI.showUI();
//    }
//}
public class SFileSystemUI {

    ImageIcon iconDir = new ImageIcon("files/dir.png");
    ImageIcon iconFile = new ImageIcon("S1115/V1/file.png");
    JFrame jf;

    // 显示界面方法
    public void showUI() {
        // 创建窗口并设置基础属性
        jf = new JFrame("文件管理系统V1.0");
        jf.setSize(500, 500);          // 窗口大小（宽500，高500）
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20)); // 左对齐，组件间距20px
        jf.setLocationRelativeTo(null); // 窗口在屏幕居中显示
        // 关闭窗口时终止应用程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 定位要遍历的目录（建议用D盘，避免C盘权限问题）
        File Dir = new File("D:\\Test");
        // 获取目录下所有文件/文件夹（返回File数组）
        File[] files = Dir.listFiles();

        // 循环遍历，区分文件和文件夹并展示（关键逻辑）
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // 是文件：用JLabel显示（仅展示名称）
                    JLabel fileJla = new JLabel(file.getName());
                    fileJla.setPreferredSize(new Dimension(100, 50)); // 固定组件大小（150x40）
                    fileJla.setIcon(iconFile);
                    jf.add(fileJla);
                } else if (file.isDirectory()) {
                    // 是文件夹：用JButton显示（可点击）
                    JButton dirbtn = new JButton(file.getName());
                    dirbtn.setPreferredSize(new Dimension(100, 50)); // 固定组件大小
                    jf.add(dirbtn);
                }
            }
        }

        // 显示窗口
        jf.setVisible(true);
    }

    // 主函数：程序入口
    public static void main(String[] args) {
        SFileSystemUI fileSystem = new SFileSystemUI();
        fileSystem.showUI(); // 调用界面展示方法
    }
}