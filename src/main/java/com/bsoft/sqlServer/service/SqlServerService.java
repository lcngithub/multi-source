
package com.bsoft.sqlServer.service;

import com.bsoft.sqlServer.dao.SqlServerMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsoft.sqlServer.dao.SqlServerMapper;

import java.util.HashMap;

@Service
public class SqlServerService {
    @Autowired
    private SqlServerMapper sqlServerMapper;
    @Autowired
    private SqlServerMapper2 sqlServerMapper2;

    public String selectDate() {
        return sqlServerMapper.selectDate();
    }

    public String queryData(HashMap<String, Object> req) {
        return sqlServerMapper.queryData(req);
    }

    public String selectDate2() {
        return sqlServerMapper2.selectDate();
    }

    public String queryData2(HashMap<String, Object> req) {
        return sqlServerMapper2.queryData(req);
    }
}
