package com.bsoft.entity;

import java.sql.Clob;

public class BigData {
//    private char[] bigData;
//
//    public char[] getBigData() {
//        return bigData;
//    }
//
//    public void setBigData(char[] bigData) {
//        this.bigData = bigData;
//    }
//  private String bigData;
//
//    public String getBigData() {
//        return bigData;
//    }
//
//    public void setBigData(String bigData) {
//        this.bigData = bigData;
//    }
//    public String getBigDataStr() {
//        return new String(this.bigData);
//    }
  private String bigData;
  private String code;

    public String getBigData() {
        return bigData;
    }

    public void setBigData(String bigData) {
        this.bigData = bigData;
    }
//  private byte[] bigData;
//
//    public byte[] getBigData() {
//        return bigData;
//    }
//
//    public void setBigData(byte[] bigData) {
//        this.bigData = bigData;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BigData{" +
                "bigData='" + bigData + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
