package com.springever.util.java.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES Coder<br/>
 * secret key length: 128bit, default: 128 bit<br/>
 * mode: ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/
 * 
 * @author Aub
 * 
 */
public class AESCoder {

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	static String IV = "HRBBANKPUBLICVS1";

	/**
	 * 初始化密
	 * 
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static byte[] initSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大
		// AES 要求密钥长度128
		kg.init(128);
		// 生成��密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密
	 * @return 密钥
	 */
	public static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数
	 * @param key
	 *            密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数
	 * @param key
	 *            二进制密
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数
	 * @param key
	 *            二进制密
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数
	 * @param key
	 *            密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为加密模
		// cipher.init(Cipher.ENCRYPT_MODE, key);
		cipher.init(Cipher.ENCRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数
	 * @param key
	 *            二进制密
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key) throws Exception {
		return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数
	 * @param key
	 *            二进制密
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return decrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数
	 * @param key
	 *            密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// 实例
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为解密模
		cipher.init(Cipher.DECRYPT_MODE, key,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 解密（该方法用于键盘单字符迭代解密）
	 * 
	 * @param params
	 *            密文参数
	 * @return
	 */
	public static String decrypt(String params, String key) {
		StringBuffer result = new StringBuffer();
		String end = params;
		while (true) {
			try {
				// 执行解密
				Key k = AESCoder.toKey(key.getBytes());
				String decryptStr = new String(decrypt(
						BASE64Custom.decode(end), k));
				// 解密后数据为直接跳出请求
				if ((decryptStr == null) || (decryptStr.length() == 0))
					break;
				// 解密后字段只有一位，代表已经解密到最后一位，直接结束解密
				if (decryptStr.length() == 1) {
					// 拼接数据
					result.append(decryptStr);
					break;
				}
				// 为实际解密数
				result.append(decryptStr.substring(decryptStr.length() - 1));
				// 记录解密后去除真实数据的密文用于下次解密
				end = decryptStr.substring(0, decryptStr.length() - 1);
			} catch (Exception e) {
				// 异常跳出循环
				break;
			}
		}

		// 反致数据
		return result.reverse().toString();
	}

	private static String showByteArray(byte[] data) {
		if (null == data) {
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		for (byte b : data) {
			sb.append(b).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {

		Key k = AESCoder.toKey("1234567890123456".getBytes());

		String data = "640200010000000604";
		System.out.println("加密前数 string:" + data);
		System.out.println("加密前数 字节:" + data.getBytes().length);
		System.out.println("加密前数 byte[]:" + showByteArray(data.getBytes()));
		System.out.println();

		byte[] encryptData = AESCoder.encrypt(data.getBytes(), k);
		System.out.println("加密后数 byte[]:" + showByteArray(encryptData));
		System.out.println("加密后数 字节:" + encryptData.length);
		System.out.println("加密后数 hexStr:" + BASE64Custom.encode(encryptData));
		System.out.println();
		byte[] decryptData = AESCoder.decrypt(encryptData, k);
		System.out.println("解密后数 byte[]:" + showByteArray(decryptData));
		System.out.println("解密后数 string:" + new String(decryptData));

	}
}