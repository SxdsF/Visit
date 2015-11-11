package com.godmaster.visit.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.basic.BasicMethods;
import com.godmaster.visit.handler.CommonProcessResponseHandler;
import com.godmaster.visit.handler.DefaultResponseHandler;
import com.godmaster.visit.handler.HttpEntityProcessResponseHandler;
import com.godmaster.visit.parse.impl.HttpResponseParser;
import com.godmaster.visit.process.Processor;
import com.godmaster.visit.process.impl.HttpEntityProcessor;

public class SyncNetworkServiceImpl implements SyncNetworkService {

	private final HttpClient client;
	private final AtomicBoolean closed = new AtomicBoolean(false);

	private static final String TAG = "SyncNetworkService";

	public SyncNetworkServiceImpl() {
		this(HttpClients.createSystem());
	}

	public SyncNetworkServiceImpl(HttpClient client) {
		this.client = client;
	}

	@Override
	public Response syncGet(String uri) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, (RequestParams) null);
	}

	@Override
	public Response syncGet(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		Response response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder get = RequestBuilder.get().setUri(uri);
				if (params != null) {
					List<NameValuePair> paramsList = params.getParamsList();
					if (paramsList != null && !paramsList.isEmpty()) {
						get.addParameters(paramsList
								.toArray(new NameValuePair[paramsList.size()]));
					}
				}
				response = BasicMethods.request(this.client, get.build(),
						new DefaultResponseHandler(),
						HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public <T> T syncGet(String uri, HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, (RequestParams) null, processor);
	}

	@Override
	public <T, V> T syncGet(String uri, HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, null, parser, processor);
	}

	@Override
	public <T> T syncGet(String uri, RequestParams params,
			HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder get = RequestBuilder.get().setUri(uri);
				if (params != null) {
					List<NameValuePair> paramsList = params.getParamsList();
					if (paramsList != null && !paramsList.isEmpty()) {
						get.addParameters(paramsList
								.toArray(new NameValuePair[paramsList.size()]));
					}
				}
				response = BasicMethods.request(this.client, get.build(),
						new HttpEntityProcessResponseHandler<T>(processor),
						HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public <T, V> T syncGet(String uri, RequestParams params,
			HttpResponseParser<V> parser, Processor<T, V> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder get = RequestBuilder.get().setUri(uri);
				if (params != null) {
					List<NameValuePair> paramsList = params.getParamsList();
					if (paramsList != null && !paramsList.isEmpty()) {
						get.addParameters(paramsList
								.toArray(new NameValuePair[paramsList.size()]));
					}
				}
				response = BasicMethods.request(this.client, get.build(),
						new CommonProcessResponseHandler<T, V>(parser,
								processor), HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public Response syncPost(String uri, RequestParams params) {
		// TODO Auto-generated method stub
		Response response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder post = RequestBuilder.post().setUri(uri);
				if (params != null) {
					post.setEntity(params.createFormEntity());
				}
				response = BasicMethods.request(this.client, post.build(),
						new DefaultResponseHandler(),
						HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public <T> T syncPost(String uri, RequestParams params,
			HttpEntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder post = RequestBuilder.post().setUri(uri);
				if (params != null) {
					post.setEntity(params.createFormEntity());
				}
				response = BasicMethods.request(this.client, post.build(),
						new HttpEntityProcessResponseHandler<T>(processor),
						HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public <T, V> T syncPost(String uri, RequestParams params,
			HttpResponseParser<V> parser, Processor<T, V> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				RequestBuilder post = RequestBuilder.post().setUri(uri);
				if (params != null) {
					post.setEntity(params.createFormEntity());
				}
				response = BasicMethods.request(this.client, post.build(),
						new CommonProcessResponseHandler<T, V>(parser,
								processor), HttpClientContext.create());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return response;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (this.closed.compareAndSet(false, true)) {
			try {
				if (this.client instanceof Closeable) {
					((Closeable) this.client).close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return this.closed.get();
	}
}
