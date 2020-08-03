package com.hytiot.example.dtutil.domain;

public class Column {

	/**
	 * 列的值
	 */
	private String data;
	/**
	 * 列名
	 */
	private String name;
	/**
	 * 搜索开关
	 */
	private boolean searchable;
	/**
	 * 索引开关
	 */
	private boolean orderable;
	/**
	 * 搜索条件
	 */
	private Search search;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public boolean isOrderable() {
		return orderable;
	}

	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

}
