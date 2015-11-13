package com.sxdsf.visit.callback;

import org.apache.http.concurrent.FutureCallback;

public interface AsyncCallback<T> extends FutureCallback<T> {
	/**
	 * It will invoke this callback when the task is scheduled.
	 * 
	 * @param scheduledTime
	 */
	public void onScheduled(long scheduledTime);

	/**
	 * It will invoke this callback when the task is started.
	 * 
	 * @param startedTime
	 */
	public void onStarted(long startedTime);

	/**
	 * It will invoke this callback when the task is ended.
	 * 
	 * @param endedTime
	 */
	public void onEnded(long endedTime);

	/**
	 * Regardless of success or failure,it will invoke this callback method.
	 */
	public void onFinish(long taskDurition, long networkAccessDurition);

	/**
	 * It will invoke this callback method in success.
	 * 
	 * @param <T>
	 * 
	 * @param response
	 */
	public void onSuccess(T response);

	/**
	 * It will invoke this callback method when it falls.
	 * 
	 * @param t
	 */
	public void onFailure(Exception t);

	/**
	 * It will invoke this callback method when it cancels.
	 */
	public void onCancelled();
}
