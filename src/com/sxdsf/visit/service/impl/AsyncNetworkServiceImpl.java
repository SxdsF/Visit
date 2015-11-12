package com.sxdsf.visit.service.impl;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;
import com.sxdsf.visit.callback.AbstractCallback;
import com.sxdsf.visit.common.CommonProcessResponseHandler;
import com.sxdsf.visit.common.DefaultResponseHandler;
import com.sxdsf.visit.common.HttpEntityProcessResponseHandler;
import com.sxdsf.visit.common.RequestHandler;
import com.sxdsf.visit.common.RequestParams;
import com.sxdsf.visit.common.Response;
import com.sxdsf.visit.parse.impl.HttpResponseParser;
import com.sxdsf.visit.process.Processor;
import com.sxdsf.visit.process.impl.HttpEntityProcessor;
import com.sxdsf.visit.service.AsyncNetworkService;

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
	public RequestHandler<Response> asyncGet(String uri,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, null, callback);
	}

	@Override
	public RequestHandler<Response> asyncGet(String uri, RequestParams params,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		RequestHandler<Response> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = new RequestHandler<Response>(this.freService.execute(
					get.build(), HttpClientContext.create(),
					new DefaultResponseHandler(), callback));
		}
		return future;
	}

	@Override
	public <T> RequestHandler<T> asyncGet(String uri,
			AbstractCallback<T> callback, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, (RequestParams) null, callback, processor);
	}

	@Override
	public <T> RequestHandler<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		RequestHandler<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = new RequestHandler<T>(this.freService.execute(get.build(),
					HttpClientContext.create(),
					new HttpEntityProcessResponseHandler<T>(processor),
					callback));
		}
		return future;
	}

	@Override
	public <T, V> RequestHandler<T> asyncGet(String uri,
			AbstractCallback<T> callback, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		return this.asyncGet(uri, (RequestParams) null, callback, parser,
				processor);
	}

	@Override
	public <T, V> RequestHandler<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		RequestHandler<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder get = RequestBuilder.get().setUri(uri);
			if (params != null) {
				List<NameValuePair> paramsList = params.getParamsList();
				if (paramsList != null && !paramsList.isEmpty()) {
					get.addParameters(paramsList
							.toArray(new NameValuePair[paramsList.size()]));
				}
			}
			future = new RequestHandler<T>(this.freService.execute(get.build(),
					HttpClientContext.create(),
					new CommonProcessResponseHandler<T, V>(parser, processor),
					callback));
		}
		return future;
	}

	@Override
	public RequestHandler<Response> asyncPost(String uri, RequestParams params,
			AbstractCallback<Response> callback) {
		// TODO Auto-generated method stub
		RequestHandler<Response> future = null;
		if (!this.isClosed()) {
			RequestBuilder post = RequestBuilder.post().setUri(uri);
			if (params != null) {
				post.setEntity(params.createFormEntity());
			}
			future = new RequestHandler<Response>(this.freService.execute(
					post.build(), HttpClientContext.create(),
					new DefaultResponseHandler(), callback));
		}
		return future;
	}

	@Override
	public <T> RequestHandler<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		WeakReference<AbstractCallback<T>> callbackReference = new WeakReference<AbstractCallback<T>>(
				callback);
		callback = null;
		WeakReference<HttpEntityProcessor<T>> processorReference = new WeakReference<HttpEntityProcessor<T>>(
				processor);
		processor = null;
		RequestHandler<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder post = RequestBuilder.post().setUri(uri);
			if (params != null) {
				post.setEntity(params.createFormEntity());
			}
			future = new RequestHandler<T>(this.freService.execute(
					post.build(),
					HttpClientContext.create(),
					new HttpEntityProcessResponseHandler<T>(processorReference
							.get()), callbackReference.get()));
		}
		return future;
	}

	@Override
	public <T, V> RequestHandler<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		RequestHandler<T> future = null;
		if (!this.isClosed()) {
			RequestBuilder post = RequestBuilder.post().setUri(uri);
			if (params != null) {
				post.setEntity(params.createFormEntity());
			}
			future = new RequestHandler<T>(this.freService.execute(
					post.build(), HttpClientContext.create(),
					new CommonProcessResponseHandler<T, V>(parser, processor),
					callback));
		}
		return future;
	}

	@Override
	public void cancel(RequestHandler<?> future) {
		// TODO Auto-generated method stub
		if (future != null && !future.isCancelled() && !future.isDone()) {
			future.cancel(true);
		}
	}

	@Override
	public void cancel(List<RequestHandler<?>> futureList) {
		// TODO Auto-generated method stub
		if (futureList != null) {
			for (RequestHandler<?> future : futureList) {
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
