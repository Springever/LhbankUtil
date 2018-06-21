package com.springever.util.android.util.log.capture;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.springever.util.android.util.log.utils.Constants;
import com.springever.util.android.util.log.utils.LogCollectorUtility;
import com.springever.util.android.util.log.utils.LogHelper;

import android.content.Context;
import android.util.Log;

/**
 * 
 * @author maowenping 2016-05-10
 * @description 日志存放
 */
public class LogFileStorage {

	private static final String TAG = LogFileStorage.class.getName();

	public static final String LOG_SUFFIX = "." + Constants.FILE_TYPE;

	private static final String CHARSET = "UTF-8";

	private static LogFileStorage sInstance;

	private Context mContext;

	private String dir;

	private LogFileStorage(Context ctx) {
		mContext = ctx.getApplicationContext();
	}

	public static synchronized LogFileStorage getInstance(Context ctx) {
		if (ctx == null) {
			LogHelper.e(TAG, "Context is null");
			return null;
		}
		if (sInstance == null) {
			sInstance = new LogFileStorage(ctx);
		}
		return sInstance;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getDir() {
		return dir;
	}

	public File getUploadLogFile() {
		/*
		File dir = mContext.getFilesDir();
		File logFile = new File(dir, LogCollectorUtility.getMid(mContext)
				+ LOG_SUFFIX);
				*/
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd",
				Locale.getDefault());
		File logFile = new File(getDir() + fmt.format(new Date()) + "_error"
				+ LOG_SUFFIX);
		if (logFile.exists()) {
			return logFile;
		} else {
			return null;
		}
	}

	public boolean deleteUploadLogFile() {
		/*
		File dir = mContext.getFilesDir();
		File logFile = new File(dir, LogCollectorUtility.getMid(mContext)
				+ LOG_SUFFIX);
				*/
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd",
				Locale.getDefault());
		File logFile = new File(getDir() + fmt.format(new Date()) + "_error"
				+ LOG_SUFFIX);
		return logFile.delete();
	}

	public boolean saveLogFile2Internal(String logString) {
		try {
			/*
			 * File dir = mContext.getFilesDir(); if (!dir.exists()) {
			 * dir.mkdirs(); } File logFile = new File(dir,
			 * LogCollectorUtility.getMid(mContext) + LOG_SUFFIX);
			 */
			DateFormat fmt = new SimpleDateFormat("yyyyMMdd",
					Locale.getDefault());
			File file = new File(getDir() + fmt.format(new Date()) + "_error"
					+ LOG_SUFFIX);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file, true);
			fos.write(logString.getBytes(CHARSET));
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.e(TAG, "saveLogFile2Internal failed!");
			return false;
		}
		return true;
	}

	public boolean saveLogFile2SDcard(String logString, boolean isAppend) {
		/*
		if (!LogCollectorUtility.isSDcardExsit()) {
			LogHelper.e(TAG, "sdcard not exist");
			return false;
		}
		*/
		try {
			/*
			File logDir = getExternalLogDir();
			if (!logDir.exists()) {
				logDir.mkdirs();
			}

			File logFile = new File(logDir,
					LogCollectorUtility.getMid(mContext) + LOG_SUFFIX);
					*/
			/*
			 * if (!isAppend) { if (logFile.exists() && !logFile.isFile())
			 * logFile.delete(); }
			 */
			DateFormat fmt = new SimpleDateFormat("yyyyMMdd",
					Locale.getDefault());
			File logFile = new File(getDir() + fmt.format(new Date()) + "_error"
					+ LOG_SUFFIX);
			LogHelper.d(TAG, logFile.getPath());
			FileOutputStream fos = new FileOutputStream(logFile, isAppend);
			fos.write(logString.getBytes(CHARSET));
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "saveLogFile2SDcard failed!");
			return false;
		}
		return true;
	}

	/*
	private File getExternalLogDir() {
		File logDir = LogCollectorUtility.getExternalDir(mContext, "Log");
		LogHelper.d(TAG, logDir.getPath());
		return logDir;
	}
	*/
}
