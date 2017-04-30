package com.dv;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.FieldPosition;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.dv.elements.CodegenClassField;
import com.dv.elements.CodegenField;
import com.dv.elements.CodegenMethod;
import com.dv.elements.Param;
import com.dv.symbols.ScopeEnum;

public class ReadXml {
	static final String space = " ";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JAXBContext context = null;
		try{
			context = JAXBContext.newInstance(CodegenElements.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			String absolutePath = new File("src\\com\\dv\\data.xml").getAbsolutePath();
			CodegenElements o =(CodegenElements) unmarshaller.unmarshal(new FileInputStream(absolutePath));
		    ArrayList<CodegenClassField> classesToCreate = o.getClasses();
		    if(classesToCreate != null && !classesToCreate.isEmpty()){
		    	for(CodegenClassField classObjects: classesToCreate){
		    		StringBuilder classBuilderFromCode = new StringBuilder();
					classBuilderFromCode.append("/**\n* Created by DV\n* Date: April 20, 2016\n* Copyright (c): All rights reserved by the author\n*/");
					String declaredClassNameField = classObjects.getName();
					if(declaredClassNameField == null || declaredClassNameField.isEmpty()){
						throw new Exception("No name of class. Specify name");
					}else{
						classBuilderFromCode.append(ScopeEnum.fromSymbol(classObjects.getScope()).getValue()).append(" class ").append(classObjects.getName());
					}
					
					String declaredInheritType  = classObjects.getInherit();
					if(declaredInheritType != null){
						String declaredParentField = classObjects.getParent();
						if(declaredParentField == null || declaredParentField.isEmpty()){
							throw new Exception("Parent superclass or interface expected");
							
						}else{
							classBuilderFromCode.append(space).
							append(declaredInheritType).append(space).append(classObjects.getParent());
						}
					}
					classBuilderFromCode.append(space).append("{\n");
					StringBuilder sb = new StringBuilder();
					ArrayList<CodegenField> declaredFieldsInNewClass = classObjects.getField();
					if(declaredFieldsInNewClass != null){
						for(CodegenField f : declaredFieldsInNewClass){
							classBuilderFromCode.append(ScopeEnum.fromSymbol(f.getScope()).getValue()).
							append(space).append(f.getType()).append(space).append(f.getName()).append(";\n");
							System.out.println(f.toString());
						}
						if(true){
							sb.append("\n@Override\npublic String toString(){\n");
							sb.append("return \""+classObjects.getName()+" [");
							int x = 0;
							for(CodegenField f : declaredFieldsInNewClass){
								if(x++ == 0)
									sb.append(""+f.getName()+"=\"+ ").append(f.getName()).append("+");
								else
									sb.append("\", "+f.getName()+"= + \"").append(f.getName()).append("+");
							}
							sb.append("\"]\";\n}");
						}
						
						if(true){
							StringBuilder jsonSB= new StringBuilder();
							jsonSB.append("\npublic Json toJSon(){\n");
							jsonSB.append("return {").append("\"").append(classObjects.getName()).append("\": {").append("\n");
							for(CodegenField f : declaredFieldsInNewClass){
								
								jsonSB.append(" \""+f.getName()+"\": ").append("\""+f.getName()).append("\"").append("\n");
							}
							jsonSB.append("}};\n}");
							System.out.println(jsonSB.toString());
						}
						
					}
					
					ArrayList<CodegenMethod> declaredMethodsInNewClass = classObjects.getMethod();
					
					if(declaredMethodsInNewClass != null){
						for(CodegenMethod f : declaredMethodsInNewClass){
							classBuilderFromCode.append(ScopeEnum.fromSymbol(f.getScope()).getValue()).append(space).append(f.getType()).append(space).append(f.getName());
							if(f.getParam() != null && !f.getParam().isEmpty()){
								classBuilderFromCode.append("(");
								for(int i = 0; i < f.getParam().size(); i++){
									Param p = f.getParam().get(i);
									classBuilderFromCode.append(p.getType()).append(space).append(p.getName());
									if(i != f.getParam().size() -1)classBuilderFromCode.append(", ");
								}
								classBuilderFromCode.append("){\n\n}");
							}else classBuilderFromCode.append("(){\n\n}");
						}
					}
					
					classBuilderFromCode.append(sb.toString());
					File outputFile = new File("src\\com\\dv\\output.java");
					outputFile.createNewFile();
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
					bufferedOutputStream.write(classBuilderFromCode.toString().getBytes());
					bufferedOutputStream.close();
					 

		    	}
		    }
					} catch (IOException ioE) {
			ioE.printStackTrace();
		} catch (JAXBException jaxbE) {
			jaxbE.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
