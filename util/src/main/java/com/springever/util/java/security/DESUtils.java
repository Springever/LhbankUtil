package com.springever.util.java.security;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtils {
    private static final String CHARSET = "UTF-8";
    private static final String DES = "DES";
    private static final String KEY = "CSII-DES";
    private static SecretKey secretkey = null;

    public DESUtils() {
    }

    private static Key getKey() {
        if (secretkey == null) {
            Object var0 = null;

            try {
                byte[] bb = "CSII-DES".getBytes("UTF-8");
                secretkey = new SecretKeySpec(bb, "DES");
            } catch (Exception var2) {
                ;
            }
        }

        return secretkey;
    }

    public static String encrypt(String source) {
        if (source != null && !"".equals(source)) {
            String s = null;
            Object var2 = null;

            try {
                byte[] center = source.getBytes("UTF-8");
                Key key = getKey();
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(1, key);
                byte[] target = cipher.doFinal(center);
                s = android.util.Base64.encodeToString(target, 0);
                return s;
            } catch (Exception var6) {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String decrypt(String source) {
        if (source != null && !"".equals(source)) {
            String s = null;
            Object var2 = null;

            try {
                byte[] center = Base64.decode(source.getBytes("UTF-8"), 0);
                Key key = getKey();
                Cipher cipher = Cipher.getInstance("DES");
                cipher.init(2, key);
                byte[] dissect = cipher.doFinal(center);
                s = new String(dissect, "UTF-8");
                return s;
            } catch (Exception var6) {
                return "";
            }
        } else {
            return "";
        }
    }
}
