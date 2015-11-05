package com.godmaster.visit;

public class StrategyGroup {

	private Protocol protocol;
	private ProtocolStrategy strategy;

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public ProtocolStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ProtocolStrategy strategy) {
		this.strategy = strategy;
	}

}
