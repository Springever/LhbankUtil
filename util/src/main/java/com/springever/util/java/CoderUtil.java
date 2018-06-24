package com.springever.util.java;

/**
 * 编码辅助工具类.
 * Created by lc3@yitong.com.cn on 2014/4/19.
 */
public class CoderUtil {

    private CoderUtil() {
    }

    /**
     * 字节数组转16进制字符串
     * @param arrB 字节数组
     * @return
     */
    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;

        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            while (intTmp < 0) {
                intTmp += 256;
            }
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 16进制字符串转字节数组
     * @param hexStr 16进制字符串
     * @return
     */
    public static byte[] hexstr2ByteArr(String hexStr) {
        if(null == hexStr) {
            return new byte[0];
        }
        int iLen = hexStr.length();

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen - 1; i += 2) {
            String strTmp = hexStr.substring(i, i + 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

}
