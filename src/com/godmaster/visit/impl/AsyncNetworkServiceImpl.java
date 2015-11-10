package com.godmaster.visit.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;
import com.godmaster.visit.AsyncNetworkService;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.callback.AbstractCallback;
import com.godmaster.visit.handler.DefaultResponseHandler;
import com.godmaster.visit.handler.ProcessResponseHandler;
import com.godmaster.visit.process.impl.EntityProcessor;

public class AsyncNetworkServiceImpl implements AsyncNetworkService {

	private final FutureRequestExecutionService freService;
	private final AtomicBoolean closed = new AtomicBoolean(false);

	private static final String TAG = "AsyncNetworkService";

	public AsyncNetworkServiceImpl() {
		this(HttpClients.createDefault(), Executors.newFixedThreadPool(3));
	}

	public AsyncNetworkServiceImpl(HttpClient client) {
		this(client, Executors.newFixedThreadPool(3));
	}

	public AsyncNetworkServiceImpl(ExecutorService service) {
		this(HttpClients.createDefault(), service);
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
		if (!this.isClosed()) {
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
		if (!this.isClosed()) {
			future = this.freService.execute(RequestBuilder.post().build(),
					HttpClientContext.create(), new ProcessResponseHandler<T>(
							processor), callback);
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
