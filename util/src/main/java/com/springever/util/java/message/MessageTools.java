package com.springever.util.java.message;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.lhbank.json.JSONArray;
import com.lhbank.json.JSONException;
import com.lhbank.json.JSONObject;
import com.springever.util.java.XmlUtils;

/**
 * xml报文解析
 */
public class MessageTools {

	/**
	 * 删除元素集
	 * 
	 * @param xmlStr
	 * @param eleTag
	 * @return
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	public static String removeElements(String xmlStr, String eleTag)
			throws DocumentException, MalformedURLException {
		Document doc = XmlUtils.readText(xmlStr);
		List eleList = doc.selectNodes(eleTag);
		Element temp;
		for (int i = 0; i < eleList.size(); i++) {
			temp = (Element) eleList.get(i);
			doc.remove(temp);
		}
		return doc.asXML();
	}

	/**
	 * XML to MAP
	 * 
	 * @param doc
	 * @return
	 */
	public static Map getElements(Document doc) {
		Map md = new HashMap();
		Element e = doc.getRootElement();
		List content = e.elements();
		Map result = new HashMap();
		Element element;
		for (int i = 0; i < content.size(); i++) {
			element = (Element) content.get(i);
			if (element.elements().size() > 0) {
				// 有子节点
				List child = element.elements();

				List values = new ArrayList();
				Map singleMap = new HashMap();

				Element node;
				for (int j = 0; j < child.size(); j++) {
					node = (Element) child.get(j);
					if (node.elements().size() > 0) {
						// List<Map>
						List childNodes = node.elements();
						Map nodeValue = new HashMap();
						Element elementValue;
						for (int k = 0; k < childNodes.size(); k++) {
							elementValue = (Element) childNodes.get(k);
							nodeValue.put(elementValue.getName(),
									elementValue.getText());
						}
						values.add(nodeValue);

					} else {
						// 单值列表 List
						Element child0 = (Element) child.get(0);
						Element child1 = (Element) child.get(1);
						if (child.size() > 1) {
							if (!child0.getName().equals(child1.getName())) {
								singleMap.put(node.getName(), node.getText());
							} else {
								values.add(node.getText());
							}
						}

					}

					// if (values.size() > 0)
					result.put(element.getName(), values);
				}
				if (singleMap.size() > 0)
					result.put(element.getName(), singleMap);

			} else {
				// 是单值 直接放入Map
				result.put(element.getName(), element.getText());
			}
		}

		return result;
	}

	public static Map jsonToMap(String jsonStr) throws Exception {
		return JSONObject.fromObject(jsonStr);
	}

	/**
	 * 将JSON转成Element 如{userId:'10012',datas:["111","222"]}
	 * 如{userId:'10012',datas:[{AcctNo:"111"},{AcctNo:"222"}]} 单层 结构userId
	 * 多层结构datas/AcctNo
	 * 
	 * @param parent
	 * @param jsonObj
	 */
	public static void jsonToElement(Element parent, JSONObject jsonObj) {
		Element elem;
		try {
			Iterator<String> nameItr = jsonObj.keys();
			String name;
			while (nameItr.hasNext()) {
				name = nameItr.next();
				Object obj = jsonObj.get(name);
				if (obj instanceof JSONArray) {
					// outMap.put(name, obj);
					elem = parent.addElement(name);
					elem.addAttribute(MBElement.TYPE, MBElement.LIST);
					JSONArray jsonArry = (JSONArray) obj;
					for (int i = 0; i < jsonArry.size(); i++) {
						Object childObj = jsonArry.get(i);
						if (childObj instanceof JSONObject) {
							Element map = elem.addElement("MAP");
							map.addAttribute(MBElement.TYPE, MBElement.MAP);
							jsonToElement(map, (JSONObject) childObj);
						} else {
							elem.setText((String) childObj);
						}
					}
				} else if (obj instanceof JSONObject) {
					elem = parent.addElement(name);
					jsonToElement(elem, (JSONObject) obj);
				} else {
					elem = parent.addElement(name);
					elem.setText(obj.toString());
				}
			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public static String jsonToString(JSONObject json) {
		StringBuffer bf = new StringBuffer();
		Iterator<String> iter = json.keys();
		bf.append("{");
		for (int i = 0; iter.hasNext(); i++) {
			String key = iter.next();
			Object obj = json.opt(key);
			if (i > 0) {
				bf.append(",");
			}
			bf.append("\"").append(key).append("\":");
			if (obj instanceof String) {
				bf.append("\"").append(obj).append("\"");
			} else if (obj instanceof JSONArray) {
				bf.append(((JSONArray) obj).toString());
			} else if (obj instanceof JSONObject) {
				bf.append(jsonToString((JSONObject) obj));
			} else {
				bf.append(obj);
			}
		}
		bf.append("}");
		return bf.toString();
	}

	/**
	 * 简化的ELEMENT
	 * 
	 * @param parent
	 * @param map
	 * @return
	 */
	public static boolean elementToMap3(Element parent, Map<String, Object> map) {
		List<Element> elems = parent.elements();
		// 加载当前节点
		// 判断当前节点的子节点是否为Map数组
		for (Element elem : elems) {
			String name = elem.getName();
			List<Element> sonElems = elem.elements();
			if (null != sonElems && !sonElems.isEmpty()) {
				// 包含子节点
				List array = new ArrayList();
				List<Element> childElems = elem.elements("Map");
				for (Element childMap : childElems) {
					// List/Map
					Map child = new HashMap();
					List<Element> subElems = childMap.elements();
					for (Element son : subElems) {
						elementToMap3(son, child);
					}
					array.add(child);
				}
				if (!array.isEmpty()) {
					map.put(name, array);
				} else {
					// Map
					Map child = new HashMap();
					for (Element son : sonElems) {
						elementToMap3(son, child);
					}
					map.put(name, child);
				}
			} else {
				System.out.println("name:---" + name + "\t text:---"
						+ elem.getText());
				map.put(name, elem.getText());
			}
		}
		return true;
	}

	/**
	 * xml
	 * 
	 * @param element
	 * @param map
	 * @return
	 */
	public static boolean elementToMap(Element element, Map<String, Object> map) {
		String type = element.attributeValue(MBElement.TYPE);
		List<Element> elems = element.elements();
		if (elems == null || elems.isEmpty()) {
			map.put(element.getName(), element.getText());
			return true;
		}
		if (MBElement.LIST.equalsIgnoreCase(type)) {
			List<Map> array = new ArrayList<Map>();
			for (Element child : elems) {
				Map childMap = new HashMap();
				elementToMap(child, childMap);
				array.add(childMap);
			}
			map.put(element.getName(), array);
		} else {
			// Map
			for (Element child : elems) {
				elementToMap(child, map);
			}
		}
		return true;
	}

	/**
	 * 将请求映射转成XML
	 * 
	 * @param params
	 * @param parent
	 * @return
	 */
	public static boolean mapToElement(Map<String, Object> params,
			Element parent) {
		if (null == params || params.isEmpty()) {
			return true;
		}
		for (String key : params.keySet()) {
			Object value = params.get(key);
			if (value instanceof String) {
				parent.addElement(key).setText((String) value);
			} else if (value instanceof String[]) {
				String[] values = (String[]) value;
				for (String text : values) {
					parent.addElement(key).addText(null == text ? "" : text);
				}
			}
		}
		return true;
	}

	public static Document loadXmlByFile(File xmlfile) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = null;
		document = reader.read(xmlfile);
		return document;
	}
}