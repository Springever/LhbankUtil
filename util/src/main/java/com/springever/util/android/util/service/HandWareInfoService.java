package com.springever.util.android.util.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.lhbank.client.sdk.core.util.SharedPreUtil;
import com.springever.util.android.util.AppUtils;
import com.springever.util.android.util.DeviceUtils;
import com.springever.util.android.util.LhAppDaqRequestUtils;
import com.springever.util.android.util.LocationUtils;
import com.springever.util.android.util.NetworkUtils;
import com.springever.util.android.util.PhoneUtils;
import com.springever.util.android.util.StringUtils;
import com.springever.util.android.util.Utils;
import com.springever.util.android.util.log.LogCollector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author maowenping 2015-12-12
 * @description 开始自动上传硬件信息
 */
public class HandWareInfoService extends Service {

    private String lonlat = null;//gps的经纬度

    private double lon = 0d;//经度

    private double lat = 0d;//纬度

    private String getClientHandWareInfo = "pmobile-daq.HandwareInfoAcq.do";//采集硬件信息接口

    private Context context;

    private SharedPreUtil sharedPreUtil;

    private String url;

    private String cifseq;

    private String mobile;

    private String paramJson;

    private LhAppDaqRequestUtils lhAppDaqRequestUtils;

    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        this.sharedPreUtil = new SharedPreUtil(context);
        url = sharedPreUtil.getString("LhAppDaqRequestUtils_url");
        cifseq = sharedPreUtil.getString("LhAppDaqRequestUtils_cifseq");
        mobile = sharedPreUtil.getString("LhAppDaqRequestUtils_mobile");
        lhAppDaqRequestUtils = LhAppDaqRequestUtils.getInstance(context, url, cifseq, mobile);
        handler = new MyHandler();
        LogCollector.writeLog("LoginFragment", "", "", "进入HandWareInfoService了");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        JSONObject json = new JSONObject();
        Utils.init(context);
        LogCollector.writeLog("LoginFragment", mobile, cifseq, "进入onStartCommand");
        String model = DeviceUtils.getModel();//获取设备型号；如MI2SC-小米
        String uuid = lhAppDaqRequestUtils.getUUID(context);//获取uuid
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
        paramJson = json.toString();
        LogCollector.writeLog("LoginFragment", mobile, cifseq, url + getClientHandWareInfo);
        LogCollector.writeLog("LoginFragment", mobile, cifseq, paramJson);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("paramJson", paramJson);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            lhAppDaqRequestUtils.requestPmobileServer(context, url + getClientHandWareInfo,
                    message.getData().getString("paramJson"));
        }
    }

}