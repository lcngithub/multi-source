package com.bsoft.sqlServer2.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public interface SqlServerMapper2 {

    String selectDate();

    String queryData(HashMap<String, Object> req);
}
