
package com.bsoft.sqlServer.service;

import com.bsoft.sqlServer.dao.SqlServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SqlServerService {
    @Autowired
    private SqlServerMapper sqlServerMapper;

    public String selectDate() {
        return sqlServerMapper.selectDate();
    }

    public String queryData(HashMap<String, Object> req) {
        return sqlServerMapper.queryData(req);
    }

}
