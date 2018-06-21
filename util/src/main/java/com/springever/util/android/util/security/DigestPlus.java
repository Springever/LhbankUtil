package com.springever.util.android.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class DigestPlus {

	public static String digest(String str) {
		String result = "";
		try {
			byte[] data = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			data = md.digest(data);
			result = new BASE64Encoder().encode(data);
		} catch (NoSuchAlgorithmException e) {
			Log.e("TAG", "Digest init ERROR!", e);
		} catch (UnsupportedEncodingException e) {
			Log.e("TAG", "Digest:encoder data ERROR!", e);
		}
		return result;
	}
}
