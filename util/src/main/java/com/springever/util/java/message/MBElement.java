package com.springever.util.java.message;

import com.lhbank.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MBElement implements IMBElement {
	public static final String LIST = "list";
	public static final String MAP = "map";
	public static final String TYPE = "type";

	private String name;
	private String text;
	private String type;
	private List<IMBElement> children;

	public MBElement() {
	}

	@SuppressWarnings("unchecked")
	public MBElement(Element node) {
		this.name = node.getName();
		this.type = node.attributeValue("type");
		List<Element> elems = node.elements();
		if (elems != null && !elems.isEmpty()) {
			children = new ArrayList<IMBElement>();
			for (Element child : elems) {
				children.add(new MBElement(child));
			}
			if (this.type == null) {
				this.type = MAP;
			}
		} else {
			this.text = node.getText();
		}
	}

	public MBElement(String name, List<Element> elems) {
		this.name = name;
		this.type = LIST;
		if (elems != null && !elems.isEmpty()) {
			children = new ArrayList<IMBElement>();
			for (Element child : elems) {
				children.add(new MBElement(child));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#generyElement(org.dom4j.Element)
	 */
	
	public Element generyElement(Element parent) {
		Element elem = parent.addElement(this.name);
		if (LIST.equals(this.type) || MAP.equals(this.type)) {
			elem.addAttribute("type", this.type);
			if (children != null) {
				for (IMBElement child : children) {
					child.generyElement(elem);
				}
			}
		} else {
			elem.setText(this.text);
		}
		return elem;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object generyObject() {
		
		if (LIST.equals(this.type)) {
			List datas = new ArrayList();
			for (IMBElement elem : children) {
				datas.add(elem.generyObject());
			}
			return datas;
		} else if (MAP.equals(this.type) || (children != null)) {
			Map map = new HashMap();
			for (IMBElement elem : children) {
				map.put(elem.getName(), elem.generyObject());
			}
			return map;
		}
		return this.text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#getName()
	 */
	
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#setName(java.lang.String)
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#getText()
	 */
	
	public String getText() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#setText(java.lang.String)
	 */
	
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#getType()
	 */
	
	public String getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#setType(java.lang.String)
	 */
	
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#getChildren()
	 */
	
	public List<IMBElement> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.bean.IMBElement#setChildren(java.util.List)
	 */
	
	public void setChildren(List<IMBElement> children) {
		this.children = children;
	}
}
