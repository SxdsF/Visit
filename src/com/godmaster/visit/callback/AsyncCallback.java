package com.godmaster.visit.callback;

import org.apache.http.concurrent.FutureCallback;

public interface AsyncCallback<T> extends FutureCallback<T> {
	/**
	 * Regardless of success or failure,it will invoke this callback method.
	 */
	public void onFinish();

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
