package com.godmaster.visit;

import java.util.concurrent.Future;
import com.godmaster.visit.callback.AbstractCallback;
import com.godmaster.visit.process.impl.EntityProcessor;

public abstract class AsyncNetworkService extends AbstractNetworkService {
	public abstract Future<Response> asyncGet(String uri,
			AbstractCallback<Response> callback);

	public abstract Future<Response> asyncGet(String uri, RequestParams params,
			AbstractCallback<Response> callback);

	public abstract <T> Future<T> asyncGet(String uri,
			AbstractCallback<T> callback, EntityProcessor<T> processor);

	public abstract <T> Future<T> asyncGet(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor);

	public abstract Future<Response> asyncPost(String uri,
			RequestParams params, AbstractCallback<Response> callback);

	public abstract <T> Future<T> asyncPost(String uri, RequestParams params,
			AbstractCallback<T> callback, EntityProcessor<T> processor);
}
