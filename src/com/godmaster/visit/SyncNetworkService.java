package com.godmaster.visit;

import java.util.concurrent.Future;
import com.godmaster.visit.process.impl.EntityProcessor;

public abstract class SyncNetworkService extends AbstractNetworkService {

	public abstract Future<Response> syncGet(String uri);

	public abstract Future<Response> syncGet(String uri, RequestParams params);

	public abstract <T> Future<T> syncGet(String uri,
			EntityProcessor<T> processor);

	public abstract <T> Future<T> syncGet(String uri, RequestParams params,
			EntityProcessor<T> processor);

	public abstract Future<Response> syncPost(String uri, RequestParams params);

	public abstract <T> Future<T> syncPost(String uri, RequestParams params,
			EntityProcessor<T> processor);
}
