package com.dv.symbols;

public enum ScopeEnum {

	pub("public"),pri("private"), pro("protected"), def("");
	
	private String actualValue;
	
	private ScopeEnum(String actualValue) {
		this.actualValue = actualValue;
	}

	public String getValue(){
		return actualValue;
	}
	public static ScopeEnum fromSymbol(String symbol){
		switch (symbol){
			case "+": return ScopeEnum.pub; 
			case "-": return ScopeEnum.pri;
			case "#": return ScopeEnum.pro;
			default: return ScopeEnum.def;
		}
		
	}
}
