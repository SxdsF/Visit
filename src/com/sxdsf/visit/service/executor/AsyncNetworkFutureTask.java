package com.sxdsf.visit.service.executor;

import java.util.concurrent.FutureTask;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Copy from apache's HttpRequestFutureTask
 * 
 * @author sunbowen
 * 
 * @param <V>
 */
public class AsyncNetworkFutureTask<V> extends FutureTask<V> {

	private final HttpUriRequest request;
	private final AsyncNetworkTaskCallable<V> callable;

	public AsyncNetworkFutureTask(final HttpUriRequest request,
			final AsyncNetworkTaskCallable<V> httpCallable) {
		super(httpCallable);
		this.request = request;
		this.callable = httpCallable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.FutureTask#cancel(boolean)
	 */
	@Override
	public boolean cancel(final boolean mayInterruptIfRunning) {
		callable.cancel();
		if (mayInterruptIfRunning) {
			request.abort();
		}
		return super.cancel(mayInterruptIfRunning);
	}

	/**
	 * @return the time in millis the task was scheduled.
	 */
	public long scheduledTime() {
		return callable.getScheduled();
	}

	/**
	 * @return the time in millis the task was started.
	 */
	public long startedTime() {
		return callable.getStarted();
	}

	/**
	 * @return the time in millis the task was finished/cancelled.
	 */
	public long endedTime() {
		if (isDone()) {
			return callable.getEnded();
		} else {
			throw new IllegalStateException("Task is not done yet");
		}
	}

	/**
	 * @return the time in millis it took to make the request (excluding the
	 *         time it was scheduled to be executed).
	 */
	public long requestDuration() {
		if (isDone()) {
			return endedTime() - startedTime();
		} else {
			throw new IllegalStateException("Task is not done yet");
		}
	}

	/**
	 * @return the time in millis it took to execute the task from the moment it
	 *         was scheduled.
	 */
	public long taskDuration() {
		if (isDone()) {
			return endedTime() - scheduledTime();
		} else {
			throw new IllegalStateException("Task is not done yet");
		}
	}

	@Override
	public String toString() {
		return request.getRequestLine().getUri();
	}

}
