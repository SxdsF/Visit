package com.godmaster.visit.impl;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.Response;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.basic.BasicMethods;
import com.godmaster.visit.handler.DefaultResponseHandler;
import com.godmaster.visit.handler.ProcessResponseHandler;
import com.godmaster.visit.process.impl.EntityProcessor;

public class SyncNetworkServiceImpl implements SyncNetworkService {

	private final HttpClient client;
	private final AtomicBoolean closed = new AtomicBoolean(false);

	private static final String TAG = "SyncNetworkService";

	public SyncNetworkServiceImpl() {
		this(HttpClients.createDefault());
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
				response = BasicMethods.request(this.client,
						RequestBuilder.get().build(),
						new DefaultResponseHandler());
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
	public <T> T syncGet(String uri, EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, null, processor);
	}

	@Override
	public <T> T syncGet(String uri, RequestParams params,
			EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				response = BasicMethods.request(this.client,
						RequestBuilder.get().build(),
						new ProcessResponseHandler<T>(processor));
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
				response = BasicMethods.request(this.client,
						RequestBuilder.post().build(),
						new DefaultResponseHandler());
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
			EntityProcessor<T> processor) {
		// TODO Auto-generated method stub
		T response = null;
		if (!this.isClosed()) {
			try {
				response = BasicMethods.request(this.client,
						RequestBuilder.post().build(),
						new ProcessResponseHandler<T>(processor));
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
