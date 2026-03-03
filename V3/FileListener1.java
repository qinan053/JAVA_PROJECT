package S1115.V3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FileListener1 extends MouseAdapter implements ActionListener, MouseListener{

    // 关联界面类，操作窗口
    FileSystemUI fileSystemUI;

    // 给监听器设置界面对象
    public void setFileSystemUI(FileSystemUI fileSystemUI) {
        this.fileSystemUI = fileSystemUI;
    }

    // 处理按钮/菜单点击事件
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取点击的组件
        Object source = e.getSource();

        // 如果点击的是文件夹按钮
        if (source instanceof JButton) {
            System.out.println("点击了文件夹按钮");
            // 获取按钮绑定的绝对路径
            String path = e.getActionCommand();
            // 关闭当前窗口，重新初始化界面显示目标文件夹内容
            fileSystemUI.jf.dispose();
            fileSystemUI.initUI(path);
        }

        // 保留V2.0的右键菜单逻辑（创建文件/文件夹、刷新）
        else if (source instanceof JMenuItem) {
            String menu = e.getActionCommand();
            // 刷新功能：关闭当前窗口，重新加载当前路径
            if (menu.equals("刷新")) {
                String path = fileSystemUI.jf.getTitle().replace("文件管理系统", "");
                fileSystemUI.jf.dispose();
                fileSystemUI.initUI(path);
            }
            // 创建文件/文件夹
            else if (menu.equals("新建文件")) {
                String path = fileSystemUI.jf.getTitle().replace("文件管理系统", "");
                String fileName = JOptionPane.showInputDialog("请输入文件名：");
                if (fileName != null && !fileName.isEmpty()) {
                    File newFile = new File(path + "\\" + fileName);
                    try {
                        if (newFile.createNewFile()) {
                            JOptionPane.showMessageDialog(null, "文件创建成功！");
                            // 刷新界面显示新文件
                            fileSystemUI.jf.dispose();
                            fileSystemUI.initUI(path);
                        } else {
                            JOptionPane.showMessageDialog(null, "文件已存在！");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else if (menu.equals("新建文件夹")) {
                String path = fileSystemUI.jf.getTitle().replace("文件管理系统", "");
                String dirName = JOptionPane.showInputDialog("请输入文件夹名：");
                if (dirName != null && !dirName.isEmpty()) {
                    File newDir = new File(path + "\\" + dirName);
                    if (newDir.mkdirs()) {
                        JOptionPane.showMessageDialog(null, "文件夹创建成功！");
                        // 刷新界面显示新文件夹
                        fileSystemUI.jf.dispose();
                        fileSystemUI.initUI(path);
                    } else {
                        JOptionPane.showMessageDialog(null, "文件夹已存在！");
                    }
                }
            }
        }
    }

    // 处理鼠标点击事件
    @Override
    public void mousePressed(MouseEvent e) {
        // 获取点击的组件
        Object source = e.getSource();
        // 只处理鼠标左键点击
        if (e.getButton() == 1) {
            // 如果点击的是文件标签，且是双击
            if (source instanceof JLabel && e.getClickCount() == 2) {
                System.out.println("双击了文件标签");
                JLabel fileLabel = (JLabel) source;
                // 获取标签绑定的文件绝对路径
                String filePath = fileLabel.getToolTipText();
                File targetFile = new File(filePath);
                // 调用电脑本地程序打开文件
                try {
                    Desktop.getDesktop().open(targetFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "文件打开失败：" + ex.getMessage());
                }
            }
        }
        // 保留V2.0的右键菜单逻辑（窗口空白处右键弹出菜单）
        else if (source instanceof JFrame && e.getButton() == 3) {
            JPopupMenu popupMenu = new JPopupMenu();
            String[] menuStrs = {"新建文件", "新建文件夹", "刷新"};
            for (String menuStr : menuStrs) {
                JMenuItem menu = new JMenuItem(menuStr);
                // 给菜单项添加点击监听器
                menu.addActionListener(this);
                // 把菜单项添加到右键菜单
                popupMenu.add(menu);
            }
            // 显示右键菜单
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }

    }
}