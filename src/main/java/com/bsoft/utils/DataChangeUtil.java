package com.bsoft.utils;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

public class DataChangeUtil {
    public static JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = new JSONObject();
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = new JSONObject(sb.toString());
            // 直接将json信息打印出来
//            System.out.println(jsonParam.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
    // 将字Clob转成String类型
    public static String clobToString(Clob sc) throws SQLException, IOException {
        String reString = "";
        Reader is = sc.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }
}
