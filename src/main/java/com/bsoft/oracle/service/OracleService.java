
package com.bsoft.oracle.service;

import com.bsoft.entity.BigData;
import com.bsoft.oracle.dao.OracleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OracleService {
    @Autowired
    private OracleMapper oracleMapper;

    public String selectDate() {
        return oracleMapper.selectDate();
    }

//    public BigData queryData(HashMap<String, Object> req) {
//        return oracleMapper.queryData(req);
//    }
    public HashMap<String, Object> queryData(HashMap<String, Object> req) {
        return oracleMapper.queryData(req);
    }
}
