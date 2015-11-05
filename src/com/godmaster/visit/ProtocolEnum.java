package com.godmaster.visit;

public enum ProtocolEnum {
	HTTP("http"), HTTPS("https"), NO_MATCH("no match");

	private String scheme;

	private ProtocolEnum(String scheme) {
		this.scheme = scheme;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public static final ProtocolEnum getProtocol(String scheme) {
		ProtocolEnum protocol = null;
		if (HTTP.getScheme().equals(scheme)) {
			protocol = HTTP;
		} else if (HTTPS.getScheme().equals(scheme)) {
			protocol = HTTPS;
		} else {
			protocol = NO_MATCH;
		}
		return protocol;
	}
}
