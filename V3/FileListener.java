package S1115.V3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FileListener extends MouseAdapter implements ActionListener, MouseListener {

    FileUI fileUI;

    public void setFileUI(FileUI fileUI) {
        this.fileUI = fileUI;
    }

    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj instanceof JButton) {
            System.out.println("点击了按钮");
            String path = e.getActionCommand();// 获取按钮上的绝对路径
            fileUI.initUI(path);
        }else if (obj instanceof JMenuItem){
            System.out.println("点击了菜单");
            JMenuItem jmi = (JMenuItem) obj;// 将事件源对象 转为 对应类型对象变量名
            String text = e.getActionCommand();
            if(text.equals("刷新")){
            }else if(text.equals("创建文件")){
                fileUI.jf.dispose();
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        // instanceof 判断对象是否是某个类的实例
        Object obj = e.getSource();// 获取点击的组件对象
        int btn = e.getButton();// 鼠标的左中右健
        if (obj instanceof JLabel) {
            // 鼠标在标签上按下了
            if (btn == 1) {
                if(e.getClickCount()==2){// 双击
                    JLabel jla = (JLabel) obj;
                    File file = new File(jla.getToolTipText());
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } else if (obj instanceof JFrame) {
        // 鼠标在窗体上按下了
        }
    }
}