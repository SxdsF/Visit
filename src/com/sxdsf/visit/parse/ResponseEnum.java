package com.sxdsf.visit.parse;

public enum ResponseEnum {
	INFORMATIONAL(100), SUCCESS(200), REDIRECTION(300), CLIENT_ERROR(400), SERVER_ERROR(
			500), NO_RESPONSE(0);

	private int code;

	private ResponseEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
