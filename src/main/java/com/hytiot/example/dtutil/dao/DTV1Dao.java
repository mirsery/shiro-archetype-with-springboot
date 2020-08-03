package com.hytiot.example.dtutil.dao;

import com.hytiot.example.dtutil.domain.ColumnSearch;
import com.hytiot.example.dtutil.domain.Order;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DTV1Dao {

    /**
     * 数据总数
     **/
    public int getRecordsTotal(@Param("tableName") String tableName);

    /**
     * 过滤后总数
     **/
    public int getRecordsFiltered(
            @Param("tableName") String tableName,
            @Param("search") String search,
            @Param("extraSearch") String extraSearch);

    /**
     * 获取记录数
     **/
    public List<HashMap<String,String>> getRecord(
            @Param("tableName") String tableName,
            @Param("columns") HashMap<String,String> columns,
            @Param("columnSearches") List<ColumnSearch> columnSearches,
            @Param("search") String search,
            @Param("extraSearch") String extraSearch,
            @Param("orderParam") List<Order> orders,
            @Param("start") Integer start,
            @Param("length") Integer length);
}
