
package com.bsoft.sqlServer2.service;

import com.bsoft.sqlServer2.dao.SqlServerMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SqlServerService2 {
    @Autowired
    private SqlServerMapper2 sqlServerMapper;

    public String selectDate() {
        return sqlServerMapper.selectDate();
    }

    public String queryData(HashMap<String, Object> req) {
        return sqlServerMapper.queryData(req);
    }

}
