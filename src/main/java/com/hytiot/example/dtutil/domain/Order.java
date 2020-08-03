package com.hytiot.example.dtutil.domain;

public class Order {
	/**
	 * 按哪一列
	 */
	private int column;
	/**
	 * 排序方式
	 */
	private String dir;

	private String dbName;

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
