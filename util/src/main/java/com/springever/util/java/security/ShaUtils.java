package com.springever.util.java.security;

import android.util.Log;

import com.lhbank.commons.codec.digest.DigestUtils;
import com.lhbank.google.common.base.Charsets;
import com.lhbank.google.common.hash.Hashing;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShaUtils {

    /**
     * sha-1 返回40个字符
     * @param values
     * @return
     */
    public static String sign(List<String> values) {
        if (values == null) {
            throw new NullPointerException("values is null");
        }
        values.removeAll(Collections.singleton(null));// remove null
        java.util.Collections.sort(values);
        StringBuilder sb = new StringBuilder();
        for (String s : values) {
            sb.append(s);
        }
        return Hashing.sha1().hashString(sb, Charsets.UTF_8).toString()
                .toUpperCase();
    }

    /*
    sha-1 返回28个字符
     */
    public static String digest(String str) {
        String result = "";
        try {
            byte[] data = str.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            data = md.digest(data);
            result = new String(Base64.encode(data,Base64.DEFAULT));
        } catch (NoSuchAlgorithmException e) {
            Log.e("TAG", "Digest init ERROR!", e);
        } catch (UnsupportedEncodingException e) {
            Log.e("TAG", "Digest:encoder data ERROR!", e);
        }
        return result;
    }

    /**
     * sha256 返回64个字符
     * @param puk
     * @return
     */
    public static String sha256(String puk){
        return DigestUtils.sha256Hex(puk);
    }

    public static void main(String [] args){
        System.out.println(ShaUtils.digest("mao1122333jl"));
        List<String> list=new ArrayList<String>();
        list.add("mwoii9029292");
        list.add("w9099920i290932");
        list.add("892093902023im,nmkjirio");
        System.out.println(ShaUtils.sign(list));
        System.out.println(sha256("mao1122333jl"));
    }
}
