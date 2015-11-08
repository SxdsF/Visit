package com.godmaster.visit.impl;

import java.lang.ref.WeakReference;
import java.util.concurrent.Future;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.handler.DefaultResponseHandler;
import com.godmaster.visit.handler.ProcessResponseHandler;
import com.godmaster.visit.process.impl.EntityProcessor;

public class SyncNetworkServiceImpl extends SyncNetworkService {

	@Override
	public Future<Response> syncGet(String uri) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, (RequestParams) null);
	}

	@Override
	public Future<Response> syncGet(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		Future<Response> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.get().build(),
					HttpClientContext.create(), new DefaultResponseHandler());
		}
		return future;
	}

	@Override
	public <T> Future<T> syncGet(String uri, EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, null, processor);
	}

	@Override
	public <T> Future<T> syncGet(String uri, RequestParams params,
			EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.get().build(),
					HttpClientContext.create(), new ProcessResponseHandler<T>(
							processor));
		}
		return future;
	}

	@Override
	public Future<Response> syncPost(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		Future<Response> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.post().build(),
					HttpClientContext.create(), new DefaultResponseHandler());
		}
		return future;
	}

	@Override
	public <T> Future<T> syncPost(String uri, RequestParams params,
			EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.post().build(),
					HttpClientContext.create(), new ProcessResponseHandler<T>(
							processor));
		}
		return future;
	}
}
