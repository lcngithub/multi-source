package com.bsoft.oracle.dao;

import com.bsoft.entity.BigData;

import java.util.HashMap;


public interface OracleMapper {

    String selectDate();

        HashMap<String, Object>  queryData(HashMap<String, Object> req);
//    BigData queryData(HashMap<String, Object> req);
}