package com.hytiot.example.dtutil.domain;

import com.hytiot.example.dtutil.DataTableUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: DataTable请求对象
 */
public class DataTableRequest {

	/**
	 * 按列顺序存储列名
	 */
	private Map<Integer, String> colMap = new HashMap<>();

	/**
	 * 第多少次请求
	 */
	private int draw;
	/**
	 * 列
	 */
	private List<Column> columns;
	/**
	 * 排序
	 */
	private List<Order> order;

	/**
	 * 开始于第多少条
	 */
	private int start = 0;
	/**
	 * 请求条数
	 */
	private int length = 0;
	/**
	 * 全局搜索条件
	 */
	private Search search;

	/**
	 * 拓展搜索 键值
	 */
	private Map<String, String> extraSearch;

	public int getPage() {
		if (length == 0) {
			return 1;
		}

		int page = start / length;
		// if(start%length!=0){
		page += 1;
		// }
		// System.out.println("请求的页码："+page);
		return page;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public Map<Integer, String> getColMap() {
		return colMap;
	}

	public void setColMap(Map<Integer, String> colMap) {
		this.colMap = colMap;
	}

	public Map<String, String> getExtraSearch() {
		return extraSearch;
	}

	public void setExtraSearch(Map<String, String> extraSearch) {
		this.extraSearch = extraSearch;
	}

	/**
	 * 获取拓展搜索的值
	 * 
	 * @param key
	 * @return
	 */
	public String getExtraSearch(String key) {
		if (extraSearch == null) {
			return null;
		}
		return extraSearch.get(key);
	}
	/**
	 * 获得搜索和排序的处理后结果
	 * @param classObj
	 * @return ResultObj<T> 列搜索、全局搜索、排序 对象
	 * @throws Exception
	 */
	public <T> ResultObj<T> getResultObj(Class<T> classObj) throws Exception{
		return DataTableUtils.getResultObj(this, classObj);
	}
}
