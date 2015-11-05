package com.godmaster.visit;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import com.godmaster.visit.callback.AsyncCallback;

public interface AsyncNetworkService extends NetworkService {

	public void asyncGet(String uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu);

	public void asyncGet(String uri, RequestParams params,
			AsyncCallback callback);

	public void asyncGet(URI uri, RequestParams params, AsyncCallback callback,
			long time, TimeUnit tu);

	public void asyncGet(URI uri, RequestParams params, AsyncCallback callback);

	public void asyncPost(String uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu);

	public void asyncPost(String uri, RequestParams params,
			AsyncCallback callback);

	public void asyncPost(URI uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu);

	public void asyncPost(URI uri, RequestParams params, AsyncCallback callback);
}
