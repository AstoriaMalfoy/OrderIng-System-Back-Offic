package cn.net.astoria.domain;

//SELECT `table`.`f_id`,`table`.`w_id`,`table`.`t_num`,`table`.`t_state`,food.`f_name`,food.`f_price` FROM
//		`table` LEFT JOIN food ON `table`.`f_id` = food.`f_id` ORDER BY `table`.`t_num`,`table`.`f_id`;的执行结果
/**
 * @ClassName TableInfo
 * @Description 当从数据库中联合查询每桌的信息的时候得到的 餐桌-菜品信息
 * @Author Astoria
 * @Date 2020/3/5 22:17
 * @Version 1.0
 */
public class TableJoinFood implements Comparable{
	/**
	 * 对应t_id
	 */
	private int t_id;
	/**
	 * 对应菜品ID
	 */
	private int f_id;
	/**
	 * 对应服务员Id
	 */
	private int w_id;
	/**
	 * 对应餐桌编号
	 */
	private int t_num;
	/**
	 * 对应菜品是否上菜的状态
	 */
	private String t_state;
	/**
	 * 对应菜品名称
	 */
	private String f_name;
	/**
	 * 对应菜品价格
	 */
	private int f_price;

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	@Override
	public String toString() {
		return "TableJoinFood{" +
				"t_id=" + t_id +
				", f_id=" + f_id +
				", w_id=" + w_id +
				", t_num=" + t_num +
				", t_state='" + t_state + '\'' +
				", f_name='" + f_name + '\'' +
				", f_price=" + f_price +
				'}';
	}

	public TableJoinFood(int t_id, int f_id, int w_id, int t_num, String t_state, String f_name, int f_price) {
		this.t_id = t_id;
		this.f_id = f_id;
		this.w_id = w_id;
		this.t_num = t_num;
		this.t_state = t_state;
		this.f_name = f_name;
		this.f_price = f_price;
	}

	public TableJoinFood(int f_id, int w_id, int t_num, String t_state, String f_name, int f_price) {
		this.f_id = f_id;
		this.w_id = w_id;
		this.t_num = t_num;
		this.t_state = t_state;
		this.f_name = f_name;
		this.f_price = f_price;
	}

	public TableJoinFood() {
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public int getW_id() {
		return w_id;
	}

	public void setW_id(int w_id) {
		this.w_id = w_id;
	}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	public String getT_state() {
		return t_state;
	}

	public void setT_state(String t_state) {
		this.t_state = t_state;
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

	@Override
	public int compareTo(Object o) {
		if(o instanceof TableJoinFood){
			TableJoinFood tableJoinFood = (TableJoinFood)o;
			if(tableJoinFood.getF_id() > this.f_id){
				return -1;
			}else if(tableJoinFood.getF_id() < this.f_id){
				return 1;
			}else{
				if(tableJoinFood.getT_id() > this.t_id){
					return -1;
				}else{
					return 1;
				}
			}
		}
		return 0;
	}
}
