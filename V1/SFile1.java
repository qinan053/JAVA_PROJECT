package S1115.V1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 基础文件管理系统
 * 核心属性：名称、路径、格式、大小、类型、权限
 * 核心操作：ls/mkdir/touch/rm/help/exit
 */
public class SFile1 {
    // 核心属性
    String name;       // 名称
    String path;       // 路径（绝对路径，如 /test/file）
    String format;     // 格式（仅文件有效，目录为空）
    int size;          // 大小（文件：KB；目录：0）
    String type;       // 类型：文件 / 目录
    String permission; // 权限（默认 读写）

    // 存储所有文件/目录
    static final List<SFile1> fileSystem = new ArrayList<>();
    // 当前工作目录（默认根目录 /，用于路径构建）
    static String dir = "/";

    //构造方法（区分文件/目录）
    //文件构造方法
    public SFile1(String name, String path, String format, int size) {
        this.name = name;
        this.path = path;
        this.format = format;
        this.size = size;
        this.type = "文件";
        this.permission = "读写"; // 默认权限
    }

    //目录构造方法
    public SFile1(String name, String path) {
        this.name = name;
        this.path = path;
        this.format = "";
        this.size = 0;
        this.type = "目录";
        this.permission = "读写"; // 默认权限
    }

    //构建绝对路径（基于当前工作目录）
    static String buildAbsolutePath(String name) {
        if (dir.equals("/")) {
            return "/" + name;
        } else {
            return dir + "/" + name;
        }
    }

    //检查当前目录是否存在同名文件/目录（避免冲突）
    static boolean isNameExists(String name) {
        String targetPath = buildAbsolutePath(name);
        for (SFile1 file : fileSystem) {
            if (file.path.equals(targetPath)) {
                return true;
            }
        }
        return false;
    }

    //根据名称查找当前目录下的文件/目录
    static SFile1 findFileInDir(String name) {
        String targetPath = buildAbsolutePath(name);
        for (SFile1 file : fileSystem) {
            if (file.path.equals(targetPath)) {
                return file;
            }
        }
        return null;
    }

    //核心功能方法
    //ls 命令：查看当前目录下的文件和目录
    static void ls() {
        System.out.println("\n当前目录：" + dir);
        System.out.println("------------------------------------------------");
        System.out.printf("%-10s %-10s %-8s %s%n", "类型", "大小(KB)", "权限", "名称");
        System.out.println("------------------------------------------------");

        boolean falg = false;
        for (SFile1 file : fileSystem) {
            // 只显示当前目录的直接子文件/目录
            String expectedPath = dir.equals("/") ? "/" + file.name : dir + "/" + file.name;
            if (file.path.equals(expectedPath)) {
                falg = true;
                String Name = file.type.equals("目录") ? file.name + "/" : file.name + "." + file.format;
                System.out.printf("%-10s %-10d %-8s %s%n",
                        file.type, file.size, file.permission, Name);
            }
        }
        if (!falg) {
            System.out.println("当前目录为空");
        }
        System.out.println("------------------------------------------------");
    }

    //mkdir 命令：创建目录
    static void mkdir(String dirName) {
        if (dirName.isEmpty()) {
            System.out.println("目录名不能为空！");
            return;
        }
        if (isNameExists(dirName)) {
            System.out.println("当前目录已存在 '" + dirName + "'！");
            return;
        }

        String dirPath = buildAbsolutePath(dirName);
        SFile1 newDir = new SFile1(dirName, dirPath);
        fileSystem.add(newDir);
        System.out.println("成功创建目录：" + newDir.path + "（权限：" + newDir.permission + "）");
    }

    //touch 命令：创建文件
    static void touch(String fileName, String format, int size) {
        if (fileName.isEmpty() || format.isEmpty()) {
            System.out.println("文件名和格式不能为空！");
            return;
        }
        if (isNameExists(fileName)) {
            System.out.println("当前目录已存在 '" + fileName + "'！");
            return;
        }

        String filePath = buildAbsolutePath(fileName);
        SFile1 newFile = new SFile1(fileName, filePath, format, size);
        fileSystem.add(newFile);
        System.out.println("成功创建文件：" + newFile.path + "." + newFile.format +
                "（大小：" + newFile.size + "KB，权限：" + newFile.permission + "）");
    }

    //rm 命令：删除文件/目录
    static void rm(String name) {
        SFile1 target = findFileInDir(name);
        if (target == null) {
            System.out.println("未找到文件/目录 '" + name + "'！");
            return;
        }

        // 执行删除
        fileSystem.remove(target);
        String displayPath = target.type.equals("目录") ? target.path : target.path + "." + target.format;
        System.out.println("成功删除：" + displayPath);
    }

    //help 命令：显示帮助信息
    static void help() {
        System.out.println("\n===== 文件管理系统帮助 =====");
        System.out.println("ls                - 查看当前目录下的文件和目录");
        System.out.println("mkdir [目录名]    - 创建新目录（如 mkdir Test）");
        System.out.println("touch [文件名] [格式] [大小KB] - 创建新文件（如 touch test txt 20）");
        System.out.println("rm [名称]         - 删除文件或空目录（如 rm test）");
        System.out.println("help              - 显示帮助信息");
        System.out.println("exit              - 退出系统");
        System.out.println("===========================\n");
    }

    //主程序入口
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println("=====       基础文件管理系统       ======");
        System.out.println("=====    输入 help 查看可用命令    ======");
        System.out.println("======================================\n");

        // 初始化根目录（默认存在）
        fileSystem.add(new SFile1("root", "/"));

        while (true) {
            // 命令提示符
            System.out.print(dir + " > ");
            String input = scanner.nextLine();
            if (input.isEmpty())
                continue;

            // 解析命令（按空格分割）
            String[] cmds = input.split(" ");
            String cmd = cmds[0];

            // 执行命令
            switch (cmd) {
                case "ls":
                    ls();
                    break;
                case "mkdir":
                    if (cmds.length < 2) {
                        System.out.println("格式错误！输入 help 查看可用命令");
                    } else {
                        mkdir(cmds[1]);
                    }
                    break;
                case "touch":
                    if (cmds.length < 4) {
                        System.out.println("格式错误！输入 help 查看可用命令");
                    } else {
                        String fileName = cmds[1];
                        String format = cmds[2];
                        int size = Integer.parseInt(cmds[3]);
                        touch(fileName, format, size);
                    }
                    break;
                case "rm":
                    if (cmds.length < 2) {
                        System.out.println("格式错误！输入 help 查看可用命令");
                    } else {
                        rm(cmds[1]);
                    }
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    System.out.println("感谢使用，再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("未知命令 '" + cmd + "'！输入 help 查看可用命令");
            }
        }
    }
}