package com.godmaster.visit;

public class ProcessChain<T, V> {
	private T responseHandler;
	private V parser;

	public T getResponseHandler() {
		return responseHandler;
	}

	public void setResponseHandler(T responseHandler) {
		this.responseHandler = responseHandler;
	}

	public V getParser() {
		return parser;
	}

	public void setParser(V parser) {
		this.parser = parser;
	}
}
