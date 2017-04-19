package com.dv.elements;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


public class CodegenField {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String scope;
	
	
	
	public String getName() {
		return name;
	}



	public String getType() {
		return type;
	}



	public String getScope() {
		return scope;
	}



	@Override
	public String toString() {
		return "CodegenField [name=" + name + ", type=" + type + ", scope=" + scope + "]";
	}
	
	
}
