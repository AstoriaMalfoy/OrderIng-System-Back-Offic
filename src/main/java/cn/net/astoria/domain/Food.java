package cn.net.astoria.domain;

/**
 * @ClassName Food
 * @Description 菜品的JavaBean对象
 * @Author Astoria
 * @Date 2020/3/1 17:53
 * @Version 1.0
 */
public class Food {


    /**
     * 菜品的编号
     */
    private int f_id;
    /**
     * 菜品的名称
     */
    private String f_name;
    /**
     * 菜品的数量
     */
    private int f_price;


    /**
     * 菜品的图片，未时装
     */
    private String f_img;


    private int monthSell;

    @Override
    public String toString() {
        return "Food{" +
                "f_id='" + f_id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", f_price=" + f_price +
                ", f_img='" + f_img + '\'' +
                ", monthSell=" + monthSell +
                '}';
    }

    public int getMonthSell() {
        return monthSell;
    }

    public void setMonthSell(int monthSell) {
        this.monthSell = monthSell;
    }

    public Food(int f_id, String f_name, int f_price, String f_img, int monthSell) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_price = f_price;
        this.f_img = f_img;
        this.monthSell = monthSell;
    }

    public Food(int f_id, String f_name, int f_price, String f_img) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_price = f_price;
        this.f_img = f_img;
    }

    public Food(int f_id, String f_name, int f_price) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_price = f_price;
    }

    public Food() {
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public int getF_price() {
        return f_price;
    }

    public void setF_price(int f_price) {
        this.f_price = f_price;
    }

    public String getF_img() {
        return f_img;
    }

    public void setF_img(String f_img) {
        this.f_img = f_img;
    }
}
