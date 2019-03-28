
package com.bsoft.controller;

import com.bsoft.entity.MappingRelation;
import com.bsoft.mysql.service.MappingRelationService;
import com.bsoft.oracle.service.OracleService;
import com.bsoft.sqlServer.service.SqlServerService;
import com.bsoft.sqlServer2.service.SqlServerService2;
import com.bsoft.utils.DataChangeUtil;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class IndexController {
    private static Logger log = Logger.getLogger(IndexController.class);
    @Autowired
    private MappingRelationService mappingRelationService;
    @Autowired
    private SqlServerService sqlServerService;
    @Autowired
    private SqlServerService2 sqlServerService2;
    @Autowired
    private OracleService oracleService;

    //数据源类型
    private static final String SQL_SERVER = "SQLServer";
    private static final String SQL_SERVER2 = "SQLServer2";
    private static final String ORACLE = "oracle";
    private static final String MYSQL = "mysql";


    //交互数据类型
    private static final String XML_FORMAT = "xml";
    private static final String JSON_FORMAT = "json";

    @Value("${inStr.data.format}")
    private String instrFormat;


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getData")
    public String getData(MappingRelation record) {
        return sqlServerService.selectDate();
    }

    @ResponseBody
    @RequestMapping("/getOracleData")
    public String getOracleData(MappingRelation record) {
        return oracleService.selectDate();
    }


    @ResponseBody
    @RequestMapping(value = "queryData/{url}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryData(@PathVariable String url, HttpServletRequest request) throws IOException {
        HashMap<String, Object> req = new HashMap<String, Object>(3);
        org.json.JSONObject xmlJSONObj = new org.json.JSONObject();
        MappingRelation relation = mappingRelationService.selectByUrl(url);
        if (relation != null) {
            JSONObject jsonParam = DataChangeUtil.getJSONParam(request);
            log.info("inStr:" + jsonParam);
            String inStr = "";
            String dateFormat = relation.getDateFormat();
            if (XML_FORMAT.equals(instrFormat)) {
                inStr = "<xml>" + XML.toString(jsonParam) + "</xml>";
            } else if (JSON_FORMAT.equals(instrFormat)) {
                inStr = inStr;
            }
            req.put("inStr", inStr);
            req.put("s_return", null);
            req.put("method", relation.getMethod());
            if (relation.getDataSource() == null) {
                xmlJSONObj.append("code", -1);
                xmlJSONObj.append("message", "url为：" + url + "的地址无方法请查询!");
                return xmlJSONObj.toString();
            }
            String dataSource = relation.getDataSource();
            System.out.println("dataSource:" + relation.getDataSource());
            String returnData = "";
            switch (dataSource) {
                case SQL_SERVER:
                    returnData = sqlServerService.queryData(req);
                    log.info("returnData:" + returnData);
                    if (JSON_FORMAT.equals(relation.getDateFormat())) {
                        return returnData;
                    }
                    xmlJSONObj = XML.toJSONObject(returnData);
                    break;
                case SQL_SERVER2:
                    returnData = sqlServerService2.queryData(req);
                    log.info("returnData:" + returnData);
                    if (JSON_FORMAT.equals(relation.getDateFormat())) {
                        return returnData;
                    }
                    xmlJSONObj = XML.toJSONObject(returnData);
                    break;
                case ORACLE:
                    oracleService.queryData(req);
                    Clob clob = (Clob) req.get("s_return");
                    if (clob != null) {
                        try {
                            returnData = DataChangeUtil.clobToString(clob).trim();
                            log.info("returnData:" + returnData);
                            if (JSON_FORMAT.equals(relation.getDateFormat())) {
                                return returnData;
                            }
                            xmlJSONObj = XML.toJSONObject(returnData);
                        } catch (SQLException e) {
                            log.warn(relation.getMethod() + "方法,入参：" + inStr + "，获取的CLOB转String失败！");
                            e.printStackTrace();
                        }
                    }
                    break;
                case MYSQL:
                    break;
                default:
                    break;
            }
        } else {
            xmlJSONObj.append("code", -1);
            xmlJSONObj.append("message", "无url为：" + url + "的地址,请查询!");
            return xmlJSONObj.toString();
        }
        return xmlJSONObj.toString();
    }
}
