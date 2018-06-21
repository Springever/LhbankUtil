package com.springever.util.android.util;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lhbank.client.sdk.core.util.NetWorkUtil;
import com.lhbank.client.sdk.core.util.SharedPreUtil;
import com.lhbank.client.sdk.core.util.Util;
import com.springever.util.android.util.log.LogCollector;
import com.springever.util.android.util.log.capture.CrashHandler;
import com.springever.util.android.util.log.utils.LogHelper;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

/**
 * @author maowenping 2018-06-16 客户端数据采集送到后台
 */
public class LhAppDaqRequestUtils {

    private static LhAppDaqRequestUtils sInstance;

    private Context activity;

    private SharedPreUtil sharedPreUtil;

    private String url;

    private String cifseq;

    private String mobile;

    private LhAppDaqRequestUtils(Context activity, String url, String cifseq, String mobile) {
        this.activity = activity;
        this.url = url;
        this.cifseq = cifseq;
        this.mobile = mobile;
        this.sharedPreUtil = new SharedPreUtil(activity);
        LogCollector.init(activity);
    }

    /**
     * @param activity 上下文
     * @param url      请求连接,如https//m.wegobank.cn/pmobile/
     * @param cifseq   手机银行电子客户号（非核心客户号）
     * @param mobile   客户手机号
     * @return
     */
    public static LhAppDaqRequestUtils getInstance(Context activity, String url, String cifseq, String mobile) {
        if (sInstance == null) {
            sInstance = new LhAppDaqRequestUtils(activity, url, cifseq, mobile);
        }
        return sInstance;
    }

    /**
     * 采集硬件信息
     */
    public void requestClientHandWareInfo() {
        LogCollector.writeLog("LoginFragment", mobile, cifseq, "进入requestClientHandWareInfo");
        sharedPreUtil.setString("LhAppDaqRequestUtils_cifseq",url);
        sharedPreUtil.setString("LhAppDaqRequestUtils_cifseq",cifseq);
        sharedPreUtil.setString("LhAppDaqRequestUtils_mobile",mobile);
        activity.sendBroadcast(new Intent("android.intent.action.REQUEST_HANDWARE_INFO_BROADCAST"));
    }

    /**
     * 获取设备UUID
     *
     * @param activity
     * @return
     */
    public String getUUID(Context activity) {
        UuidUtils.buidleID(activity).check();
        String uuid = UuidUtils.getUUID();//设备唯一标识
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * @param activity  上下文
     * @param url       请求连接,如https://ip:port/xx.do
     * @param paramJson json形成的字符串
     */
    public void requestPmobileServer(Context activity, String url, String paramJson) {
        NetWorkUtil.getInstance(activity)
                .requestPost(url, new Object(), paramJson, new NetWorkUtil.ResultCallBack() {

                    @Override
                    public void onSuccess(Object Response) {
                        if (Response != null) {
                            JSONObject data = null;
                            String returncode = "";
                            try {
                                data = new JSONObject(Response.toString());
                                returncode = data.optString("_RejCode");
                                if ("000000".equals(returncode)) {
                                } else {
                                    String ReturnMsg = data.optString("_RejMessage");
                                    if ("TimeOut".equals(returncode)) {
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }

                        } else {
                        }
                    }

                    @Override
                    public void onError(Object arg0) {
                    }
                });
    }

    public String getUrl() {
        return url;
    }

    public String getCifseq() {
        return cifseq;
    }

    public String getMobile() {
        return mobile;
    }
}