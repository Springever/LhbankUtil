package com.springever.util.android.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lhbank.client.sdk.core.util.NetWorkUtil;
import com.lhbank.client.sdk.core.util.SharedPreUtil;
import com.springever.util.android.util.log.LogCollector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author maowenping 2018-06-16 客户端数据采集送到后台
 */
public class LhAppDaqRequestUtils {

    private String lonlat = null;//gps的经纬度

    private double lon = 0d;//经度

    private double lat = 0d;//纬度

    private String getClientHandWareInfo = "pmobile-daq.HandwareInfoAcq.do";//采集硬件信息接口

    private static LhAppDaqRequestUtils sInstance;

    private Context activity;

    private SharedPreUtil sharedPreUtil;

    private String url;

    private String cifseq;

    private String mobile;

    private Handler handler;

    private LhAppDaqRequestUtils(Context activity, String url, String cifseq, String mobile) {
        this.activity = activity;
        this.url = url;
        this.cifseq = cifseq;
        this.mobile = mobile;
        this.sharedPreUtil = new SharedPreUtil(activity);
        handler = new MyHandler();
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

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            JSONObject json = new JSONObject();
            Utils.init(activity);
            LogCollector.writeLog("LoginFragment", mobile, cifseq, "进入handleMessage");
            String model = DeviceUtils.getModel();//获取设备型号；如MI2SC-小米
            String uuid = getUUID(activity);//获取uuid
            String sdkDes = DeviceUtils.getSDKDescVersion();//获取系统SDK版本说明，例如4.3
            String sdkInt = DeviceUtils.getSDKVersion() + "";//获取系统SDK版本号
            String sysVer = DeviceUtils.getSystemVersion();//获取系统版本号,如MIUI9.5
            String mac = DeviceUtils.getMacAddress();//获取mac地址
            String vender = DeviceUtils.getManufacturer();//获取设备产商
            String carrie = PhoneUtils.getSimOperatorByMnc();//获取网络运营商名称，如电信
            String ip = NetworkUtils.getIPAddress(true);//ipv4
            //获取gps经纬度
            if (LocationUtils.isGpsEnabled() && LocationUtils.isLocationEnabled()) {//开gps且有定位服务权限
                LocationUtils.register(10000, 10, new LocationUtils.OnLocationChangeListener() {
                    @Override
                    public void getLastKnownLocation(Location location) {
                        if (location != null) {
                            //latitude  纬度,longitude 经度
                            lon = location.getLongitude();
                            lat = location.getLatitude();
                            lonlat = lon + "," + lat;
                        }
                    }

                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }
                });
            }
            String address = LocationUtils.getCountryName(lat, lon) + "," + LocationUtils.getLocality(lat, lon) + "," + LocationUtils.getStreet(lat, lon);//国家+所在地+街道
            String imei = PhoneUtils.getIMEI();//imei
            String imsi = PhoneUtils.getIMSI();//imsi
            String androidId = DeviceUtils.getAndroidID();
            String serialNumber = DeviceUtils.getSerialNumber();
            String appInfo = null;
            StringBuilder sb = new StringBuilder();
            LogCollector.writeLog("LoginFragment", mobile, cifseq, lonlat);
            //获取已安装app信息
            List<AppUtils.AppInfo> appInfoList = AppUtils.getAppsInfo();
            if (appInfoList != null && appInfoList.isEmpty()) {
                for (int i = 0; i < appInfoList.size(); i++) {
                    LogCollector.writeLog("LoginFragment", mobile, cifseq, appInfoList.get(i).toString());
                    if (!StringUtils.isEmpty(appInfoList.get(i).getName())) {
                        sb.append(appInfoList.get(i).getName());
                        sb.append(",");
                    }
                }
                appInfo = sb.substring(0, sb.length() - 1);
            }
            try {
                json.put("uuid", uuid);
                json.put("clientType", "android");//系统型号
                json.put("channel", "pmobile");//渠道
                if (!StringUtils.isEmpty(cifseq)) {
                    json.put("cifseq", cifseq);
                } else {
                    json.put("cifseq", "lhyh999999");
                }
                if (!StringUtils.isEmpty(mobile)) {
                    json.put("mobile", mobile);
                } else {
                    json.put("mobile", "lhyh999999");
                }
                if (!StringUtils.isEmpty(model)) {
                    json.put("model", model);
                }
                if (!StringUtils.isEmpty(sdkDes)) {
                    json.put("sdkDes", sdkDes);
                }
                if (!StringUtils.isEmpty(sdkInt)) {
                    json.put("sdkInt", sdkInt);
                }
                if (!StringUtils.isEmpty(sysVer)) {
                    json.put("sysVer", sysVer);
                }
                if (!StringUtils.isEmpty(mac)) {
                    json.put("mac", mac);
                }
                if (!StringUtils.isEmpty(vender)) {
                    json.put("vender", vender);
                }
                if (!StringUtils.isEmpty(carrie)) {
                    json.put("carrie", carrie);
                }
                if (!StringUtils.isEmpty(ip)) {
                    json.put("ip", ip);
                }
                if (!StringUtils.isEmpty(lonlat)) {
                    json.put("lonlat", lonlat);
                }
                if (!StringUtils.isEmpty(address)) {
                    json.put("address", address);
                }
                if (!StringUtils.isEmpty(imei)) {
                    json.put("imei", imei);
                }
                if (!StringUtils.isEmpty(imsi)) {
                    json.put("imsi", imsi);
                }
                if (!StringUtils.isEmpty(androidId)) {
                    json.put("androidId", androidId);
                }
                if (!StringUtils.isEmpty(serialNumber)) {
                    json.put("serialNumber", serialNumber);
                }
                if (!StringUtils.isEmpty(appInfo)) {
                    json.put("appInfo", appInfo);
                }
            } catch (JSONException e) {

            }
            String paramJson = json.toString();
            LogCollector.writeLog("LoginFragment", mobile, cifseq, url + getClientHandWareInfo);
            LogCollector.writeLog("LoginFragment", mobile, cifseq, paramJson);
            requestPmobileServer(activity, url + getClientHandWareInfo,
                    message.getData().getString("paramJson"));
        }
    }

    /**
     * 采集硬件信息
     */
    public void requestClientHandWareInfo() {
        LogCollector.writeLog("LoginFragment", mobile, cifseq, "进入requestClientHandWareInfo");
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }).start();
        /**发广播
        sharedPreUtil.setString("LhAppDaqRequestUtils_cifseq",url);
        sharedPreUtil.setString("LhAppDaqRequestUtils_cifseq",cifseq);
        sharedPreUtil.setString("LhAppDaqRequestUtils_mobile",mobile);
        activity.sendBroadcast(new Intent("android.intent.action.REQUEST_HANDWARE_INFO_BROADCAST"));
        */
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