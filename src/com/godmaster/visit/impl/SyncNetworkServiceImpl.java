package com.godmaster.visit.impl;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.StrategyGroup;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.process.Processor;

public class SyncNetworkServiceImpl implements SyncNetworkService {

	private static final ThreadLocal<StrategyGroup> threadLocal = new ThreadLocal<>();

	@Override
	public <T> T syncGet(String uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		StrategyGroup sg = threadLocal.get();
		return null;
	}

	@Override
	public <T> T syncGet(String uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncGet(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		T response = null;
		if (uri != null && processor != null) {
			StrategyGroup sg = threadLocal.get();
			if (sg != null) {
				switch (sg.getProtocol()) {
				case HTTP:
					break;
				case HTTPS:
					break;
				default:
					break;
				}
			} else {

			}
		}
		return response;
	}

	@Override
	public <T> T syncGet(URI uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncPost(String uri, RequestParams params,
			Processor processor, long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T syncPost(String uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncPost(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncPost(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T syncPost(URI uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncPost(uri, params, processor, -1, null);
	}

}
