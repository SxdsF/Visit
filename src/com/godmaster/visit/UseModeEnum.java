package com.godmaster.visit;

public enum UseModeEnum {

	ASYNC("asynchronous"), SYNC("synchronous");

	private String name;

	private UseModeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
