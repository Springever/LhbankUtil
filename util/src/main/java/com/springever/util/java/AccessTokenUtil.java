package com.springever.util.java;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessTokenUtil {

    public static String generateAccessToken(String custStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String dateStr = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        StringBuffer buffer = new StringBuffer();
        String randomStr = custStr + dateStr;
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(randomStr.getBytes());

            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(generateAccessToken("1231231sdfsdfsdfsdfsdfsdf23123"));
    }
}
