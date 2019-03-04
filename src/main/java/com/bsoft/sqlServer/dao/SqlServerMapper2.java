package com.bsoft.sqlServer.dao;

import java.util.HashMap;

public interface SqlServerMapper2 {

	String selectDate();

	String queryData(HashMap<String, Object> req);
}
