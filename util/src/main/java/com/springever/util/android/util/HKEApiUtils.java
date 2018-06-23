package com.springever.util.android.util;

import android.content.Context;

import com.lhbank.cfca.sdk.hke.Callback;
import com.lhbank.cfca.sdk.hke.HKEApi;
import com.lhbank.cfca.sdk.hke.data.AuthenticateInfo;
import com.lhbank.cfca.sdk.hke.data.CFCACertificate;

/**
 * Created by troy on 2018/3/6.
 */

public class HKEApiUtils {

    public HKEApiUtils() {
        throw new UnsupportedOperationException("i'm so handsome...");
    }

    /**
     * @param context
     * @param orgID   机构ID，测试机构为HKE_TEST_ORG
     * @param appID   AppID，测试APP为HKE_TEST_APP
     */
    public static void initialize(Context context, String orgID, String appID) {
        HKEApi.initialize(context, orgID, appID);
    }

    /**
     * @param name             用户姓名（用于绑定用户信息，后台会做身份交易，务必真实有效）
     * @param identityTypeCode 用户证件类型编码（具体请参见附录证件编码，传证件类型编码
     *                         如“居民身份证”请传”0”，传null默认为居民身份证）
     * @param identityNumber   用户身份证号码（用于绑定用户信息，后台会做身份交易，务必真实有效）
     * @param phoneNumber      用户银行绑定手机号(用以保存用户信息，不做校验但必须传入)
     * @param deviceID         用户设备标识(用以唯一区分用户设备，当支持多设备可以用该参数来区分用户设备进行设备吊销操作等)
     * @param callback         异步回调通知,响应的结果为String，包含获取随机数。
     */
    public static void requestHKEServerRandom(final String name,
                                              final String identityTypeCode,
                                              final String identityNumber, final String phoneNumber,
                                              final String deviceID,
                                              final Callback<String> callback) {
        HKEApi.getInstance().requestHKEServerRandom(name, identityTypeCode, identityNumber, phoneNumber, deviceID, callback);
    }


    public static void cancelRequestHKEServerRandom() {
        HKEApi.getInstance().cancelRequestHKEServerRandom();
    }

    /**
     * @param callback 异步回调通知,响应的结果为DeviceAuthenticateInfo，包含当前证书列表接口、密码服务端随机数、密码状态；当前证书列表可用于判断是否需要下证书，如果当前不存在证书则说BANKOFLANHAI_SCCBA
     *                 HKEApi.getInstance().authenticateWithServerSignature(serverRandomSignature, callback);
     *                 }
     *                 <p>
     *                 public static void cancelAuthenticate() {
     *                 HKEApi.getInstance().cancelAuthenticate();
     *                 }
     *                 <p>
     *                 /**
     * @param callback 证书下载的通知回调
     */
    public static void downloadCertificate(final Callback<CFCACertificate> callback) {
        HKEApi.getInstance().downloadCertificate(callback);
    }

    public static void cancelDownloadCertificate() {
        HKEApi.getInstance().cancelDownloadCertificate();
    }


    /**
     * @param businessText          待签名业务报文
     * @param businessTextSignature 使用机构证书对业务报文的签名
     * @param callback              签名的通知回调，调用成功时回调参数为签名结果
     */
    public static void signMessageWithBusinessMessage(String businessText,
                                                      String businessTextSignature,
                                                      final Callback<String> callback) {
        HKEApi.getInstance().signMessageWithBusinessMessage(businessText, businessTextSignature, callback);
    }

    public static void cancelSign() {
        HKEApi.getInstance().cancelSign();
    }


    /**
     * @return 当前版本号
     */
    public static String getVersion() {
        return HKEApi.getVersion();
    }

}
