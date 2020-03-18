package cn.net.astoria.domain;

/**
 * @ClassName Waiter
 * @Description 服务员的JavaBean对象
 * @Author Astoria
 * @Date 2020/3/1 18:33
 * @Version 1.0
 */
public class Waiter {

    /**
     * 服务员的ID
     */
    private int w_id;
    /**
     * 服务员的用户名
     */
    private String w_username;
    /**
     * 服务员的密码
     */
    private String w_password;
    /**
     * 服务员当天处理的订单数量
     */
    private int TodayOrderCount;
    /**
     * 服务员当月处理的订单数量
     */
    private int MonthOrderCount;


    public Waiter(int w_id, String w_username, String w_password, int todayOrderCount, int monthOrderCount) {
        this.w_id = w_id;
        this.w_username = w_username;
        this.w_password = w_password;
        TodayOrderCount = todayOrderCount;
        MonthOrderCount = monthOrderCount;
    }


    public Waiter() {
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "w_id=" + w_id +
                ", w_username='" + w_username + '\'' +
                ", w_password='" + w_password + '\'' +
                ", TodayOrderCount=" + TodayOrderCount +
                ", MonthOrderCount=" + MonthOrderCount +
                '}';
    }

    public int getTodayOrderCount() {
        return TodayOrderCount;
    }

    public void setTodayOrderCount(int todayOrderCount) {
        TodayOrderCount = todayOrderCount;
    }

    public int getMonthOrderCount() {
        return MonthOrderCount;
    }

    public void setMonthOrderCount(int monthOrderCount) {
        MonthOrderCount = monthOrderCount;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getW_username() {
        return w_username;
    }

    public void setW_username(String w_username) {
        this.w_username = w_username;
    }

    public String getW_password() {
        return w_password;
    }

    public void setW_password(String w_password) {
        this.w_password = w_password;
    }


}
