package com.sxdsf.visit.service;

import org.apache.http.client.HttpClient;

import com.sxdsf.visit.service.impl.SyncNetworkServiceImpl;

public class SyncNetworkServiceBuilder {
	private HttpClient client;

	public static final SyncNetworkServiceBuilder create() {
		return new SyncNetworkServiceBuilder();
	}

	protected SyncNetworkServiceBuilder() {
		super();
	}

	public final SyncNetworkServiceBuilder setHttpClient(HttpClient client) {
		this.client = client;
		return this;
	}

	public final SyncNetworkService build() {
		SyncNetworkService service = null;
		HttpClient client = this.client;
		if (client == null) {
			service = new SyncNetworkServiceImpl();
		} else {
			service = new SyncNetworkServiceImpl(client);
		}
		return service;

	}
}
