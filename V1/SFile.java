package S1115.V1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SFile {
    // 属性：
    // 地址
    String path;
    // 名字
    String name;
    // 文件格式
    String format;
    // 大小(KB)
    int size;// 单位
    // 类型：是文件还是文件夹
    String type;
    // 以下是文件夹的

    // 方法： 打印详细信息 获取名字 获取地址 获取格式 读取数据 设定权限读写 获取大小
    public void printInfo() {
    // 区分文件还是文件夹了
        if(type.equals("文件")){
            System.out.println("文件的名字是："+name);
            System.out.println("文件的地址是："+path);
            System.out.println("文件的格式是："+format);
            System.out.println("文件的大小是："+size + "KB");
            System.out.println("文件的权限是：读写");
        }else{
            System.out.println("文件夹的名字是："+name);
            System.out.println("文件夹的地址是："+path);
            System.out.println("文件夹的大小是："+size + "KB");
            System.out.println("文件夹的权限是：读写");
        }
    }
//
//
//    // 存储所有文件和文件夹的列表
//    private static List<SFile> fileSystem = new ArrayList<>();
//
//
//    // 创建文件的构造方法
//    public SFile(String path, String name, String format, int size) {
//        this.path = path;
//        this.name = name;
//        this.format = format;
//        this.size = size;
//        this.type = "文件";
//    }
//
//    // 创建文件夹的构造方法
//    public SFile(String path, String name) {
//        this.path = path;
//        this.name = name;
//        this.format = "";
//        this.size = 0;
//        this.type = "文件夹";
//    }
//
//    //获取名字
//    public void getName(){
//
//        System.out.println("文件的名字是："+name);
//    }
//
//    // 获取地址
//    public void getPath() {
//
//        System.out.println("文件的地址是：" + path);
//    }
//
//    // 获取格式
//    public void getFormat() {
//        if(type.equals("文件")) {
//            System.out.println("格式是：" + format);
//        } else {
//            System.out.println("文件夹没有格式");
//        }
//    }
//
//    // 获取大小
//    public void getSize() {
//        System.out.println("文件的大小是：" + size + "KB");
//    }
//
//    // 创建文件的方法
//    public static void createFile(String name, String format, int size) {
//        String path = "/files/" + name + "." + format;
//        SFile newFile = new SFile(path, name, format, size);
//        fileSystem.add(newFile);
//        System.out.println("文件创建成功！");
//        newFile.printInfo();
//    }
//
//    // 创建目录的方法
//    public static void createDirectory(String name) {
//        String path = "/directories/" + name;
//        SFile newDir = new SFile(path, name);
//        fileSystem.add(newDir);
//        System.out.println("目录创建成功！");
//        newDir.printInfo();
//    }
//
//    // 查看文件信息
//    public static void viewFileInfo(String name) {
//        SFile file = findFileByName(name);
//        if(file != null) {
//            file.printInfo();
//        } else {
//            System.out.println("未找到文件或目录：" + name);
//        }
//    }
//
//    // 根据名称查找文件
//    private static SFile findFileByName(String name) {
//        for(SFile file : fileSystem) {
//            if(file.name.equals(name)) {
//                return file;
//            }
//        }
//        return null;
//    }
//
//
//    // 显示菜单
//    public static void showMenu() {
//        System.out.println("--欢迎来到FileSystem--");
//        System.out.println("1.创建文件");
//        System.out.println("2.创建目录");
//        System.out.println("3.查看文件信息");
//        System.out.println("4.获取文件名");
//        System.out.println("请输入功能编号 - fileName/DirName");
//    }


    // 存储所有文件/文件夹（简化：平级存储，无嵌套）
    private static List<SFile> fileList = new ArrayList<>();

    // 文件构造方法
    public SFile(String name, String format, int size) {
        this.name = name;
        this.format = format;
        this.size = size;
        this.type = "文件";
        this.path = "/文件/" + name + "." + format; // 固定路径
    }

    // 文件夹构造方法
    public SFile(String name) {
        this.name = name;
        this.format = "";
        this.size = 0;
        this.type = "文件夹";
        this.path = "/目录/" + name; // 固定路径
    }

    // 根据名称查找（文件匹配「名称.格式」，文件夹匹配「名称」）
    private static SFile findByName(String targetName) {
        for (SFile file : fileList) {
            String fullName = "文件".equals(file.type) ? file.name + "." + file.format : file.name;
            if (fullName.equals(targetName)) {
                return file;
            }
        }
        return null;
    }

    // 创建文件
    public static void createFile(String name, String format, int size) {
        fileList.add(new SFile(name, format, size));
        System.out.println("文件创建成功！");
    }

    // 创建目录
    public static void createDir(String name) {
        fileList.add(new SFile(name));
        System.out.println("目录创建成功！");
    }

    // 查看信息
    public static void viewInfo(String name) {
        SFile file = findByName(name);
        if (file != null) {
            file.printInfo();
        } else {
            System.out.println("未找到：" + name + "\n");
        }
    }

    // 获取名称
    public static void getName(String targetName) {
        SFile file = findByName(targetName);
        if (file != null) {
            String fullName = "文件".equals(file.type) ? file.name + "." + file.format : file.name;
            System.out.println("名称：" + fullName + "\n");
        } else {
            System.out.println("未找到：" + targetName + "\n");
        }
    }

    // 简化菜单
    public static void showMenu() {
        System.out.println("===== 文件系统 =====");
        System.out.println("1. 创建文件");
        System.out.println("2. 创建目录");
        System.out.println("3. 查看信息");
        System.out.println("4. 获取名称");
        System.out.println("5. 退出");
        System.out.println("====================");
        System.out.print("请选择功能（1-5）：");
    }

    /**
     * 显示帮助信息
     */
    private static void showHelp() {
        System.out.println("=== 命令说明 ===");
        System.out.println("ls          - 查看当前目录下的文件和目录");
        System.out.println("cd          - 切换工作目录");
        System.out.println("mkdir       - 创建目录");
        System.out.println("touch       - 创建文件");
        System.out.println("rm          - 删除文件/目录（目录会递归删除）");
        System.out.println("stat        - 查看文件详情");
        System.out.println("exit        - 退出系统");
        System.out.println("help        - 显示帮助信息");
    }

    // 主函数： 创建文件对象 创建文件/创建目录
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            showMenu();
//            String choice = sc.next();
//            String[] info = choice.split("-");
//            int id=Integer.parseInt(info[0]);
//            String para = info[1];

            int id;
            // 处理非数字输入
            try {
                id = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("输入错误！请输入数字1-5\n");
                continue;
            }
            switch (id){
                case 1:
                    // 创建文件
                    System.out.print("输入文件名：");
                    String fileName = sc.nextLine().trim();
                    System.out.print("输入文件格式（如txt、docx）：");
                    String format = sc.nextLine().trim();
                    System.out.print("输入文件大小（KB）：");
                    try {
                        int size = Integer.parseInt(sc.nextLine().trim());
                        createFile(fileName, format, size);
                    } catch (NumberFormatException e) {
                        System.out.println("大小输入错误！请输入数字\n");
                    }
                    break;

                case 2:
                    // 创建目录
                    System.out.print("输入目录名：");
                    String dirName = sc.nextLine().trim();
                    createDir(dirName);
                    break;

                case 3:
                    // 查看信息（文件输入「名称.格式」，目录输入「名称」）
                    System.out.print("输入要查看的名称（文件：名称.格式 / 目录：名称）：");
                    String viewName = sc.nextLine().trim();
                    viewInfo(viewName);
                    break;

                case 4:
                    // 获取名称
                    System.out.print("输入要查询的名称（文件：名称.格式 / 目录：名称）：");
                    String getName = sc.nextLine().trim();
                    getName(getName);
                    break;

                case 5:
                    System.out.println("退出系统！");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("请输入正确的功能编号~");
            }
        }
    }
}