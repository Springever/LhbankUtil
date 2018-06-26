package com.springever.util.java.security;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密、
 * 
 * SHA1withRSA解密
 * AES128
 * 
 * 
 */
public class AesUtils {

	private static final char[] BCD_LOOKUP = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * AES解密.
	 * 
	 * @param content
	 *            待解密内容.
	 * @param password
	 *            解密密码.
	 * @return
	 */
	public static String decrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(bytesToHexStr(Base64.decode(password,Base64.DEFAULT)).getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodedFormat = secretKey.getEncoded();
			SecretKeySpec keySpec = new SecretKeySpec(enCodedFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] contents = Base64.decode(content,Base64.DEFAULT);
			byte[] result = cipher.doFinal(contents);
			return new String(result, "utf-8");
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(bytesToHexStr(Base64.decode(password,Base64.DEFAULT)).getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			byte[] byteContent = content.getBytes("utf-8");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return new String(Base64.encode(result,Base64.DEFAULT));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Transform the specified byte into a Hex String form.
	 */
	public static final String bytesToHexStr(byte[] bcd) {
		StringBuffer s = new StringBuffer(bcd.length * 2);
		for (int i = 0; i < bcd.length; i++) {
			s.append(BCD_LOOKUP[(bcd[i] >>> 4) & 0x0f]);
			s.append(BCD_LOOKUP[bcd[i] & 0x0f]);
		}
		return s.toString();
	}

	/**
	 * Transform the specified Hex String into a byte array.
	 */
	public static final byte[] hexStrToBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
