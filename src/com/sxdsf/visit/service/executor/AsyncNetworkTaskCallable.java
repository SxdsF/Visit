package com.sxdsf.visit.service.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;
import com.sxdsf.visit.callback.AsyncCallback;

/**
 * Copy from apache's HttpRequestTaskCallable
 * 
 * @author sunbowen
 * 
 * @param <V>
 */
class AsyncNetworkTaskCallable<V> implements Callable<V> {

	private final HttpUriRequest request;
	private final HttpClient httpclient;
	private final AtomicBoolean cancelled = new AtomicBoolean(false);

	private final long scheduled = System.currentTimeMillis();
	private long started = -1;
	private long ended = -1;

	private final HttpContext context;
	private final ResponseHandler<V> responseHandler;
	private final AsyncCallback<V> callback;

	private final AsyncNetworkExecutionMetrics metrics;

	AsyncNetworkTaskCallable(final HttpClient httpClient,
			final HttpUriRequest request, final HttpContext context,
			final ResponseHandler<V> responseHandler,
			final AsyncCallback<V> callback,
			final AsyncNetworkExecutionMetrics metrics) {
		this.httpclient = httpClient;
		this.responseHandler = responseHandler;
		this.request = request;
		this.context = context;
		this.callback = callback;
		this.metrics = metrics;
		if (this.callback != null) {
			this.callback.onScheduled(scheduled);
		}
	}

	public long getScheduled() {
		return scheduled;
	}

	public long getStarted() {
		return started;
	}

	public long getEnded() {
		return ended;
	}

	public V call() throws Exception {
		if (!cancelled.get()) {
			try {
				metrics.getActiveConnections().incrementAndGet();
				started = System.currentTimeMillis();
				if (callback != null) {
					callback.onStarted(started);
				}
				try {
					metrics.getScheduledConnections().decrementAndGet();
					final V result = httpclient.execute(request,
							responseHandler, context);
					ended = System.currentTimeMillis();
					metrics.getSuccessfulConnections().increment(started);
					if (callback != null) {
						callback.completed(result);
						callback.onEnded(ended);
					}
					return result;
				} catch (final Exception e) {
					metrics.getFailedConnections().increment(started);
					ended = System.currentTimeMillis();
					if (callback != null) {
						callback.failed(e);
						callback.onEnded(ended);
					}
					throw e;
				}
			} finally {
				metrics.getRequests().increment(started);
				metrics.getTasks().increment(started);
				metrics.getActiveConnections().decrementAndGet();
			}
		} else {
			throw new IllegalStateException(
					"call has been cancelled for request " + request.getURI());
		}
	}

	public void cancel() {
		cancelled.set(true);
		if (callback != null) {
			callback.cancelled();
			ended = System.currentTimeMillis();
			callback.onEnded(ended);
		}
	}
}
