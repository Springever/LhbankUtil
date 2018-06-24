package com.springever.util.java;

import com.lhbank.json.JSON;
import com.lhbank.json.JSONObject;
import com.lhbank.json.xml.XMLSerializer;

import java.util.Map;

public class JsonUtils {
    /**
     * XML 转成 JSON
     *
     * @param xmlStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String xmlStrToJsonStr(String xmlStr) {
        XMLSerializer xml = new XMLSerializer();
        JSONObject json = (JSONObject) xml.read(xmlStr);
        return json.toString();
    }

    public static String mapToJsonStr(Map map) {
        JSON json = JSONObject.fromObject(map);
        return json.toString();
    }

    /**
     * JSON转成XML
     *
     * @param jsonStr
     * @return
     */
    public static String jsonStrToXmlStr(String jsonStr) {
        XMLSerializer xml = new XMLSerializer();
        JSONObject json = JSONObject.fromObject(jsonStr);
        return xml.write(json);
    }
}
