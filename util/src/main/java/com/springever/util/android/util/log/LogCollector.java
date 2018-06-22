package com.springever.util.android.util.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.springever.util.android.util.log.capture.CrashHandler;
import com.springever.util.android.util.log.capture.LogFileStorage;
import com.springever.util.android.util.log.upload.HttpParameters;
import com.springever.util.android.util.log.upload.UploadLogManager;
import com.springever.util.android.util.log.utils.Constants;
import com.springever.util.android.util.log.utils.LogCollectorUtility;
import com.springever.util.android.util.log.utils.LogHelper;

/**
 * @author maowenping 2016-05-10
 * @description 日志收集主类
 */
public class LogCollector {

	private static final String TAG = LogCollector.class.getName();

	private static String Upload_Url;

	private static Context mContext;

	private static HttpParameters mParams;

	private static String dir;// 日志目录
	static {
		if (isExistSDCard()) {
			dir = Environment.getExternalStorageDirectory()
					.getAbsolutePath()+"/LhbankLog/";
		} else {
			dir = "/data/data/" + "com.lhbank.hrbydyx" + "/LhbankLog/";
		}
	}

	/**
	 * @param ctx
	 *            上下文
	 * @return true成功;false表示ctx为空或者pDir所代表的目录不存在
	 * @description 输出日志格式为：发生时间:2016-05-20 17:43:35.949 应用版本号(字符):1.0
	 *              应用版本号(数字):1 操作系统版本:5.0.2 客户端制造商:samsung 客户端型号:SM-T815C
	 *              设备唯一号:dcc98aeec4e027ed8bb81463e2009a9f
	 *              异常情况:java.lang.NullPointerException:XXXXXXX
	 *              文件名格式：时间_机构编号_用户编号_debug.txt，例如：20160510_error.txt
	 *              调用例子：LogCollector.init(getApplicationContext(),
	 *              "/mnt/sdcard/HrbYdyx/hrb_file/log/",false);
	 * 
	 */
	public static boolean init(Context ctx){
		if (ctx == null) {
			return false;
		}
        try {
            initCreateFiles(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDebugMode(false);
		mContext = ctx;
		LogFileStorage.getInstance(mContext).setDir(dir);
		CrashHandler crashHandler = CrashHandler.getInstance(ctx);
		crashHandler.init();
		return true;
	}

	/**
	 * 首次进入应用的时候将各种目录创建好
	 *
	 * @Description
	 * @throws IOException
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-9-12
	 */
	public static void initCreateFiles(String logpath) throws IOException {
		if (isExistSDCard()) {
			String[] paths = new String[] { logpath };
			int size = paths.length;
			for (int i = 0; i < size; i++) {
				String path = paths[i];
				File file = new File(path);
				if (!file.exists()) {
					file.mkdir();
				}
			}
		} else {
		}
	}

	/**
	 * 检测SD卡是否存在
	 *
	 * @Description
	 * @return
	 * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-9-12
	 */
	public static boolean isExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}

	/**
	 * 获取本应用android客户端logcat日志
	 */
	public static void getLogcat(){
		DateFormat fmt2 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		String path = dir + fmt2.format(new Date());
		try {
			ArrayList commandLine = new ArrayList();
			commandLine.add("logcat");
			commandLine.add("-d");//使用该参数可以让logcat获取日志完毕后终止进程
			commandLine.add("-v");
			commandLine.add("time");
			commandLine.add("-f");//如果使用commandLine.add(">");是不会写入文件，必须使用-f的方式
			commandLine.add(path + "_logcat.txt");
			Runtime.getRuntime().exec((String[]) commandLine.toArray(new String[commandLine.size()]));
		}catch (Exception e){

		}
	}
	/**
	 * @author maowenping 2016-05-23
	 * @param day
	 *            相距多少天（负数则是前多少天；正数则是将来多少天）；例如0表示当天
	 * @return android崩溃日志路径;null表示路径不存在
	 */
	public static String getErrorLogPath(long day) {
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		Date date = getDateFutureOrBefore(fmt.format(new Date()), day);
		String filePath = dir + fmt.format(date) + "." + "_error"
				+ Constants.FILE_TYPE;
		if (new File(filePath).exists()) {
			return filePath;
		} else {
			return null;
		}
	}

	/**
	 * @author maowenping 2016-05-23
	 * @param userid
	 *            用户账号
	 * @param orgid
	 *            用户机构
	 * @param day
	 *            相距多少天(负数则是前多少天;正数则是将来多少天);例如0表示当天
	 * @return android应用日志路径(Debug);null表示路径不存在或传入的参数为空
	 */
	public static String getDebugLogPath(String userid, String orgid, long day) {
		if (userid == null || userid.length() == 0
				|| userid.trim().length() == 0) {
			return null;
		}
		if (orgid == null || orgid.length() == 0 || orgid.trim().length() == 0) {
			return null;
		}
		DateFormat fmt = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		Date date = getDateFutureOrBefore(fmt.format(new Date()), day);
		String filePath = dir + fmt.format(date) + "_" + orgid + "_" + userid
				+ "_debug." + Constants.FILE_TYPE;
		if (new File(filePath).exists()) {
			return filePath;
		} else {
			return null;
		}
	}

	private static HttpParameters getHttpParams(Map<String, String> params) {
		HttpParameters httpParams = new HttpParameters();
		String key;
		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {
			key = it.next();
			httpParams.add(key, params.get(key));
		}
		return httpParams;
	}

	/**
	 * @author maowenping 2016-05-23
	 * @description wifi情况下才能上传
	 * @param isWifiOnly
	 *            wifi是否已开;true为开、false为没开
	 * @param upload_url
	 *            例如：http://130.1.11.177:9080/mmp/json/uploadLog/uploadLog.do
	 * @param params
	 *            请求参数;例如 Map为a:A,b:B;则请求参数为a=A&b=B
	 * @return 
	 *         false表示没有初始化(调用init方法)或者upload_url为空、空白或者网络不可用或者wifi不可用(isWifiOnly为true
	 *         )
	 */
	public static boolean upload(boolean isWifiOnly, String upload_url,
			Map<String, String> params) {
		if (mContext == null || Upload_Url == null || upload_url.length() == 0
				|| upload_url.trim().length() == 0) {
			Log.d(TAG, "please check if init() or not");
			return false;
		}
		if (!LogCollectorUtility.isNetworkConnected(mContext)) {
			return false;
		}
		boolean isWifiMode = LogCollectorUtility.isWifiConnected(mContext);
		if (isWifiOnly && !isWifiMode) {
			return false;
		}
		Upload_Url = upload_url;
		mParams = getHttpParams(params);
		UploadLogManager.getInstance(mContext).uploadLogFile(Upload_Url,
				mParams);
		return true;
	}

	/**
	 * @author maowenping 2016-05-23
	 * @description 只要有网就可以上传
	 *            wifi是否已开;true为开、false为没开
	 * @param upload_url
	 *            例如：http://130.1.11.177:9080/mmp/json/uploadLog/uploadLog.do
	 * @param params
	 *            请求参数;例如 Map为a:A,b:B;则请求参数为a=A&b=B
	 * @return false表示没有初始化(调用init方法)或者upload_url为空、空白或者网络不可用
	 */
	public static void upload(String upload_url, Map<String, String> params) {
		if (mContext == null || Upload_Url == null || upload_url.length() == 0
				|| upload_url.trim().length() == 0) {
			Log.d(TAG, "please check if init() or not");
			return;
		}
		if (!LogCollectorUtility.isNetworkConnected(mContext)) {
			return;
		}
		Upload_Url = upload_url;
		mParams = getHttpParams(params);
		UploadLogManager.getInstance(mContext).uploadLogFile(Upload_Url,
				mParams);
	}

	private static void setDebugMode(boolean isDebug) {
		Constants.DEBUG = isDebug;
		LogHelper.enableDefaultLog = isDebug;
	}

	/**
	 * 
	 * @param className
	 *            类名
	 * @param userid
	 *            用户账号
	 * @param orgid
	 *            用户机构
	 * @param str
	 *            要输出的日志信息
	 * @return 返回日志路径
	 * @description 输出日志格式为：2016-05-10 12:01:01.111:MainAActivity:日志信息
	 *              文件名格式：时间_机构编号_用户编号_debug.txt，例如：20160510_0100_6801_debug.txt
	 */
	public static String writeLog(String className, String userid,
			String orgid, String str) {
		if (userid == null || userid.length() == 0
				|| userid.trim().length() == 0) {
			userid = "9999";
		}
		if (orgid == null || orgid.length() == 0 || orgid.trim().length() == 0) {
			orgid = "9999";
		}
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
				Locale.getDefault());
		DateFormat fmt2 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		StringBuilder sb = new StringBuilder();
		sb.append(fmt.format(new Date()) + ":" + className + ":" + str + "\r\n");
		String path = dir + fmt2.format(new Date()) + "_" + orgid + "_"
				+ userid + "_debug." + Constants.FILE_TYPE;
		File file = new File(path);
		PrintWriter bw = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			bw = new PrintWriter(new FileWriter(file, true));
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch (IOException e) {

		} finally {

		}
		getLogcat();
		return path;
	}

	/**
	 * @author maowenping 2015-12-12
	 * @description 日期+几天；日期-几天
	 * @return
	 */
	public static Date getDateFutureOrBefore(String sj1, long day) {
		sj1 = sj1.replaceAll("-|\\/", "").trim();
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd",
				Locale.getDefault());
		Date mydate = null;
		try {
			Date date = myFormatter.parse(sj1);
			mydate = new Date((date.getTime() + day * 24 * 60 * 60 * 1000));
		} catch (Exception e) {
			return null;
		}
		return mydate;
	}
}
