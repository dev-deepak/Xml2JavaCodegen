package com.dv.elements;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class CodegenClassField {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String inherit;
	@XmlAttribute
	private String parent;
	@XmlAttribute
	private String scope;
	
	@XmlElement
	ArrayList<CodegenField> field;
	@XmlElement
	ArrayList<CodegenMethod> method;
	
	@Override
	public String toString(){
		StringBuilder append = new StringBuilder().append("name = " + name).append(" inherit ="+ inherit).append(" parent = "+ parent);
		if(field != null &&  !field.isEmpty()){
			append.append("\nfields: \n\t");
			for(CodegenField eachField: this.field){
				append.append(eachField.toString()).append("\n\t");
			}
		}
		if(method != null &&  !method.isEmpty()){
			append.append("\nmethod: \n\t");
			for(CodegenMethod eachMethod: this.method){
				append.append(eachMethod.toString()).append("\n\t");
			}
		}
		return append.toString();
	}

	public String getName() {
		return name;
	}

	public String getInherit() {
		return inherit;
	}

	public String getParent() {
		return parent;
	}

	public String getScope() {
		return scope;
	}

	public ArrayList<CodegenField> getField() {
		return field;
	}

	public ArrayList<CodegenMethod> getMethod() {
		return method;
	}

	
}
