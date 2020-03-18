package cn.net.astoria.domain;

import java.util.List;

/**
 * @ClassName PageBean
 * @Description 通用的分页对象
 * @Author Astoria
 * @Date 2020/3/3 21:46
 * @Version 1.0
 */
public class PageBean<T> {
	/**
	 * 当前的浏览的页码
	 */
	private int currengPage;
	/**
	 * 每一页的记录数
	 */
	private int pageSize;
	/**
	 * 总的记录数
	 */
	private int listCount;
	/**
	 * 总的页面数
	 */
	private int pageCount;
	/**
	 * 列表集合
	 */
	private List<T> listData;

	public PageBean(int currengPage, int pageSize, int listCount, int pageCount, List<T> listData) {
		this.currengPage = currengPage;
		this.pageSize = pageSize;
		this.listCount = listCount;
		this.pageCount = pageCount;
		this.listData = listData;
	}

	public PageBean() {
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"currengPage=" + currengPage +
				", pageSize=" + pageSize +
				", listCount=" + listCount +
				", pageCount=" + pageCount +
				", listData=" + listData +
				'}';
	}

	public int getCurrengPage() {
		return currengPage;
	}

	public void setCurrengPage(int currengPage) {
		this.currengPage = currengPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}
}
