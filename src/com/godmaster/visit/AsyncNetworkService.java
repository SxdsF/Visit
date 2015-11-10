package com.godmaster.visit;

import java.util.List;
import java.util.concurrent.Future;
import com.godmaster.visit.callback.AbstractCallback;
import com.godmaster.visit.process.impl.EntityProcessor;

/**
 * An asynchronous service of network.
 * 
 * @author sunbowen
 * 
 */
public interface AsyncNetworkService extends NetworkService {

	/**
	 * An asynchronous "get" method based on HTTP/HTTPS.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param callback
	 *            the callback of this method.
	 * @return the default response of this method.
	 */
	public Future<Response> asyncGet(String uri,
			AbstractCallback<Response> callback);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param callback
	 *            the callback of this method.
	 * @return the default response of this method.
	 */
	public Future<Response> asyncGet(String uri, RequestParams params,
			AbstractCallback<Response> callback);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param callback
	 *            the callback of this method.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> Future<T> asyncGet(String uri, AbstractCallback<T> callback,
			EntityProcessor<T> processor);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param callback
	 *            the callback of this method.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> Future<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor);

	/**
	 * An asynchronous "post" method based on HTTP/HTTPS.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param callback
	 *            the callback of this method.
	 * @return the default response of this method.
	 */
	public Future<Response> asyncPost(String uri, RequestParams params,
			AbstractCallback<Response> callback);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param callback
	 *            the callback of this method.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> Future<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor);

	/**
	 * You can cancel a request in this method.
	 * 
	 * @param future
	 */
	public void cancel(Future<?> future);

	/**
	 * You can cancel a request's list in this method.
	 * 
	 * @param futureList
	 */
	public void cancel(List<Future<?>> futureList);
}
