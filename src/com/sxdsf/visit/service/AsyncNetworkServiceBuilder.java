package com.sxdsf.visit.service;

import java.util.concurrent.ExecutorService;
import org.apache.http.client.HttpClient;

import com.sxdsf.visit.service.impl.AsyncNetworkServiceImpl;

public class AsyncNetworkServiceBuilder {
	private HttpClient client;
	private ExecutorService service;

	public static final AsyncNetworkServiceBuilder create() {
		return new AsyncNetworkServiceBuilder();
	}

	protected AsyncNetworkServiceBuilder() {
		super();
	}

	public final AsyncNetworkServiceBuilder setHttpClient(HttpClient client) {
		this.client = client;
		return this;
	}

	public final AsyncNetworkServiceBuilder setExecutorService(
			ExecutorService service) {
		this.service = service;
		return this;
	}

	public final AsyncNetworkService build() {
		AsyncNetworkService asyncService = null;
		HttpClient client = this.client;
		ExecutorService service = this.service;
		if (client != null && service != null) {
			asyncService = new AsyncNetworkServiceImpl(client, service);
		} else if (client != null && service == null) {
			asyncService = new AsyncNetworkServiceImpl(client);
		} else if (client == null && service != null) {
			asyncService = new AsyncNetworkServiceImpl(service);
		} else {
			asyncService = new AsyncNetworkServiceImpl();
		}
		return asyncService;
	}
}
