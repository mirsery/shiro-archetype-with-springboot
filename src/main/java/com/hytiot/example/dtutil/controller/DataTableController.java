package com.hytiot.example.dtutil.controller;

import com.hytiot.example.dtutil.DataTableParam;
import com.hytiot.example.dtutil.domain.DataTableRequest;
import com.hytiot.example.dtutil.domain.DataTableResponse;
import com.hytiot.example.dtutil.service.DTV1Service;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: shiro-archetype-with-springboot
 * @description:
 * @author: misery
 * @create: 2020-08-01 18:00
 **/
@Controller
@RequestMapping("/dataTable/test")
public class DataTableController {

    private Logger logger = LoggerFactory.getLogger(DataTableController.class);

    @Autowired
    private DTV1Service dtv1Service;

    @GetMapping("/index")
    public String index() {
        System.out.println("hello");
        return "index";
    }

    @ResponseBody
    @PostMapping("/list")
    public DataTableResponse<HashMap<String, String>> getTestLists(@DataTableParam DataTableRequest dataTableRequest) {

        String tableName = "shiro_users";
        logger.info("draw: " + dataTableRequest.getDraw());
        // dt table -> db 数据库字段
        HashMap<String, String> argMapColumns = new HashMap<String, String>() {{
            put("id", "id");
            put("user_no", "user_no");
            put("username", "username");
            put("password", "password");
            put("create_time", "create_time");
        }};
        return dtv1Service.getV1List(dataTableRequest, tableName, argMapColumns);
    }
}