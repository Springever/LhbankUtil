package com.springever.util.android.util.security;

import java.util.Random;

import android.app.Application;
import android.util.Log;

/**
 * JNI工具类，对重要算法及加密相关的代码进行保护
 * 
 * 
 */
public class CryptoUtil {
	private static final String TAG = "CryptoUtil";

	private CryptoUtil() {
	}

	public static CryptoUtil instance = new CryptoUtil();

	public static CryptoUtil getInstance() {
		return instance;
	}

	/**
	 * AES加密
	 * 
	 * @param app
	 * @param key
	 * @param datas
	 * @return
	 */
	private native byte[] aesEncode(Application app, byte[] key, byte[] datas);

	/**
	 * 获取应用签名
	 * 
	 * @param ctx
	 * @param onceCode
	 * @return
	 */
	private native byte[] getAppSignInfo(Application app, String onceCode,
			byte[] key);

	static {
		System.loadLibrary("pnc-crypto");
	}

	/**
	 * 获取应用包签名
	 * 
	 * @param ctx
	 * @param onceCode
	 * @return
	 */
	public static String getApkSignInfo(Application app, String onceCode,
			String key) {
		String str = null;
		try {
			if (key.length() != 16) {
				Log.i(TAG, "key length need equals 16");
				return str;
			}
			byte[] dataArr = getInstance().getAppSignInfo(app, onceCode,
					key.getBytes());
			Log.d(TAG, "BASE64加密前：" + new String(dataArr));
			str = Converts.base64ToString(dataArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return str;
	}

	// 随机数种子
	final static char[] CHARS_ALL = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };
	// 分隔符
	final static char CHAR_SPLIT = 29;

	/**
	 * 加密报文， 内容为：数据摘要+加密报文+加密密钥
	 * 
	 * @param app
	 *            应用程序
	 * @param str
	 * @return
	 */
	public static String encryptData(Application app, String str) {
		String encryptData = "";
		// 随机密钥
		Random rnd = new Random();
		StringBuilder keyStrBui = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			// 33到126即可实现键盘上所有单字符的随机种子
			keyStrBui.append(String.valueOf(CHARS_ALL[rnd.nextInt(62)]));
		}
		String keyStr = keyStrBui.toString();
		try {
			// 数据摘要
			String strMD5 = MD5.md5(keyStr + str);
			byte[] keyArr = keyStr.getBytes("utf-8");
			byte[] dataArr = str.getBytes("utf-8");
			byte[] encryptArr = getInstance().aesEncode(app, keyArr, dataArr);
			// 加密报文
			String strAESC = Converts.base64ToString(encryptArr);
			// 加密密钥
			String encryptKeyStr = RSACerPlus.getInstance(app)
					.doEncrypt(keyStr);
			// 内容拼接
			encryptData = strMD5 + CHAR_SPLIT + strAESC + CHAR_SPLIT
					+ encryptKeyStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptData;
	}

}
