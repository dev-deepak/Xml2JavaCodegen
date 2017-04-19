package com.dv.elements;

import javax.xml.bind.annotation.XmlAttribute;

public class Param {
	@XmlAttribute
private String name;
	@XmlAttribute
private String type;
	
	
	
	public String getName() {
		return name;
	}



	public String getType() {
		return type;
	}



	@Override
	public String toString() {
		return "Param [name=" + name + ", type=" + type + "]";
	}
	
}
