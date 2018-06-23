package com.springever.util.android.util;

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

    /*
    public JSONObject getAddressByIp(String ip) throws Exception {
        String json_result = null;
        try {
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JSONObject json = new JSONObject(json_result);
        System.out.println("json数据： " + json);
        String code=json.optString("code","1");
        if("0".equals(code)){//正确返回地址

        }else{

        }
        String country = JSONObject.fromObject(json.get("data")).get("country").toString();
        String region = JSONObject.fromObject(json.get("data")).get("region").toString();
        String city = JSONObject.fromObject(json.get("data")).get("city").toString();
        String county = JSONObject.fromObject(json.get("data")).get("county").toString();
        String isp = JSONObject.fromObject(json.get("data")).get("isp").toString();
        String area = JSONObject.fromObject(json.get("data")).get("area").toString();
        System.out.println("国家： " + country);
        System.out.println("地区： " + area);
        System.out.println("省份: " + region);
        System.out.println("城市： " + city);
        System.out.println("区/县： " + county);
        System.out.println("互联网服务提供商： " + isp);

        String address = country + "/";
        address += region + "/";
        address += city + "/";
        address += county;
        System.out.println(address);
    }
    */
}