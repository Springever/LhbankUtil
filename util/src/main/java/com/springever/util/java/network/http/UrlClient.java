package com.springever.util.java.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class UrlClient {

	public boolean execute(String url, String msg) {
		HttpURLConnection httpConn = null;
		PrintWriter out = null;
		BufferedReader in = null;
		String responseStr = null;
		try {
			URL urlClient = new URL(url);
			httpConn = (HttpURLConnection) urlClient.openConnection();
			setHttpConnection(httpConn);
			showHttpRequestHeaders(httpConn);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.print(msg);
			out.flush();

			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			boolean firstLine = true;
			while ((line = in.readLine()) != null) {
				if (firstLine) {
					firstLine = false;
				} else {
					sb.append("\n");
				}
				sb.append(line);
			}
			responseStr = sb.toString();
			showHttpResponseHeaders(httpConn);
			return true;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
			// logger.error("mb io is error", e);
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}

	/**
	 * 测试请求头
	 * 
	 * @param httpConn
	 */
	@SuppressWarnings("unused")
	private void showHttpResponseHeaders(HttpURLConnection httpConn) {
		Map<String, List<String>> map = httpConn.getHeaderFields();
		for (String key : map.keySet()) {
			//显示头
		}
	}

	/**
	 * 请求头
	 * 
	 * @param httpConn
	 */
	@SuppressWarnings("unused")
	private void showHttpRequestHeaders(HttpURLConnection httpConn) {
		Map<String, List<String>> map = httpConn.getRequestProperties();
		for (String key : map.keySet()) {
			//显示头
		}
	}

	/**
	 * 设置请求属性信息
	 * 
	 * @param httpConn
	 * @throws ProtocolException
	 */
	private void setHttpConnection(HttpURLConnection httpConn)
			throws ProtocolException {
		httpConn.setRequestMethod("POST");//GET
		httpConn.setConnectTimeout(5000);
		httpConn.setReadTimeout(5000);
		httpConn.setDoOutput(true);// 是否打开输出流 true|false
		httpConn.setDoInput(true);// 是否打开输入流true|false
		httpConn.setUseCaches(false);// 是否缓存true|false
		httpConn.setRequestProperty("Connection", "keep-alive");//保持TCP会话连接
		httpConn.setRequestProperty("Content-Type",
				"text/html,text/plan,text/xml; charset=UTF-8");
		httpConn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml,application/do;");
		httpConn.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.803.0 Safari/535.1");
	}

}
