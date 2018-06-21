package com.springever.util.android.util.log.upload;

import com.lhbank.http.HttpEntity;
import com.lhbank.http.HttpResponse;
import com.lhbank.http.HttpStatus;
import com.lhbank.http.HttpVersion;
import com.lhbank.http.StatusLine;
import com.lhbank.http.client.HttpClient;
import com.lhbank.http.client.methods.HttpPost;
import com.lhbank.http.conn.ClientConnectionManager;
import com.lhbank.http.conn.scheme.PlainSocketFactory;
import com.lhbank.http.conn.scheme.Scheme;
import com.lhbank.http.conn.scheme.SchemeRegistry;
import com.lhbank.http.conn.ssl.SSLSocketFactory;
import com.lhbank.http.entity.ByteArrayEntity;
import com.lhbank.http.impl.client.DefaultHttpClient;
import com.lhbank.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import com.lhbank.http.params.BasicHttpParams;
import com.lhbank.http.params.HttpConnectionParams;
import com.lhbank.http.params.HttpParams;
import com.lhbank.http.params.HttpProtocolParams;
import com.lhbank.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * @author maowenping 2016-05-11
 * @description HTTP
 */
public class HttpManager {

	private static final int SET_CONNECTION_TIMEOUT = 5 * 1000;
	private static final int SET_SOCKET_TIMEOUT = 20 * 1000;

	private static final String BOUNDARY = getBoundry();// UUID.randomUUID().toString();
	private static final String MP_BOUNDARY = "--" + BOUNDARY;
	private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--";
	private static final String LINEND = "\r\n";

	private static final String CHARSET = "UTF-8";

	public static String uploadFile(String url, HttpParameters params,
			File logFile) throws IOException {

		HttpClient client = getHttpClient();

		HttpPost post = new HttpPost(url);

		ByteArrayOutputStream bos = null;

		FileInputStream logFileInputStream = null;

		String result = null;

		try {

			bos = new ByteArrayOutputStream();

			if (params != null) {
				String key = "";
				for (int i = 0; i < params.size(); i++) {
					key = params.getKey(i);
					StringBuilder temp = new StringBuilder(10);
					temp.setLength(0);
					temp.append(MP_BOUNDARY).append(LINEND);
					temp.append("content-disposition: form-data; name=\"")
							.append(key).append("\"").append(LINEND + LINEND);
					temp.append(params.getValue(key)).append(LINEND);
					bos.write(temp.toString().getBytes());
				}
			}

			StringBuilder temp = new StringBuilder();
			temp.append(MP_BOUNDARY).append(LINEND);
			temp.append(
					"content-disposition: form-data; name=\"logfile\"; filename=\"")
					.append(logFile.getName()).append("\"").append(LINEND);
			temp.append("Content-Type: application/octet-stream; charset=utf-8")
					.append(LINEND + LINEND);
			bos.write(temp.toString().getBytes());
			logFileInputStream = new FileInputStream(logFile);
			byte[] buffer = new byte[1024 * 8];// 8k
			while (true) {
				int count = logFileInputStream.read(buffer);
				if (count == -1) {
					break;
				}
				bos.write(buffer, 0, count);
			}

			bos.write((LINEND + LINEND).getBytes());
			bos.write((END_MP_BOUNDARY + LINEND).getBytes());

			ByteArrayEntity formEntity = new ByteArrayEntity(bos.toByteArray());
			post.setEntity(formEntity);
			HttpResponse response = client.execute(post);
			StatusLine status = response.getStatusLine();
			int statusCode = status.getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				result = readHttpResponse(response);
			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					throw e;
				}
			}
			if (logFileInputStream != null) {
				try {
					logFileInputStream.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}

		return result;
	}

	private static String readHttpResponse(HttpResponse response) {
		String result = null;
		HttpEntity entity = response.getEntity();
		InputStream inputStream;

		try {
			inputStream = entity.getContent();
			ByteArrayOutputStream content = new ByteArrayOutputStream();
			int readBytes = 0;
			byte[] sBuffer = new byte[512];
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
				content.write(sBuffer, 0, readBytes);
			}
			result = new String(content.toByteArray(), CHARSET);
			return result;

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	private static HttpClient getHttpClient() {

		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			HttpParams params = new BasicHttpParams();

			HttpConnectionParams.setConnectionTimeout(params, 10000);
			HttpConnectionParams.setSoTimeout(params, 10000);

			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			HttpConnectionParams.setConnectionTimeout(params,
					SET_CONNECTION_TIMEOUT);
			HttpConnectionParams.setSoTimeout(params, SET_SOCKET_TIMEOUT);
			HttpClient client = new DefaultHttpClient(ccm, params);
			return client;
		} catch (Exception e) {
			// e.printStackTrace();
			return new DefaultHttpClient();
		}
	}

	private static class MySSLSocketFactory extends SSLSocketFactory {

		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);

			TrustManager tm = new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}
			};

			sslContext.init(null, new TrustManager[] { tm }, null);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
				boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

	}

	private static String getBoundry() {
		StringBuffer _sb = new StringBuffer();
		for (int t = 1; t < 12; t++) {
			long time = System.currentTimeMillis() + t;
			if (time % 3 == 0) {
				_sb.append((char) time % 9);
			} else if (time % 3 == 1) {
				_sb.append((char) (65 + time % 26));
			} else {
				_sb.append((char) (97 + time % 26));
			}
		}
		return _sb.toString();
	}
}
