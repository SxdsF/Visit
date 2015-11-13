package com.sxdsf.visit.service.executor;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

import com.sxdsf.visit.callback.AsyncCallback;

/**
 * Copy from apache's FutureRequestExecutionService
 * 
 * @author sunbowen
 * 
 */
public class AsyncNetworkExecutionService implements Closeable {
	private final HttpClient httpclient;
	private final ExecutorService executorService;
	private final AsyncNetworkExecutionMetrics metrics = new AsyncNetworkExecutionMetrics();
	private final AtomicBoolean closed = new AtomicBoolean(false);

	/**
	 * Create a new AsyncNetworkExecutionService.
	 * 
	 * @param httpclient
	 *            you should tune your httpclient instance to match your needs.
	 *            You should align the max number of connections in the pool and
	 *            the number of threads in the executor; it doesn't make sense
	 *            to have more threads than connections and if you have less
	 *            connections than threads, the threads will just end up
	 *            blocking on getting a connection from the pool.
	 * @param executorService
	 *            any executorService will do here. E.g.
	 *            {@link java.util.concurrent.Executors#newFixedThreadPool(int)}
	 */
	public AsyncNetworkExecutionService(final HttpClient httpclient,
			final ExecutorService executorService) {
		this.httpclient = httpclient;
		this.executorService = executorService;
	}

	/**
	 * Schedule a request for execution.
	 * 
	 * @param <T>
	 * 
	 * @param request
	 *            request to execute
	 * @param responseHandler
	 *            handler that will process the response.
	 * @return HttpAsyncClientFutureTask for the scheduled request.
	 * @throws InterruptedException
	 */
	public <T> AsyncNetworkFutureTask<T> execute(final HttpUriRequest request,
			final HttpContext context, final ResponseHandler<T> responseHandler) {
		return execute(request, context, responseHandler, null);
	}

	/**
	 * Schedule a request for execution.
	 * 
	 * @param <T>
	 * 
	 * @param request
	 *            request to execute
	 * @param context
	 *            optional context; use null if not needed.
	 * @param responseHandler
	 *            handler that will process the response.
	 * @param callback
	 *            callback handler that will be called when the request is
	 *            scheduled, started, completed, failed, or cancelled.
	 * @return HttpAsyncClientFutureTask for the scheduled request.
	 * @throws InterruptedException
	 */
	public <T> AsyncNetworkFutureTask<T> execute(final HttpUriRequest request,
			final HttpContext context,
			final ResponseHandler<T> responseHandler,
			final AsyncCallback<T> callback) {
		if (closed.get()) {
			throw new IllegalStateException(
					"Close has been called on this httpclient instance.");
		}
		metrics.getScheduledConnections().incrementAndGet();
		final AsyncNetworkTaskCallable<T> callable = new AsyncNetworkTaskCallable<T>(
				httpclient, request, context, responseHandler, callback,
				metrics);
		final AsyncNetworkFutureTask<T> httpRequestFutureTask = new AsyncNetworkFutureTask<T>(
				request, callable);
		executorService.execute(httpRequestFutureTask);

		return httpRequestFutureTask;
	}

	/**
	 * @return metrics gathered for this instance.
	 * @see AsyncNetworkExecutionMetrics
	 */
	public AsyncNetworkExecutionMetrics metrics() {
		return metrics;
	}

	public void close() throws IOException {
		closed.set(true);
		executorService.shutdownNow();
		if (httpclient instanceof Closeable) {
			((Closeable) httpclient).close();
		}
	}
}
