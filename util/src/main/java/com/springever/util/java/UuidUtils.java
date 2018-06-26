package com.springever.util.java;

import java.security.SecureRandom;

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

    /**
     * 产生n位随机数
     *
     * @param n
     * @param radix
     *            10-只有数字 36-包含数字和字母(小写)
     * @return
     */
    public static String randomPassword(int n, int radix) {
        SecureRandom a = new SecureRandom();
        long l = a.nextLong();
        long l1 = l < 0L ? -l : l;
        StringBuffer stringbuffer;
        for (stringbuffer = new StringBuffer(Long.toString(l1, radix)); stringbuffer.length() < n; stringbuffer
                .insert(0, '0')) {
        }

        if (stringbuffer.length() > n) {
            return stringbuffer.substring(stringbuffer.length() - n);
        } else {
            return stringbuffer.toString();
        }
    }
}
