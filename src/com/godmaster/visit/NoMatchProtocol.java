package com.godmaster.visit;

import org.apache.http.client.HttpClient;

public class NoMatchProtocol implements Protocol {

	@Override
	public HttpClient getClient() {
		// TODO Auto-generated method stub
		return new NoMatchClient();
	}

}
