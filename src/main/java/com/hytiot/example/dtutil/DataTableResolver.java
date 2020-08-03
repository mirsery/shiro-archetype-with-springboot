package com.hytiot.example.dtutil;

import com.hytiot.example.dtutil.domain.Column;
import com.hytiot.example.dtutil.domain.DataTableRequest;
import com.hytiot.example.dtutil.domain.Order;
import com.hytiot.example.dtutil.domain.Search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class DataTableResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //仅支持  DataTableParam 注解的支持
        return parameter.getParameterAnnotation(DataTableParam.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        Map<String, String[]> parameterMap = webRequest.getParameterMap();

        DataTableRequest param = getParam(webRequest);

        //设置拓展参数
        param.setExtraSearch(getExtraSearchParam(parameterMap));

        return param;
    }

    /**
     * 解析DataTable 请求参数为 DataTableRequest对象
     *
     * @param request
     * @return
     */
    public DataTableRequest getParam(NativeWebRequest request) {

        DataTableRequest result = new DataTableRequest();

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

            result.getColMap().put(num, (String) request.getParameter(str + "[data]"));

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


    private Map<String, String> getExtraSearchParam(Map<String, String[]> parameterMap) {
        Map<String, String> extraSearch = new HashMap<String, String>();

        for (String keyStr : parameterMap.keySet()) {
            if (keyStr != null && keyStr != "" && keyStr.startsWith("extra_search")) {
                String key = keyStr.substring(13, keyStr.length() - 1);
                String value = getFirstStr(parameterMap.get(keyStr));
                extraSearch.put(key, value);
            }

        }
        return extraSearch;
    }

    /**
     * 获取String 数组的第一个值   strings[0]
     *
     * @param strings
     * @return
     */
    private String getFirstStr(String[] strings) {
        String result = "";
        if (strings != null && strings.length > 0) {
            result = strings[0] == null ? "" : strings[0];
        }
        return result;
    }

}

