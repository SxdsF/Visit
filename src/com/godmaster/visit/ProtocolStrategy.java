package com.godmaster.visit;

import org.apache.http.client.HttpClient;

public interface ProtocolStrategy {
	public HttpClient getClient();
}
