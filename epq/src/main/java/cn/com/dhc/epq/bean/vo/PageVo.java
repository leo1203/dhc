package cn.com.dhc.epq.bean.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageVo<T> implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer count;
    private Integer pageSize;
    private Integer pageIndex;
    private List<T> data;
    private Map<Integer, String> questions;

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Map<Integer, String> getQuestions() {
		return questions;
	}
	public void setQuestions(Map<Integer, String> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "PageVo{" +
				"count=" + count +
				", pageSize=" + pageSize +
				", pageIndex=" + pageIndex +
				", data=" + data +
				", questions=" + questions +
				'}';
	}
}
