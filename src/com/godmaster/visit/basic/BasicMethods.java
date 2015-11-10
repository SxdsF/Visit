package com.godmaster.visit.basic;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

public final class BasicMethods {
	/**
	 * 
	 * @param client
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final HttpResponse request(HttpClient client,
			HttpUriRequest request) throws ClientProtocolException, IOException {
		return request(client, request, (HttpContext) null);
	}

	/**
	 * 
	 * @param client
	 * @param request
	 * @param context
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final HttpResponse request(HttpClient client,
			HttpUriRequest request, HttpContext context)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		if (client != null) {
			response = client.execute(request, context);
		}
		return response;
	}

	/**
	 * 
	 * @param client
	 * @param request
	 * @param handler
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final <T> T request(HttpClient client,
			HttpUriRequest request, ResponseHandler<T> handler)
			throws ClientProtocolException, IOException {
		return request(client, request, handler, null);
	}

	/**
	 * 
	 * @param client
	 * @param request
	 * @param handler
	 * @param context
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final <T> T request(HttpClient client,
			HttpUriRequest request, ResponseHandler<T> handler,
			HttpContext context) throws ClientProtocolException, IOException {
		T response = null;
		if (client != null) {
			response = client.execute(request, handler, context);
		}
		return response;
	}

	/**
	 * 
	 * @param client
	 * @param host
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final HttpResponse request(HttpClient client, HttpHost host,
			HttpRequest request) throws ClientProtocolException, IOException {
		return request(client, host, request, (HttpContext) null);
	}

	/**
	 * 
	 * @param client
	 * @param host
	 * @param request
	 * @param context
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final HttpResponse request(HttpClient client, HttpHost host,
			HttpRequest request, HttpContext context)
			throws ClientProtocolException, IOException {
		HttpResponse response = null;
		if (client != null) {
			response = client.execute(host, request, context);
		}
		return response;
	}

	/**
	 * 
	 * @param client
	 * @param host
	 * @param request
	 * @param handler
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final <T> T request(HttpClient client, HttpHost host,
			HttpRequest request, ResponseHandler<T> handler)
			throws ClientProtocolException, IOException {
		return request(client, host, request, handler, null);
	}

	/**
	 * 
	 * @param client
	 * @param host
	 * @param request
	 * @param handler
	 * @param context
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static final <T> T request(HttpClient client, HttpHost host,
			HttpRequest request, ResponseHandler<T> handler, HttpContext context)
			throws ClientProtocolException, IOException {
		T response = null;
		if (client != null) {
			response = client.execute(host, request, handler, context);
		}
		return response;
	}
}
