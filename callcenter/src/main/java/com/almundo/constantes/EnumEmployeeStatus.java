package com.almundo.constantes;

public enum EnumEmployeeStatus {

	FREE("FREE"), IN_CALL("IN_CALL");
	
	private String property;

	private EnumEmployeeStatus(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
}
