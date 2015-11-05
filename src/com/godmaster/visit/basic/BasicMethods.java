package com.godmaster.visit.basic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.godmaster.visit.callback.exception.NoResponseException;

public class BasicMethods {
	public static HttpEntity GET(HttpClient client, String uri)
			throws URISyntaxException, ClientProtocolException, IOException,
			NoResponseException {
		return GET(client, uri, null);
	}

	public static HttpEntity GET(HttpClient client, String uri,
			Map<String, String> params) throws URISyntaxException,
			ClientProtocolException, IOException, NoResponseException {
		return GET(client, new URI(uri), params);
	}

	public static HttpEntity GET(HttpClient client, URI uri)
			throws ClientProtocolException, URISyntaxException, IOException,
			NoResponseException {
		return GET(client, uri, null);
	}

	public static HttpEntity GET(HttpClient client, URI uri,
			Map<String, String> params) throws URISyntaxException,
			ClientProtocolException, IOException, NoResponseException {
		HttpEntity entity = null;
		if (client != null) {
			if (params != null && !params.isEmpty() && uri != null) {
				Set<Entry<String, String>> set = params.entrySet();
				if (set != null && !set.isEmpty()) {
					List<NameValuePair> qparams = new ArrayList<>();
					for (Entry<String, String> param : set) {
						if (param != null) {
							qparams.add(new BasicNameValuePair(param.getKey(),
									param.getValue()));
						}
					}
					uri = URIUtils.createURI(uri.getScheme(), uri.getHost(),
							uri.getPort(), uri.getPath(),
							URLEncodedUtils.format(qparams, "UTF-8"),
							uri.getFragment());
				}
			}
			HttpGet get = new HttpGet(uri);
			HttpResponse response = client.execute(get,
					new ResponseHandler<HttpResponse>() {

						@Override
						public HttpResponse handleResponse(HttpResponse arg0)
								throws ClientProtocolException, IOException {
							// TODO Auto-generated method stub
							return arg0;
						}
					});
			if (response != null) {
				entity = response.getEntity();
			} else {
				throw new NoResponseException();
			}

		}
		return entity;
	}

	public static HttpEntity POST(HttpClient client, String uri,
			HttpEntity params) throws ClientProtocolException, IOException,
			URISyntaxException, NoResponseException {
		return POST(client, new URI(uri), params);
	}

	public static HttpEntity POST(HttpClient client, URI uri, HttpEntity params)
			throws ClientProtocolException, IOException, NoResponseException {
		HttpEntity entity = null;
		if (client != null) {
			HttpPost post = new HttpPost(uri);
			post.setEntity(params);
			HttpResponse response = client.execute(post,
					new ResponseHandler<HttpResponse>() {

						@Override
						public HttpResponse handleResponse(HttpResponse arg0)
								throws ClientProtocolException, IOException {
							// TODO Auto-generated method stub
							return arg0;
						}
					});
			if (response != null) {
				entity = response.getEntity();
			} else {
				throw new NoResponseException();
			}

		}
		return entity;
	}
}
