package com.godmaster.visit.impl;

import java.util.concurrent.Future;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;

import com.godmaster.visit.AsyncNetworkService;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.callback.AbstractCallback;
import com.godmaster.visit.handler.DefaultResponseHandler;
import com.godmaster.visit.handler.ProcessResponseHandler;
import com.godmaster.visit.process.impl.EntityProcessor;

public class AsyncNetworkServiceImpl extends AsyncNetworkService {

	@Override
	public Future<Response> asyncGet(String uri,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, null, callback);
	}

	@Override
	public Future<Response> asyncGet(String uri, RequestParams params,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		Future<Response> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.get().build(),
					HttpClientContext.create(), new DefaultResponseHandler(),
					callback);
		}
		return future;
	}

	@Override
	public <T> Future<T> asyncGet(String uri, AbstractCallback<T> callback,
			EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, null, callback, processor);
	}

	@Override
	public <T> Future<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.get().build(),
					HttpClientContext.create(), new ProcessResponseHandler<T>(
							processor), callback);
		}
		return future;
	}

	@Override
	public Future<Response> asyncPost(String uri, RequestParams params,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		return this.asyncPost(uri, params, callback, null);
	}

	@Override
	public <T> Future<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isclosed()) {
			future = this.freService.execute(RequestBuilder.post().build(),
					HttpClientContext.create(), new ProcessResponseHandler<T>(
							processor), callback);
		}
		return future;
	}
}
