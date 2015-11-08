package com.godmaster.visit.impl;

import java.util.List;
import java.util.concurrent.Future;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.process.impl.DataProcessor;

public class SyncNetworkServiceImpl implements SyncNetworkService {

	@Override
	public void cancel(Future<?> future) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel(List<Future<?>> futureList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isclosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Future<Response> syncGet(String uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Response> syncGet(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> syncGet(String uri, DataProcessor<T> processor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> syncGet(String uri, RequestParams params,
			DataProcessor<T> processor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<Response> syncPost(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Future<T> syncPost(String uri, RequestParams params,
			DataProcessor<T> processor) {
		// TODO Auto-generated method stub
		return null;
	}

}
