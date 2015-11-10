package com.godmaster.visit;

import com.godmaster.visit.process.impl.EntityProcessor;

/**
 * A synchronous service of network.
 * 
 * @author sunbowen
 * 
 */
public interface SyncNetworkService extends NetworkService {

	/**
	 * A synchronous "get" method based on HTTP/HTTPS.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @return the default response of this method.
	 */
	public Response syncGet(String uri);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @return the default response of this method.
	 */
	public Response syncGet(String uri, RequestParams params);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> T syncGet(String uri, EntityProcessor<T> processor);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> T syncGet(String uri, RequestParams params,
			EntityProcessor<T> processor);

	/**
	 * A synchronous "post" method based on HTTP/HTTPS.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @return the default response of this method.
	 */
	public Response syncPost(String uri, RequestParams params);

	/**
	 * The same as above.
	 * 
	 * @param uri
	 *            the uri in form of String.
	 * @param params
	 *            the request params.
	 * @param processor
	 *            the response processor.
	 * @return the value of your custom.
	 */
	public <T> T syncPost(String uri, RequestParams params,
			EntityProcessor<T> processor);
}
