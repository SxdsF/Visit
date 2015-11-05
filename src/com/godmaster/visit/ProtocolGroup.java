package com.godmaster.visit;

public class ProtocolGroup {

	private ProtocolEnum protocolEnum;
	private Protocol protocol;

	private ProtocolGroup(ProtocolEnum protocolEnum, Protocol protocol) {
		this.protocolEnum = protocolEnum;
		this.protocol = protocol;
	}

	public ProtocolEnum getProtocolEnum() {
		return protocolEnum;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public static ProtocolGroup create(String scheme) {
		return new ProtocolGroup(ProtocolEnum.getProtocol(scheme),
				ProtocolFactory.getInstance(scheme));
	}
}
