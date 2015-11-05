package com.godmaster.visit.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.godmaster.visit.AsyncHttpClient;

public class NetworkService {
	private static NetworkService instance = null;
	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	private NetworkService() {
	}

	public static NetworkService getInstance() {
		if (instance == null) {
			synchronized (AsyncHttpClient.class) {
				if (instance == null) {
					instance = new NetworkService();
				}
			}
		}
		return instance;
	}
}
