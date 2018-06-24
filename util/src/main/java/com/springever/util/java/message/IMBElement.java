package com.springever.util.java.message;

import org.dom4j.Element;

import java.util.List;

public interface IMBElement {

	public abstract Element generyElement(Element parent);

	public abstract Object generyObject();

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getText();

	public abstract void setText(String text);

	public abstract String getType();

	public abstract void setType(String type);

	public abstract List<IMBElement> getChildren();

	public abstract void setChildren(List<IMBElement> children);

}