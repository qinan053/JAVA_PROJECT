package S1115.V2;

public class User {
    //属性
    //变量
    String userName;
    //常量
    final String weixinID;
    final int id;
    //构造代码块
    {
        System.out.println("执行构造代码块1");
    }
    {
        System.out.println("执行构造代码块2");
    }
    //构造方法
    public User(String weixinID){
        this.weixinID = weixinID;
        System.out.println("无参数构造方法，只有一个常量参数");
        count++;
        id = count;
    }

    //重载构造方法
    public User(String userName,String weixinID){
        this.weixinID = weixinID;
        this.userName = userName;
        System.out.println("有参数构造方法");
        count++;
        id = count;
    }

    //普通方法
    public void showInfo(String userName,String weixinID){
        System.out.println("用户昵称："+userName);
        System.out.println("ID："+weixinID);
    }

    //final修饰的方法
    public final void getweixinID(){
        System.out.println("获得ID："+weixinID);
    }

    //静态资源
    static int count = 0;

    //静态代码块
    static {
        System.out.println("静态代码块");
    }

    //内部类
    public class PassWord{
        String password;
        String hash;
    }

    //主函数
    public static void main(String[] args) {
        User user = new User("zhangsan","123456");
        User user1 = new User("1234111");
        User user3 = new User("aaa","123");
        User user4 = new User("456");
        System.out.println(user.id);
        System.out.println(user1.id);
        System.out.println(count);
    }
}
