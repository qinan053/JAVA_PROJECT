package S1115.V1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Java 简单文件管理系统
 * 支持文件/目录的创建、删除、查看、复制、移动等功能
 */
public class SFile0 {
    // 当前工作目录（默认用户主目录）
    private static File currentDir = new File(System.getProperty("user.home"));
    private static final Scanner scanner = new Scanner(System.in);
    // 日期格式化工具（用于显示文件修改时间）
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println("=== Java 简单文件管理系统 ===");
        System.out.println("当前工作目录：" + currentDir.getAbsolutePath());
        System.out.println("输入 'help' 查看可用命令\n");

        while (true) {
            // 显示命令提示符（当前目录名）
            System.out.print("[" + currentDir.getName() + "] > ");
            String command = scanner.nextLine().trim();

            // 命令处理
            switch (command.toLowerCase()) {
                case "help":
                    showHelp();
                    break;
                case "ls":
                    listFiles();
                    break;
                case "cd":
                    System.out.print("请输入目标目录路径：");
                    String targetDir = scanner.nextLine().trim();
                    changeDirectory(targetDir);
                    break;
                case "mkdir":
                    System.out.print("请输入目录名称：");
                    String dirName = scanner.nextLine().trim();
                    createDirectory(dirName);
                    break;
                case "touch":
                    System.out.print("请输入文件名称：");
                    String fileName = scanner.nextLine().trim();
                    createFile(fileName);
                    break;
                case "rm":
                    System.out.print("请输入要删除的文件/目录路径：");
                    String deletePath = scanner.nextLine().trim();
                    deleteFileOrDir(deletePath);
                    break;
                case "cp":
                    System.out.print("请输入源文件/目录路径：");
                    String sourcePath = scanner.nextLine().trim();
                    System.out.print("请输入目标路径：");
                    String destPath = scanner.nextLine().trim();
                    copyFileOrDir(sourcePath, destPath);
                    break;
                case "mv":
                    System.out.print("请输入源文件/目录路径：");
                    String srcPath = scanner.nextLine().trim();
                    System.out.print("请输入目标路径：");
                    String targetPath = scanner.nextLine().trim();
                    moveFileOrDir(srcPath, targetPath);
                    break;
                case "stat":
                    System.out.print("请输入文件路径：");
                    String filePath = scanner.nextLine().trim();
                    showFileDetails(filePath);
                    break;
                case "exit":
                    System.out.println("感谢使用，再见！");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("未知命令！输入 'help' 查看可用命令");
            }
        }
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
        System.out.println("cp          - 复制文件/目录");
        System.out.println("mv          - 移动文件/目录");
        System.out.println("stat        - 查看文件详情");
        System.out.println("exit        - 退出系统");
        System.out.println("help        - 显示帮助信息");
    }

    /**
     * 列出当前目录下的所有文件和目录
     */
    private static void listFiles() {
        File[] files = currentDir.listFiles();
        if (files == null) {
            System.out.println("无法访问当前目录或目录为空");
            return;
        }

        // 先显示目录，再显示文件
        System.out.println("=== 目录 ===");
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.printf("%-30s %s%n", file.getName(), "[目录]");
            }
        }

        System.out.println("\n=== 文件 ===");
        for (File file : files) {
            if (file.isFile()) {
                // 转换文件大小为 KB（保留 2 位小数）
                double sizeKB = (double) file.length() / 1024;
                System.out.printf("%-30s %.2f KB%n", file.getName(), sizeKB);
            }
        }
    }

    /**
     * 切换工作目录
     * @param targetDir 目标目录路径（绝对路径或相对路径）
     */
    private static void changeDirectory(String targetDir) {
        File newDir = new File(targetDir);
        // 如果是相对路径，基于当前目录构建绝对路径
        if (!newDir.isAbsolute()) {
            newDir = new File(currentDir, targetDir);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            currentDir = newDir;
            System.out.println("切换成功，当前目录：" + currentDir.getAbsolutePath());
        } else {
            System.out.println("目录不存在或不是有效目录");
        }
    }

    /**
     * 创建目录
     * @param dirName 目录名称（相对路径）
     */
    private static void createDirectory(String dirName) {
        File newDir = new File(currentDir, dirName);
        if (newDir.exists()) {
            System.out.println("目录已存在");
            return;
        }

        if (newDir.mkdirs()) { // mkdirs() 支持创建多级目录
            System.out.println("目录创建成功：" + newDir.getAbsolutePath());
        } else {
            System.out.println("目录创建失败");
        }
    }

    /**
     * 创建文件
     * @param fileName 文件名（相对路径）
     */
    private static void createFile(String fileName) {
        File newFile = new File(currentDir, fileName);
        if (newFile.exists()) {
            System.out.println("文件已存在");
            return;
        }

        try {
            if (newFile.createNewFile()) {
                System.out.println("文件创建成功：" + newFile.getAbsolutePath());
            } else {
                System.out.println("文件创建失败");
            }
        } catch (IOException e) {
            System.out.println("创建文件时发生错误：" + e.getMessage());
        }
    }

    /**
     * 删除文件或目录（递归删除目录）
     * @param path 要删除的文件/目录路径
     */
    private static void deleteFileOrDir(String path) {
        File file = new File(path);
        if (!file.isAbsolute()) {
            file = new File(currentDir, path);
        }

        if (!file.exists()) {
            System.out.println("文件/目录不存在");
            return;
        }

        // 递归删除目录
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteFileOrDir(child.getAbsolutePath());
                }
            }
        }

        if (file.delete()) {
            System.out.println("删除成功：" + file.getAbsolutePath());
        } else {
            System.out.println("删除失败：" + file.getAbsolutePath());
        }
    }

    /**
     * 复制文件或目录
     * @param sourcePath 源路径
     * @param destPath 目标路径
     */
    private static void copyFileOrDir(String sourcePath, String destPath) {
        File source = new File(sourcePath);
        if (!source.isAbsolute()) {
            source = new File(currentDir, sourcePath);
        }

        File dest = new File(destPath);
        if (!dest.isAbsolute()) {
            dest = new File(currentDir, destPath);
        }

        // 检查源文件/目录是否存在
        if (!source.exists()) {
            System.out.println("源文件/目录不存在");
            return;
        }

        try {
            if (source.isFile()) {
                copySingleFile(source, dest);
            } else {
                copyDirectory(source, dest);
            }
            System.out.println("复制成功：" + source.getAbsolutePath() + " -> " + dest.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("复制失败：" + e.getMessage());
        }
    }

    /**
     * 复制单个文件
     */
    private static void copySingleFile(File source, File dest) throws IOException {
        // 如果目标是目录，自动生成与源文件同名的文件
        if (dest.isDirectory()) {
            dest = new File(dest, source.getName());
        }

        // 使用缓冲流提高复制效率
        try (InputStream in = new BufferedInputStream(new FileInputStream(source));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(dest))) {

            byte[] buffer = new byte[1024 * 8]; // 8KB 缓冲区
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }

    /**
     * 递归复制目录
     */
    private static void copyDirectory(File sourceDir, File destDir) throws IOException {
        // 创建目标目录（包括父目录）
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            File destFile = new File(destDir, file.getName());
            if (file.isFile()) {
                copySingleFile(file, destFile);
            } else {
                copyDirectory(file, destFile);
            }
        }
    }

    /**
     * 移动文件或目录（本质是复制+删除源文件）
     * @param srcPath 源路径
     * @param targetPath 目标路径
     */
    private static void moveFileOrDir(String srcPath, String targetPath) {
        File source = new File(srcPath);
        if (!source.isAbsolute()) {
            source = new File(currentDir, srcPath);
        }

        File target = new File(targetPath);
        if (!target.isAbsolute()) {
            target = new File(currentDir, targetPath);
        }

        if (!source.exists()) {
            System.out.println("源文件/目录不存在");
            return;
        }

        // 先复制，复制成功后删除源文件
        try {
            copyFileOrDir(source.getAbsolutePath(), target.getAbsolutePath());
            deleteFileOrDir(source.getAbsolutePath());
            System.out.println("移动成功：" + source.getAbsolutePath() + " -> " + target.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("移动失败：" + e.getMessage());
        }
    }

    /**
     * 查看文件详情
     * @param filePath 文件路径
     */
    private static void showFileDetails(String filePath) {
        File file = new File(filePath);
        if (!file.isAbsolute()) {
            file = new File(currentDir, filePath);
        }

        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        System.out.println("=== 文件详情 ===");
        System.out.println("路径：" + file.getAbsolutePath());
        System.out.println("类型：" + (file.isDirectory() ? "目录" : "文件"));
        if (file.isFile()) {
            System.out.println("大小：" + file.length() + " 字节（" + String.format("%.2f", (double) file.length() / 1024) + " KB）");
        }
        System.out.println("创建时间：" + sdf.format(new Date(file.lastModified())));
        System.out.println("可读：" + (file.canRead() ? "是" : "否"));
        System.out.println("可写：" + (file.canWrite() ? "是" : "否"));
        System.out.println("可执行：" + (file.canExecute() ? "是" : "否"));
    }
}