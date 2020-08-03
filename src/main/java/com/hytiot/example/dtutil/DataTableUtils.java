package com.hytiot.example.dtutil;

import com.hytiot.example.dtutil.domain.Column;
import com.hytiot.example.dtutil.domain.DataTableRequest;
import com.hytiot.example.dtutil.domain.Order;
import com.hytiot.example.dtutil.domain.ResultObj;
import com.hytiot.example.dtutil.domain.Search;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class DataTableUtils {

	/**
	 * 解析DataTable 请求参数为 DataTableRequest对象
	 * 
	 * @param request
	 * @return
	 */
	public static DataTableRequest getParam(HttpServletRequest request) {

		DataTableRequest result = new DataTableRequest();
		// 拓展搜索参数
		result.setExtraSearch(getExtraSearchParam(request.getParameterMap()));

		// start", "draw", "length"

		result.setStart(request.getParameter("start") == null ? 0 : Integer
				.valueOf((String) (request.getParameter("start"))));
		result.setDraw(request.getParameter("draw") == null ? 0 : Integer
				.valueOf((String) (request.getParameter("draw"))));
		result.setLength(request.getParameter("length") == null ? 0 : Integer
				.valueOf((String) (request.getParameter("length"))));

		Search search = new Search();
		search.setValue((String) request.getParameter("search[value]"));

		search.setRegex(request.getParameter("search[regex]") == null ? false
				: Boolean.valueOf(request.getParameter("search[regex]")));

		result.setSearch(search);

		List<Order> orderList = new ArrayList<Order>();
		Integer numOrder = 0;
		String strOrder = "order[" + numOrder + "]";

		while (request.getParameter(strOrder + "[column]") != null) {
			Order order = new Order();

			order.setColumn(Integer.valueOf((String) (request
					.getParameter(strOrder + "[column]"))));
			order.setDir((String) request.getParameter(strOrder + "[dir]"));

			orderList.add(order);
			numOrder++;
			strOrder = "order[" + numOrder + "]";
		}

		result.setOrder(orderList);
		List<Column> columns = new ArrayList<Column>();

		Integer num = 0;

		String str = "columns[" + num + "]";

		while (request.getParameter(str + "[searchable]") != null) {
			Column column = new Column();

			result.getColMap().put(num,
					(String) request.getParameter(str + "[data]"));

			column.setData((String) request.getParameter(str + "[data]"));
			column.setName((String) request.getParameter(str + "[name]"));
			column.setOrderable(request.getParameter(str + "[orderable]") == null ? false
					: Boolean.valueOf((String) request.getParameter(str
							+ "[orderable]")));
			column.setSearchable(request.getParameter(str
					+ "[searchable]".toString()) == null ? false : Boolean
					.valueOf((String) request
							.getParameter(str + "[searchable]")));

			Search search2 = new Search();

			search2.setValue((String) request.getParameter(str
					+ "[search][value]"));
			search2.setRegex(request.getParameter(str + "[search][regex]") == null ? false
					: Boolean.valueOf((String) request.getParameter(str
							+ "[search][regex]")));
			column.setSearch(search2);
			columns.add(column);

			result.setColumns(columns);
			num++;
			str = "columns[" + num + "]";
		}

		return result;
	}

	private static Map<String, String> getExtraSearchParam(
			Map<String, String[]> parameterMap) {
		Map<String, String> extraSearch = new HashMap<String, String>();

		for (String keyStr : parameterMap.keySet()) {
			if (keyStr != null && keyStr != ""
					&& keyStr.startsWith("extra_search")) {
				String key = keyStr.substring(13, keyStr.length() - 1);
				String value = getFirstStr(parameterMap.get(keyStr));
				extraSearch.put(key, value);
			}

		}

		return extraSearch;
	}

	private static String getFirstStr(String[] strings) {
		String result = "";
		if (strings != null && strings.length > 0) {
			result = strings[0] == null ? "" : strings[0];
		}
		return result;
	}

	public static <T> ResultObj<T> getResultObj(
			DataTableRequest dataTableRequest, Class<T> classObj)
			throws Exception {

		String orderBy = "";

		Map<Integer, String> colMap = dataTableRequest.getColMap();
		// 全局搜索开关
		boolean globalSearchFlag = false;
		// 全局搜索 对象 关系or
		T globalSearchObj = classObj.newInstance();

		// 指定列搜索
		T columnsSearchObj = classObj.newInstance();

		// 获得全局搜索对象
		Search search = dataTableRequest.getSearch();

		// 检查是否进行全局搜索
		if (search.getValue() != null && !"".equals(search.getValue()))
			globalSearchFlag = true;

		// 获得列信息（排序，搜索）
		List<Column> columns = dataTableRequest.getColumns();

		for (Column column : columns) {
			// 列名
			column.getData();
			// 是否搜索
			if (column.isSearchable()) {
				// 搜索的索引词
				String value = column.getSearch().getValue();

				// 反射设置检索值
				Field declaredField = classObj.getDeclaredField(column
						.getData());
				declaredField.setAccessible(true);

				// String类型
				if (declaredField.getType().equals(String.class)) {
					if (value != null && !"".equals(value.trim()))
						declaredField.set(columnsSearchObj, value);
					if (globalSearchFlag && search.getValue() != null
							&& !"".equals(search.getValue().trim()))
						declaredField.set(globalSearchObj, search.getValue());

					// int类型
				} else if (declaredField.getType().equals(int.class)) {
					if (value != null && !"".equals(value.trim()))
						declaredField.set(columnsSearchObj,
								Integer.valueOf(value));
					if (globalSearchFlag && search.getValue() != null
							&& !"".equals(search.getValue().trim()))
						declaredField.set(globalSearchObj,
								Integer.valueOf(search.getValue()));
					// Integer 类型
				} else if (declaredField.getType().equals(Integer.class)) {
					if (value != null && !"".equals(value.trim()))
						declaredField.set(columnsSearchObj,
								Integer.valueOf(value));
					if (globalSearchFlag && search.getValue() != null
							&& !"".equals(search.getValue().trim()))
						declaredField.set(globalSearchObj,
								Integer.valueOf(search.getValue()));
					// Date类型
				} else if (declaredField.getType().equals(Date.class)) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						if (value != null && !"".equals(value.trim()))
							declaredField.set(columnsSearchObj,
									sdf.parse(value));
						if (globalSearchFlag && search.getValue() != null
								&& !"".equals(search.getValue().trim()))
							declaredField.set(globalSearchObj,
									sdf.parse(search.getValue()));
					} catch (Exception e) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");

						if (value != null && !"".equals(value.trim()))
							declaredField.set(columnsSearchObj,
									sdf.parse(value));
						if (globalSearchFlag && search.getValue() != null
								&& !"".equals(search.getValue().trim()))
							declaredField.set(globalSearchObj,
									sdf.parse(search.getValue()));
					}
					// TimeStamp 类型
				} else if (declaredField.getType().equals(Timestamp.class)) {

					if (value != null && !"".equals(value.trim()))
						declaredField.set(columnsSearchObj,
								Timestamp.valueOf(value));
					if (globalSearchFlag && search.getValue() != null
							&& !"".equals(search.getValue().trim()))
						declaredField.set(globalSearchObj,
								Timestamp.valueOf(search.getValue()));
					// BigDecimal 类型
				} else if (declaredField.getType().equals(BigDecimal.class)) {
					if (value != null && !"".equals(value.trim()))
						declaredField.set(columnsSearchObj, new BigDecimal(
								value));
					if (globalSearchFlag && search.getValue() != null
							&& !"".equals(search.getValue().trim()))
						declaredField.set(globalSearchObj, new BigDecimal(
								search.getValue()));
				}
			}
			// 是否排序
			if (column.isOrderable()) {
				// 暂不实现
			}
			//

		}

		// 解析排序信息
		List<Order> orders = dataTableRequest.getOrder();

		if (orders.size() > 0) {
			for (Order o : orders) {
				// 按那一列排序,由列编号转换为 排序名
				String colName = colMap.get(o.getColumn());
				// 列名为空不操作
				if (colName != null && !"".equals(colName)) {

					// 排序方式
					if ("DESC".equalsIgnoreCase(o.getDir())) {
						orderBy += colName + " DESC,";
					} else if ("ASC".equalsIgnoreCase(o.getDir())) {
						orderBy += colName + " ASC,";
					}
				}
			}
			orderBy = orderBy.substring(0, orderBy.length() - 1);
		}
		ResultObj<T> resultObj = new ResultObj<T>();
		resultObj.setColumnsSearchObj(columnsSearchObj);
		resultObj.setGlobalSearchObj(globalSearchObj);
		resultObj.setOrderBy(orderBy);
		return resultObj;

	}
}
