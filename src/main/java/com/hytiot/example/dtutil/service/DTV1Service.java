package com.hytiot.example.dtutil.service;

import com.hytiot.example.dtutil.dao.DTV1Dao;
import com.hytiot.example.dtutil.dao.DTV2Dao;
import com.hytiot.example.dtutil.domain.Column;
import com.hytiot.example.dtutil.domain.ColumnSearch;
import com.hytiot.example.dtutil.domain.DataTableRequest;
import com.hytiot.example.dtutil.domain.DataTableResponse;
import com.hytiot.example.dtutil.domain.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-08-02 16:26
 **/
@Service
public class DTV1Service {

    @Autowired
    private DTV1Dao dtv1Dao;

    @Autowired
    private DTV2Dao dtv2Dao;

    public DataTableResponse<HashMap<String, String>> getV1List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns) {
        return this.getV1List(dataTableRequest, tableName, argMapColumns, null, null);
    }

    public DataTableResponse<HashMap<String, String>> getV1List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns, String search) {
        return this.getV1List(dataTableRequest, tableName, argMapColumns, search, null);
    }

    public DataTableResponse<HashMap<String, String>> getV2List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns, String joinSql) {
        return this.getV2List(dataTableRequest, tableName, argMapColumns, joinSql, null, null);
    }

    public DataTableResponse<HashMap<String, String>> getV2List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns, String joinSql, String search) {
        return this.getV2List(dataTableRequest, tableName, argMapColumns, joinSql, search, null);
    }

    /**
     * argMapColumns hashMap key为dt ，value为 db
     **/
    public DataTableResponse<HashMap<String, String>> getV1List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns, String search, String extraSearch) {
        List<Order> orders = dataTableRequest.getOrder();
        List<Column> columns = dataTableRequest.getColumns();
        if (orders.size() < 1) {
            orders = null;
        } else {
            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setDbName(argMapColumns.get(columns.get(orders.get(i).getColumn()).getName()));
            }
        }

        List<ColumnSearch> columnSearches = new ArrayList<>();
        for (Column item : columns) {
            if (item.isSearchable() &&
                    item.getSearch().getValue() != null &&
                    !"".equals(item.getSearch().getValue())) {
                ColumnSearch columnSearch = new ColumnSearch();
                columnSearch.setSearchValue(item.getSearch().getValue());
                columnSearch.setDbName(argMapColumns.get(item.getName()));
                columnSearches.add(columnSearch);
            }
        }
        if (columnSearches.size() < 1) {
            columnSearches = null;
        }

        DataTableResponse<HashMap<String, String>> dataTableResponse = new DataTableResponse();
        try {
            int start = dataTableRequest.getStart();
            int length = dataTableRequest.getLength();
            dataTableResponse.setDraw(dataTableRequest.getDraw());
            dataTableResponse.setRecordsTotal(dtv1Dao.getRecordsTotal(tableName));
            dataTableResponse.setRecordsFiltered(dtv1Dao.getRecordsFiltered(tableName, search, extraSearch));
            List<HashMap<String, String>> list = dtv1Dao
                    .getRecord(tableName, argMapColumns, columnSearches, search, extraSearch, orders, start, length);
            dataTableResponse.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            dataTableResponse.setError(e.getMessage());
        }
        return dataTableResponse;
    }

    public DataTableResponse<HashMap<String, String>> getV2List(DataTableRequest dataTableRequest,
            String tableName, HashMap<String, String> argMapColumns, String joinSql, String search,
            String extraSearch) {
        List<Order> orders = dataTableRequest.getOrder();
        List<Column> columns = dataTableRequest.getColumns();
        if (orders.size() < 1) {
            orders = null;
        } else {
            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setDbName(argMapColumns.get(columns.get(orders.get(i).getColumn()).getName()));
            }
        }

        List<ColumnSearch> columnSearches = new ArrayList<>();
        for (Column item : columns) {
            if (item.isSearchable() &&
                    item.getSearch().getValue() != null &&
                    !"".equals(item.getSearch().getValue())) {
                ColumnSearch columnSearch = new ColumnSearch();
                columnSearch.setSearchValue(item.getSearch().getValue());
                columnSearch.setDbName(argMapColumns.get(item.getName()));
                columnSearches.add(columnSearch);
            }
        }
        if (columnSearches.size() < 1) {
            columnSearches = null;
        }

        DataTableResponse<HashMap<String, String>> dataTableResponse = new DataTableResponse();
        try {
            int start = dataTableRequest.getStart();
            int length = dataTableRequest.getLength();
            dataTableResponse.setDraw(dataTableRequest.getDraw());
            dataTableResponse.setRecordsTotal(dtv2Dao.getRecordsTotal(tableName));
            dataTableResponse.setRecordsFiltered(dtv2Dao.getRecordsFiltered(tableName, search, extraSearch, joinSql));
            List<HashMap<String, String>> list = dtv2Dao
                    .getRecord(tableName, argMapColumns, columnSearches, search, extraSearch, orders, start, length,
                            joinSql);
            dataTableResponse.setData(list);
        } catch (Exception e) {
            e.printStackTrace();
            dataTableResponse.setError(e.getMessage());
        }
        return dataTableResponse;
    }

}