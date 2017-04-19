package com.dv.elements;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class CodegenMethod {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String scope;
	@XmlElement
	private ArrayList<Param> param;
	
	
	
	public String getName() {
		return name;
	}



	public String getType() {
		return type;
	}



	public String getScope() {
		return scope;
	}



	public ArrayList<Param> getParam() {
		return param;
	}



	@Override
	public String toString() {
		return "CodegenMethod [name=" + name + ", type=" + type + ", scope=" + scope + ", param=" + param + "]";
	}
	
}
