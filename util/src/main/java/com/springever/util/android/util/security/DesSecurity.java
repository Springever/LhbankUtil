package com.springever.util.android.util.security;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/***
 * 与IPHONE android匹配的DES加密算法
 * 
 */
public class DesSecurity {
	// 默认 初始化向量
	private String initVec = "NJCB-P&C";
	// 默认 初始化密钥
	private String initKey = "NJCB-P&C";

	private BASE64Custom base64;
	private Cipher enCipher;
	private Cipher deCipher;

	public static void main(String[] args) {
		try {
			String test = "{'DEVICE_INFO':'356409046312768|SH12DPL00333|android 2.3.7','PHONE_NO':'13822222222','OS_VERSION':'2.3.7','LOGIN_TYPE':'1','TransId':'ClientLogin','DEVICE_DIGEST':'E8Aq\\/anVagUov8CLabrKrBJXqlQ=','APP_VERSION':'1.4','DEVICE_TYPE':'android','PASSWORD':'21218cca77804d2ba1922c33e0151105','ImageCoder':'0'}";
			// 默认密钥
			DesSecurity des = new DesSecurity();
			// System.out.println("加密前的字符：" + test);
			// System.out.println("加密后的字符：" + des.encrypt(test));
			// System.out.println("解密后的字符：" + des.decrypt(des.encrypt(test),
			// "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化加密对象
	 * 
	 * @throws Exception
	 */
	public DesSecurity() throws Exception {
		if (initKey == null) {
			throw new NullPointerException("Parameter is null!");
		}
		initCipher(initKey.getBytes(), initVec.getBytes());
	}

	/**
	 * 初始化加密对象
	 * 
	 * @param key
	 *            加密密钥 必须为8位
	 * @param iv
	 *            初始化向量 必须为8位
	 * @throws Exception
	 */
	public DesSecurity(String key, String iv) throws Exception {
		if (key == null) {
			throw new NullPointerException("Parameter is null!");
		}
		initCipher(key.getBytes(), iv.getBytes());
	}

	private void initCipher(byte[] secKey, byte[] secIv) throws Exception {
		// 获得DES密钥
		DESKeySpec dks = new DESKeySpec(secKey);
		// 获得DES加密密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 生成加密密钥
		SecretKey key = keyFactory.generateSecret(dks);
		// 创建初始化向量对象
		IvParameterSpec iv = new IvParameterSpec(secIv);
		AlgorithmParameterSpec paramSpec = iv;
		// 为加密算法指定填充方式，创建加密会话对象
		enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 为加密算法指定填充方式，创建解密会话对象
		deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 初始化加解密会话对象
		enCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
		deCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		base64 = new BASE64Custom();
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密二进制数据
	 * @return 经BASE64编码过的加密数据
	 * @throws Exception
	 */
	public String encrypt(byte[] data) throws Exception {
		return base64.encode(enCipher.doFinal(data));
	}

	/**
	 * 加密字符串
	 * 
	 * @param strIn
	 *            需加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public String encrypt(String strIn) throws Exception {
		return encrypt(strIn.getBytes()).replace(" ", "");
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密字符串（经过BASE64编码）
	 * @return 解密后的二进制数据
	 * @throws Exception
	 */
	public byte[] decrypt(String data) throws Exception {
		return deCipher.doFinal(base64.decode(data));
	}

	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 * @param character
	 *            构建字符串编码
	 * @throws Exception
	 */
	public String decrypt(String strIn, String character) throws Exception {
		return new String(decrypt(strIn), character);
	}
}
