package com.springever.util.android.util.security;

import java.io.IOException;

public class Base64DataException extends IOException {
	/**
	 * @Description
	 * @Author lewis(lgs@yitong.com.cn) 2014-3-9 下午3:24:24
	 */
	private static final long serialVersionUID = 3083522395718859773L;

	public Base64DataException(String detailMessage) {
		super(detailMessage);
	}
}