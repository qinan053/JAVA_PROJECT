package S1115.V2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

// 鼠标监听器接口 MouseListener 相当于 FileListener的父类
// 接口通常会声明 空的抽象方法 ，要求子类必须实现重写
public class FileListener implements ActionListener, MouseListener {
    public SFileSystemUI fui;
    String dir="D:\\Test\\";
    @Override
    public void actionPerformed(ActionEvent e) {
        // 监听菜单
        String ac = e.getActionCommand();
        if(ac.equals("新建文件")){
            String fileName = JOptionPane.showInputDialog(null, "请输入文件名:");
            File file = new File(dir+fileName);
            try {
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "文件创建成功！");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            fui.jf.setVisible(false);// 先关闭之前的窗体
            fui.showUI();// 创建新的窗体
        }else if(ac.equals("新建文件夹")){
            String fileName = JOptionPane.showInputDialog(null, "请输入文件夹名:");
            File file = new File(dir+fileName);
            file.mkdir();
            JOptionPane.showMessageDialog(null,"文件夹创建成功！");
            fui.jf.setVisible(false);// 先关闭之前的窗体
            fui.showUI();// 创建新的窗体
        }else if(ac.equals("刷新")){
            //刷新界面
            fui.jf.setVisible(false); //关闭当前窗口
            fui.showUI();  //重新打开窗口，加载最新目录
        }
    }
//@Override
//public void actionPerformed(ActionEvent e){
//    String ac = e.getActionCommand();  //获取点击的菜单名称
//
//    //1.新建文件
//    if (ac.equals("新建文件")){
//        //弹出输入框 ，让用户输入文件名
//        String fileName = JOptionPane.showInputDialog(null,"请输入文件名(如test.txt):");
//        if(fileName != null && !fileName.isEmpty()) {  //防止用户取消输入
//            File file = new File(dir + fileName);
//            try {
//                file.createNewFile();  //创建文件
//                JOptionPane.showMessageDialog(null, "文件创建成功！");
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(null, "创建失败：" + ex.getMessage());
//            }
//        }
//    }else if(ac.equals("新建文件夹")){
//        //2.新建文件夹
//        String fileName = JOptionPane.showInputDialog(null,"请输入文件夹名");
//        if (fileName != null && !fileName.isEmpty()){
//            File file = new File(dir+fileName);
//            if (file.mkdir()){ //创建单级文件夹
//                JOptionPane.showMessageDialog(null,"文件夹创建成功！");
//            }else{
//                JOptionPane.showMessageDialog(null,"创建失败（可能已存在）！");
//            }
//        }
//    }
//
//    //3.刷新界面（重新加载目录）
//    if (ac.equals("新建文件")||ac.equals("新建文件夹")||ac.equals("刷新")){
//        fui.jf.setVisible(false); //关闭当前窗口
//        fui.showUI();  //重新打开窗口，加载最新目录
//    }
//}
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // 监听鼠标是否在窗体上右键 弹出菜单
        int btnId = e.getButton();// 1 左键 2 中键 3 右键
        if(btnId==3){
            JPopupMenu popupMenu = new JPopupMenu();
            String[] menuStrs = {"新建文件","新建文件夹","刷新"};
            for (String menuStr : menuStrs) {
                JMenuItem menu = new JMenuItem(menuStr);
                menu.addActionListener(this);
                popupMenu.add(menu);
            }
            popupMenu.show(e.getComponent(),e.getX(),e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}