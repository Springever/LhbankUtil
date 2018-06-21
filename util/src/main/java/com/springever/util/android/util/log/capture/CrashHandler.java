package com.springever.util.android.util.log.capture;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.URLEncoder;

import com.springever.util.android.util.log.utils.Constants;
import com.springever.util.android.util.log.utils.LogCollectorUtility;
import com.springever.util.android.util.log.utils.LogHelper;

import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.Base64;

/**
 * @author maowenping 2016-05-20
 * @description android崩溃
 */
public class CrashHandler implements UncaughtExceptionHandler {

	private static final String TAG = CrashHandler.class.getName();

	private static final String CHARSET = "UTF-8";

	private static CrashHandler sInstance;

	private Context mContext;

	private UncaughtExceptionHandler mDefaultCrashHandler;

	String appVerName;

	String appVerCode;

	String OsVer;

	String vendor;

	String model;

	String mid;

	private CrashHandler(Context c) {
		mContext = c.getApplicationContext();
		appVerName = "应用版本号(字符):" + LogCollectorUtility.getVerName(mContext);
		appVerCode = "应用版本号(数字):" + LogCollectorUtility.getVerCode(mContext);
		OsVer = "操作系统版本:" + Build.VERSION.RELEASE;
		vendor = "客户端制造商:" + Build.MANUFACTURER;
		model = "客户端型号:" + Build.MODEL;
		mid = "设备唯一号:" + LogCollectorUtility.getMid(mContext);
	}

	public static CrashHandler getInstance(Context c) {
		if (c == null) {
			LogHelper.e(TAG, "Context is null");
			return null;
		}
		if (sInstance == null) {
			sInstance = new CrashHandler(c);
		}
		return sInstance;
	}

	public void init() {
		if (mContext == null) {
			return;
		}
		boolean b = LogCollectorUtility.hasPermission(mContext);
		if (!b) {
			return;
		}
		mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex);
		ex.printStackTrace();
		if (mDefaultCrashHandler != null) {
			mDefaultCrashHandler.uncaughtException(thread, ex);
		} else {
			Process.killProcess(Process.myPid());
		}
	}

	private void handleException(Throwable ex) {
		String s = fomatCrashInfo(ex);
		// String bes = fomatCrashInfoEncode(ex);
		LogHelper.d(TAG, s);
		// LogHelper.d(TAG, bes);
		// LogFileStorage.getInstance(mContext).saveLogFile2Internal(bes);
		LogFileStorage.getInstance(mContext).saveLogFile2Internal(s);
		if (Constants.DEBUG) {
			LogFileStorage.getInstance(mContext).saveLogFile2SDcard(s, true);
		}
	}

	private String fomatCrashInfo(Throwable ex) {
		String lineSeparator = "\r\n";
		StringBuilder sb = new StringBuilder();
		String logTime = "发生时间:" + LogCollectorUtility.getCurrentTime();
		// String exception = "异常情况:" + ex.toString();
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);
		String dump = info.toString();
		// String crashMD5 = "崩溃MD5:"
		// + LogCollectorUtility.getMD5Str(dump);
		// String crashDump = "crashDump:" + "{" + dump + "}";
		String crashDump = "异常情况:" + "{" + dump + "}";
		printWriter.close();
		sb.append(logTime).append(lineSeparator);
		sb.append(appVerName).append(lineSeparator);
		sb.append(appVerCode).append(lineSeparator);
		sb.append(OsVer).append(lineSeparator);
		sb.append(vendor).append(lineSeparator);
		sb.append(model).append(lineSeparator);
		sb.append(mid).append(lineSeparator);
		// sb.append(exception).append(lineSeparator);
		// sb.append(crashMD5).append(lineSeparator);
		sb.append(crashDump).append(lineSeparator).append(lineSeparator)
				.append(lineSeparator);
		;
		return sb.toString();
	}

	private String fomatCrashInfoEncode(Throwable ex) {
		String lineSeparator = "\r\n";
		StringBuilder sb = new StringBuilder();
		String logTime = "logTime:" + LogCollectorUtility.getCurrentTime();
		String exception = "exception:" + ex.toString();
		Writer info = new StringWriter();
		PrintWriter printWriter = new PrintWriter(info);
		ex.printStackTrace(printWriter);
		String dump = info.toString();
		String crashMD5 = "crashMD5:" + LogCollectorUtility.getMD5Str(dump);
		try {
			dump = URLEncoder.encode(dump, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String crashDump = "crashDump:" + "{" + dump + "}";
		printWriter.close();
		sb.append("&start---").append(lineSeparator);
		sb.append(logTime).append(lineSeparator);
		sb.append(appVerName).append(lineSeparator);
		sb.append(appVerCode).append(lineSeparator);
		sb.append(OsVer).append(lineSeparator);
		sb.append(vendor).append(lineSeparator);
		sb.append(model).append(lineSeparator);
		sb.append(mid).append(lineSeparator);
		sb.append(exception).append(lineSeparator);
		sb.append(crashMD5).append(lineSeparator);
		sb.append(crashDump).append(lineSeparator);
		sb.append("&end---").append(lineSeparator).append(lineSeparator)
				.append(lineSeparator);
		String bes = Base64.encodeToString(sb.toString().getBytes(),
				Base64.NO_WRAP);
		return bes;
	}
}
