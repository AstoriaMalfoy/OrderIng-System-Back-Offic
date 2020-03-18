package cn.net.astoria.domain;

/**
 * @ClassName User
 * @Description 管理员的JavaBean对象
 * @Author Astoria
 * @Date 2020/3/1 17:48
 * @Version 1.0
 */
public class Boss {



    /**
     * 管理员的账号
     */
    private String b_name;
    /**
     * 管理员的密码
     */
    private String b_password;




    public Boss(String b_name, String b_password) {
        this.b_name = b_name;
        this.b_password = b_password;
    }

    public Boss() {
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_password() {
        return b_password;
    }

    public void setB_password(String b_password) {
        this.b_password = b_password;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "b_name='" + b_name + '\'' +
                ", b_password='" + b_password + '\'' +
                '}';
    }
}
