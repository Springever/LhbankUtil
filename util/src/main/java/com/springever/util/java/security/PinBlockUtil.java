package com.springever.util.java.security;

import com.springever.util.java.CoderUtil;

/**
 * Pin Block加解密工具类.
 * Created by lc3@yitong.com.cn on 2014/4/18.
 */
public class PinBlockUtil {

    /**
     * 标准ANSI X9.8 Format（带主帐号信息）的PIN BLOCK计算.
     * PIN BLOCK 格式等于 PIN 按位异或 主帐号.
     * @param pin 6位明文密码
     * @param accno 账号
     * @return 加密字节数组
     */
    public static byte[] encrypt(String pin, String accno) {
        byte arrAccno[] = getHAccno(accno);
        byte arrPin[] = getHPin(pin);
        byte arrRet[] = new byte[8];
        //PIN BLOCK 格式等于 PIN 按位异或 主帐号;
        for (int i = 0; i < 8; i++) {
            arrRet[i] = (byte)(arrPin[i] ^ arrAccno[i]);
        }
        return arrRet;
    }

    /**
     * PIN BLOCK 解密.
     * @param data 密串字节数组
     * @param accno 账号
     * @return 解密16进制字符串
     */
    public static String decrypt(byte[] data, String accno) {
        byte arrAccno[] = getHAccno(accno);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            byte b = (byte)(data[i + 1] ^ arrAccno[i + 1]);
            sb.append(toChar(b >> 4));
            sb.append(toChar(b & 0x0F));
        }
        return sb.toString();
    }

    /**
     * PIN BLOCK 解密.
     * @param hexStr 密串16进制字符串
     * @param accno 账号
     * @return 解密16进制字符串
     */
    public static String decrypt(String hexStr, String accno) {
        return decrypt(CoderUtil.hexstr2ByteArr(hexStr), accno);
    }


    /**
     * 对密码进行转换 PIN格式.
     * BYTE 1 PIN的长度
     * @param pin 明文密码
     * @return
     */
    private static byte[] getHPin(String pin) {
        byte arrPin[] = pin.getBytes();
        byte encode[] = new byte[8];
        encode[0] = 0x06;
        encode[1] = uniteBytes(arrPin[0], arrPin[1]);
        encode[2] = uniteBytes(arrPin[2], arrPin[3]);
        encode[3] = uniteBytes(arrPin[4], arrPin[5]);
        encode[4] = (byte) 0xFF;
        encode[5] = (byte) 0xFF;
        encode[6] = (byte) 0xFF;
        encode[7] = (byte) 0xFF;
        return encode;
    }


    /**
     * 对帐号进行转换.
     * BYTE 1 — BYTE 2 0X0000 BYTE 3 — BYTE 8 12个主帐号.
     * 取主帐号的右12位（不包括最右边的校验位），不足12位左补“0X00”.
     * @param accno 账号
     * @return
     */
    private static byte[] getHAccno(String accno) {
        //取出主帐号；
        int len = accno.length();
        byte arrTemp[] = accno.substring(len < 13 ? 0 : len - 13, len - 1).getBytes();
        byte arrAccno[] = new byte[12];
        for (int i = 0; i < 12; i++) {
            arrAccno[i] = (i <= arrTemp.length ? arrTemp[i] : (byte) 0x00);
        }
        byte encode[] = new byte[8];
        encode[0] = (byte) 0x00;
        encode[1] = (byte) 0x00;
        encode[2] = uniteBytes(arrAccno[0], arrAccno[1]);
        encode[3] = uniteBytes(arrAccno[2], arrAccno[3]);
        encode[4] = uniteBytes(arrAccno[4], arrAccno[5]);
        encode[5] = uniteBytes(arrAccno[6], arrAccno[7]);
        encode[6] = uniteBytes(arrAccno[8], arrAccno[9]);
        encode[7] = uniteBytes(arrAccno[10], arrAccno[11]);
        return encode;
    }

    /**
     * 合并字节.
     * @param h 高位字节
     * @param l 低位字节
     * @return
     */
    private static byte uniteBytes(byte h, byte l) {
        return (byte) (toByte(h) << 4 | toByte(l));
    }

    /**
     * 字母类型byte转普通byte.
     * @param c 字母型byte
     * @return
     */
    private static byte toByte(byte c) {
        return (byte) (c - '0');
    }

    /**
     * int类型转数字型字母.
     * @param b
     * @return
     */
    private static char toChar(int b) {
        if(b < 00) {
            b += 16;
        }
        return (char) (b + '0');
    }

}
