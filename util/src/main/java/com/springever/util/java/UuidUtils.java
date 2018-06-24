package com.springever.util.java;

public class UuidUtils {
    //生成一个128位的唯一标识符
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    //生成用户id
    public static String getUserid(){
        String userId=System.currentTimeMillis()+getUUID().toString().replaceAll("\\-", "").substring(0,7);
        return userId;
    }
}
