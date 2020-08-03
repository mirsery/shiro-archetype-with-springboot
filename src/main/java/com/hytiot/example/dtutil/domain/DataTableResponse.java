package com.hytiot.example.dtutil.domain;

import java.util.List;


//@JsonSerialize(using = DataTableResponseSerializer.class)
public class DataTableResponse<T> {
	/**
	 * 请求次数
	 */
	private int draw;
	/**
	 * 总记录数
	 */
	private int recordsTotal;
	/**
	 * 过滤后的总记录数
	 */
	private int recordsFiltered;

	private List<T> data;

	private String error;

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getDraw() {
		return this.draw;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsTotal() {
		return this.recordsTotal;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsFiltered() {
		return this.recordsFiltered;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public List<T> getData() {
		return this.data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
