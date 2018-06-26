package com.springever.util.java.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
 AES128算法
 */
public class AesCbcUtils {

	public static final String KEY_ALGORITHM = "AES";
	// 加密模式为ECB，填充模式为NoPadding
	public static final String CIPHER_ALGORITHM = "AES/CBC/NoPadding";
	// 字符集
	public static final String ENCODING = "UTF-8";
	// 向量
	public static final String IV_SEED = "1234567812345678";

	/**
	 * AES加密算法
	 * 
	 * @param str 密文
	 * @param key 密key
	 * @return
	 */
	public static String encrypt(String str, String key) {
		try {
			if (str == null) {
				return null;
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				return null;
			}
			byte[] raw = key.getBytes(ENCODING);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] srawt = str.getBytes(ENCODING);
			int len = srawt.length;
			/* 计算补空格后的长度 */
			while (len % 16 != 0) {
				len++;
			}
			byte[] sraw = new byte[len];
			/* 在最后空格 */
			for (int i = 0; i < len; ++i) {
				if (i < srawt.length) {
					sraw[i] = srawt[i];
				} else {
					sraw[i] = 32;
				}
			}
			byte[] encrypted = cipher.doFinal(sraw);
			return formatString(new String(Base64.encode(encrypted,Base64.DEFAULT), "UTF-8"));
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * AES解密算法
	 * 
	 * @param str 密文
	 * @param key 密key
	 * @return
	 */
	public static String decrypt(String str, String key) {
		try {
			// 判断Key是否正确
			if (key == null) {
				return null;
			}
			// 判断Key是否为16位
			if (key.length() != 16) {
				return null;
			}
			byte[] raw = key.getBytes(ENCODING);
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] bytes = Base64.decode(str.getBytes("UTF-8"),Base64.DEFAULT);
			bytes = cipher.doFinal(bytes);
			return new String(bytes, ENCODING);
		} catch (Exception ex) {
			return null;
		}
	}

	private static String formatString(String sourceStr) {
		if (sourceStr == null) {
			return null;
		}
		return sourceStr.replaceAll("\\r", "").replaceAll("\\n", "");
	}

	public static void main(String[] args) {
		String aesKey = "abcdefghabcdefgh";
		String source = "abcdefghabcdef12";
		// 加密
		String encryptStr = encrypt(source, aesKey);
		System.out.println(encryptStr);
		// 解密
		String decryptStr = decrypt(encryptStr, aesKey);
		System.out.println(decryptStr);
	}

}