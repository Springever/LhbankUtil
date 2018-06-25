package com.springever.util.java;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 根据IP地址获取详细的地域信息
 */
public class AddressUtils {
    /**
     * @param ip             ip地址 格式为：192.168.1.1
     * @param encodingString 服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getAddresses(String ip, String encodingString) {
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        String content = "ip=" + ip;
        String returnStr = getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            returnStr = decodeUnicode(returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return null;//无效IP，局域网测试
            }

            return returnStr;
        }
        return null;
    }

    /**
     *
     * @param ip             ip地址 格式为：192.168.1.1
     * @param encodingString 服务器端请求编码。如GBK,UTF-8等
     * @return 实际生活中的地址
     */
    public static String getRealAddress(String ip, String encodingString){
        String encodeString = getAddresses(ip, encodingString);
        String address = "";
        if (!StringUtils.isEmpty(encodeString)) {
            try {
                JSONObject jsonObject = new JSONObject(encodeString);
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
        return address;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();
            out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
}