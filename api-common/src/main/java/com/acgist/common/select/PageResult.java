package com.acgist.common.select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询结果
 */
public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 内容 */
	private final List<T> result = new ArrayList<T>();

	/** 总记录数 */
	private final long total;

	/** 分页信息 */
	private final PageQuery pageQuery;

	public PageResult() {
		this.total = 0L;
		this.pageQuery = new PageQuery();
	}

	/**
	 * @param result 内容
	 * @param total 总记录数
	 * @param pageable 分页信息
	 */
	public PageResult(List<T> result, long total, PageQuery pageQuery) {
		this.result.addAll(result);
		this.total = total;
		this.pageQuery = pageQuery;
	}

	public List<T> getResult() {
		return result;
	}

	public long getTotal() {
		return total;
	}

	public int getPageSize() {
		return pageQuery.getPageSize();
	}
	
	public int getTotalPage() {
		return (int) (getTotal() / getPageSize());
	}
	
}
