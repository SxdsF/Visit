package com.sxdsf.visit.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;

import com.sxdsf.visit.AsyncNetworkService;
import com.sxdsf.visit.RequestParams;
import com.sxdsf.visit.Response;
import com.sxdsf.visit.callback.AbstractCallback;
import com.sxdsf.visit.handler.CommonProcessResponseHandler;
import com.sxdsf.visit.handler.DefaultResponseHandler;
import com.sxdsf.visit.handler.HttpEntityProcessResponseHandler;
import com.sxdsf.visit.parse.impl.HttpResponseParser;
import com.sxdsf.visit.process.Processor;
import com.sxdsf.visit.process.impl.HttpEntityProcessor;

public class AsyncNetworkServiceImpl implements AsyncNetworkService {

	private final FutureRequestExecutionService freService;
	private final AtomicBoolean closed = new AtomicBoolean(false);

	private static final String TAG = "AsyncNetworkService";

	public AsyncNetworkServiceImpl() {
		this(HttpClients.createSystem(), Executors.newFixedThreadPool(3));
	}

	public AsyncNetworkServiceImpl(HttpClient client) {
		this(client, Executors.newFixedThreadPool(3));
	}

	public AsyncNetworkServiceImpl(ExecutorService service) {
		this(HttpClients.createSystem(), service);
	}

	public AsyncNetworkServiceImpl(HttpClient client, ExecutorService service) {
		this.freService = new FutureRequestExecutionService(client, service);
	}

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
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = this.freService.execute(get.build(),
					HttpClientContext.create(), new DefaultResponseHandler(),
					callback);
		}
		return future;
	}

	@Override
	public <T> Future<T> asyncGet(String uri, AbstractCallback<T> callback,
			HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, null, callback, processor);
	}

	@Override
	public <T, V> Future<T> asyncGet(String uri, AbstractCallback<T> callback,
			HttpResponseParser<V> parser, Processor<T, V> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			future = this.freService.execute(get.build(),
					HttpClientContext.create(),
					new CommonProcessResponseHandler<T, V>(parser, processor),
					callback);
		}
		return future;
	}

	@Override
	public <T> Future<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = this.freService.execute(get.build(),
					HttpClientContext.create(),
					new HttpEntityProcessResponseHandler<T>(processor),
					callback);
		}
		return future;
	}

	@Override
	public <T, V> Future<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = this.freService.execute(get.build(),
					HttpClientContext.create(),
					new CommonProcessResponseHandler<T, V>(parser, processor),
					callback);
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
			AbstractCallback<T> callback, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder post = RequestBuilder.post().setUri(uri);
			if (params != null) {
				post.setEntity(params.createFormEntity());
			}
			future = this.freService.execute(post.build(),
					HttpClientContext.create(),
					new HttpEntityProcessResponseHandler<T>(processor),
					callback);
		}
		return future;
	}

	@Override
	public <T, V> Future<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		Future<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder post = RequestBuilder.post().setUri(uri);
			if (params != null) {
				post.setEntity(params.createFormEntity());
			}
			future = this.freService.execute(post.build(),
					HttpClientContext.create(),
					new CommonProcessResponseHandler<T, V>(parser, processor),
					callback);
		}
		return future;
	}

	@Override
	public void cancel(Future<?> future) {
		// TODO Auto-generated method stub
		if (future != null && !future.isCancelled() && !future.isDone()) {
			future.cancel(true);
		}
	}

	@Override
	public void cancel(List<Future<?>> futureList) {
		// TODO Auto-generated method stub
		if (futureList != null) {
			for (Future<?> future : futureList) {
				this.cancel(future);
			}
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (this.freService != null) {
			if (this.closed.compareAndSet(false, true)) {
				try {
					this.freService.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, e.getMessage());
				}
			}
		}
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return this.closed.get();
	}
}
