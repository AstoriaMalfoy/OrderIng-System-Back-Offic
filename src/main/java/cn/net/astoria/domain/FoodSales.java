package cn.net.astoria.domain;

/**
 * @ClassName FoodSales
 * @Description 对应销售表中的数据 对用用于查询销量信息
 * @Author Astoria
 * @Date 2020/3/4 16:06
 * @Version 1.0
 */
public class FoodSales {
	/**
	 * 对应相应的key值 使用yyyyMM + fId的形式拼接而成
	 */
	private String fs_Id;
	/**
	 * 对应该菜品当月的销量
	 */
	private int fs_count;

	public FoodSales(String fs_Id, int fs_count) {
		this.fs_Id = fs_Id;
		this.fs_count = fs_count;
	}

	public FoodSales() {
	}


	@Override
	public String toString() {
		return "FoodSales{" +
				"fs_Id='" + fs_Id + '\'' +
				", fs_count=" + fs_count +
				'}';
	}

	public String getFs_Id() {
		return fs_Id;
	}

	public void setFs_Id(String fs_Id) {
		this.fs_Id = fs_Id;
	}

	public int getFs_count() {
		return fs_count;
	}

	public void setFs_count(int fs_count) {
		this.fs_count = fs_count;
	}
}
