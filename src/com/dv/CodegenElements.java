package com.dv;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.dv.elements.CodegenClassField;

@XmlRootElement(name = "xml")
public class CodegenElements {

	@XmlElement(name = "C")
	private ArrayList<CodegenClassField> classes;
	
	@XmlElement(name = "I")
	private ArrayList<CodegenClassField> interfaces;
	
	
}
