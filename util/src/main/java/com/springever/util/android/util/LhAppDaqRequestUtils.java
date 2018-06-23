package com.springever.util.android.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

    private String getClientHandWareInfo = "pmobile-query.HandwareInfoAcq.do";//采集硬件信息接口

    private static LhAppDaqRequestUtils sInstance;

    private Context activity;

    private SharedPreUtil sharedPreUtil;

    private Handler handler;

    private LhAppDaqRequestUtils() {
    }

    public void init(Context activity) {
        //HKEApiUtils.initialize(activity, "BANKOFLANHAI_SCCBA", "APPOFLANHAI_SCCBA");
        this.activity = activity;
        this.sharedPreUtil = new SharedPreUtil(activity);
        handler = new MyHandler();
    }

    public static LhAppDaqRequestUtils getInstance() {
        if (sInstance == null) {
            sInstance = new LhAppDaqRequestUtils();
        }
        return sInstance;
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            Bundle bundle = message.getData();
            requestClientHandWareInfoNotAsync(bundle.getString("url"), bundle.getString("cifseq"), bundle.getString("mobile"));
        }
    }

    /**
     * 采集硬件信息
     */
    public void requestClientHandWareInfo(final String url, final String cifseq, final String mobile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                bundle.putString("cifseq", cifseq);
                bundle.putString("mobile", mobile);
                message.setData(bundle);
                handler.sendMessage(message);
                Looper.loop();
            }
        }).start();
    }

    /**
     * @param url
     * @param cifseq
     * @param mobile
     */
    public void requestClientHandWareInfoNotAsync(String url, String cifseq, String mobile) {
        String paramJson = getHandwareInfo(cifseq, mobile);
        requestPmobileServer(activity, url + getClientHandWareInfo, paramJson);
    }

    /**
     * 采集硬件信息
     */
    public String getHandwareInfo(String cifseq, String mobile) {
        JSONObject json = new JSONObject();
        Utils.init(activity);
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
        //String address = LocationUtils.getCountryName(lat, lon) + "," + LocationUtils.getLocality(lat, lon) + "," + LocationUtils.getStreet(lat, lon);//国家+所在地+街道
        String address = AddressUtils.getAddresses(ip, "utf-8");
        if (!StringUtils.isEmpty(address)) {
            try {
                JSONObject jsonObject = new JSONObject(address);
                String code = jsonObject.optString("code", "1");
                if ("0".equals(code)) {
                    JSONObject data = jsonObject.optJSONObject("data");
                    if (data != null) {
                        String country = jsonObject.optString("country", "").toString();//国家
                        String region = jsonObject.optString("region", "").toString();//省份
                        String city = jsonObject.optString("city", "").toString();//城市
                        String county = jsonObject.optString("county", "").toString();//区/县
                        String area = jsonObject.optString("area", "").toString();//地区
                        String country_id = jsonObject.optString("country_id", "").toString();//国家id
                        String region_id = jsonObject.optString("region_id", "").toString();//省份id
                        String city_id = jsonObject.optString("city_id", "").toString();//城市id
                        String county_id = jsonObject.optString("county_id", "").toString();//区/县id
                        String area_id = jsonObject.optString("area_id", "").toString();//地区id
                        StringBuilder sb = new StringBuilder();
                        if (!StringUtils.isEmpty(country) && !"XX".equals(country)
                                && !"内网IP".equals(country) && !"local".equals(country)) {
                            sb.append(country);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(region) && !"XX".equals(region)
                                && !"内网IP".equals(region) && !"local".equals(region)) {
                            sb.append(region);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(city) && !"XX".equals(city)
                                && !"内网IP".equals(city) && !"local".equals(city)) {
                            sb.append(city);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(county) && !"XX".equals(county)
                                && !"内网IP".equals(county) && !"local".equals(county)) {
                            sb.append(county);
                            sb.append(",");
                        }
                        sb.append(";");
                        if (!StringUtils.isEmpty(country_id) && !"XX".equals(country_id)
                                && !"内网IP".equals(country_id) && !"local".equals(country_id)) {
                            sb.append(country_id);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(region_id) && !"XX".equals(region_id)
                                && !"内网IP".equals(region_id) && !"local".equals(region_id)) {
                            sb.append(region_id);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(city_id) && !"XX".equals(city_id)
                                && !"内网IP".equals(city_id) && !"local".equals(city_id)) {
                            sb.append(city_id);
                            sb.append(",");
                        }
                        if (!StringUtils.isEmpty(county_id) && !"XX".equals(county_id)
                                && !"内网IP".equals(county_id) && !"local".equals(county_id)) {
                            sb.append(county_id);
                            sb.append(",");
                        }
                        address = sb.toString();
                    } else {
                        address = null;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String imei = PhoneUtils.getIMEI();//imei
        String imsi = PhoneUtils.getIMSI();//imsi
        String androidId = DeviceUtils.getAndroidID();
        String serialNumber = DeviceUtils.getSerialNumber();
        String appInfo = null;
        StringBuilder sb = new StringBuilder();
        //获取已安装app信息(非系统自带）
        List<AppUtils.AppInfo> appInfoList = AppUtils.getAppsInfo();
        if (appInfoList != null && !appInfoList.isEmpty()) {
            for (int i = 0; i < appInfoList.size(); i++) {
                if (!StringUtils.isEmpty(appInfoList.get(i).getName()) && !appInfoList.get(i).isSystem()) {
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
        return paramJson;
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
}