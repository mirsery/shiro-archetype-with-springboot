package com.hytiot.example.dtutil.domain;

public class Search {
	/**
	 * 当前列搜索的值
	 */
	private String value;
	/**
	 * 当前列搜索是否使用正则
	 */
	private Boolean regex;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getRegex() {
		return regex;
	}

	public void setRegex(Boolean regex) {
		this.regex = regex;
	}

}
