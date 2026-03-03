package S1115.V2;

public class AdminUser extends User{

    public AdminUser(String weixinID){
        super(weixinID);
        System.out.println("子类自己的构造方法");
    }

    //方法重写
    public void showInfo(){
        System.out.println("用户名称："+userName);
        System.out.println("ID："+weixinID);
        System.out.println("权限：管理员");
    }
    public static void main(String[] args) {
        AdminUser adminuser = new AdminUser("123456");
        adminuser.userName="lisi";
        adminuser.showInfo();
        adminuser.getweixinID();

        //调用父类中的内部类
//        User.PassWord passWord = new User.PassWord();
    }
}
