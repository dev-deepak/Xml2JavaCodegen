package com.dv;

import java.io.File;
import java.io.FileInputStream;
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
			context = JAXBContext.newInstance(CodegenClassField.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			String absolutePath = new File("src\\com\\dv\\data.xml").getAbsolutePath();
			CodegenElements o =(CodegenElements) unmarshaller.unmarshal(new FileInputStream(absolutePath));
			
			Class<? extends CodegenElements> classObject = o.getClass();
			StringBuilder classBuilderFromCode = new StringBuilder();
			classBuilderFromCode.append("/**\n* Created by DV\n* Date: April 20, 2016\n* Copyright (c): All rights reserved by the author\n*/");
			Field declaredNameField = classObject.getDeclaredField("name");
			if(declaredNameField == null){
				throw new Exception("No name of class. Specify name");
			}else{
				declaredNameField.setAccessible(true);
				Field declaredClassScope = classObject.getDeclaredField("scope");
				declaredClassScope.setAccessible(true);
				
				classBuilderFromCode.append(ScopeEnum.fromSymbol(declaredClassScope == null ? "" : (String) declaredClassScope.get(o)).getValue()).append(" class ").append(declaredNameField.get(o));
			}
			
			Field declaredInheritType = classObject.getDeclaredField("inherit");
			
			if(declaredInheritType != null){
				Field declaredParentField = classObject.getDeclaredField("parent");
				if(declaredParentField == null){
					throw new Exception("Parent superclass or interface expected");
					
				}else{
					declaredInheritType.setAccessible(true);
					declaredParentField.setAccessible(true);
					classBuilderFromCode.append(space).append(declaredInheritType.get(o)).append(space).append(declaredParentField.get(o));
				}
			}
			classBuilderFromCode.append(space).append("{\n");
			
			Field declaredFieldsInNewClass = classObject.getDeclaredField("field");
			if(declaredFieldsInNewClass != null){
				declaredFieldsInNewClass.setAccessible(true);
				ArrayList<CodegenField> fieldsList = (ArrayList<CodegenField>) declaredFieldsInNewClass.get(o);
				for(CodegenField f : fieldsList){
					classBuilderFromCode.append(ScopeEnum.fromSymbol(f.getScope()).getValue()).append(space).append(f.getType()).append(space).append(f.getName()).append(";\n");
					System.out.println(f.toString());
				}
			}
			
			Field declaredMethodsInNewClass = classObject.getDeclaredField("method");
			if(declaredMethodsInNewClass != null){
				declaredMethodsInNewClass.setAccessible(true);
				ArrayList<CodegenMethod> fieldsList = (ArrayList<CodegenMethod>) declaredMethodsInNewClass.get(o);
				for(CodegenMethod f : fieldsList){
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
					System.out.println(f.toString());
				}
			}
			
			
			System.out.println(classBuilderFromCode.toString());
			System.out.println(o);
		} catch (IOException ioE) {
			ioE.printStackTrace();
		} catch (JAXBException jaxbE) {
			jaxbE.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
