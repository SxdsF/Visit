package com.sxdsf.visit.common;

import org.apache.http.impl.client.HttpRequestFutureTask;

public class RequestHandler<V> {
	private final HttpRequestFutureTask<V> futureTask;

	public RequestHandler(HttpRequestFutureTask<V> futureTask) {
		this.futureTask = futureTask;
	}

	public boolean cancel(final boolean mayInterruptIfRunning) {
		return this.futureTask != null ? this.futureTask
				.cancel(mayInterruptIfRunning) : false;
	}

	public boolean isCancelled() {
		return this.futureTask != null ? this.futureTask.isCancelled() : false;
	}

	public boolean isDone() {
		return this.futureTask != null ? this.futureTask.isDone() : false;
	}

	/**
	 * @return the time in millis the task was scheduled.
	 */
	public long scheduledTime() {
		return this.futureTask != null ? this.futureTask.scheduledTime() : 0;
	}

	/**
	 * @return the time in millis the task was started.
	 */
	public long startedTime() {
		return this.futureTask != null ? this.futureTask.startedTime() : 0;
	}

	/**
	 * @return the time in millis the task was finished/cancelled.
	 */
	public long endedTime() {
		return this.futureTask != null ? this.futureTask.endedTime() : 0;
	}

	/**
	 * @return the time in millis it took to make the request (excluding the
	 *         time it was scheduled to be executed).
	 */
	public long requestDuration() {
		return this.futureTask != null ? this.futureTask.requestDuration() : 0;
	}

	/**
	 * @return the time in millis it took to execute the task from the moment it
	 *         was scheduled.
	 */
	public long taskDuration() {
		return this.futureTask != null ? this.futureTask.taskDuration() : 0;
	}

	@Override
	public String toString() {
		return this.futureTask != null ? this.futureTask.toString() : null;
	}
}
