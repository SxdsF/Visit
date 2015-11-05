package com.godmaster.visit.impl;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import com.godmaster.visit.AsyncNetworkService;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.callback.AsyncCallback;

public class AsyncNetworkServiceImpl implements AsyncNetworkService {

	@Override
	public void asyncGet(String uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asyncGet(URI uri, RequestParams params, AsyncCallback callback,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asyncPost(String uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu) {
		// TODO Auto-generated method stub

	}

	@Override
	public void asyncPost(URI uri, RequestParams params,
			AsyncCallback callback, long time, TimeUnit tu) {
		// TODO Auto-generated method stub

	}

}
