package com.godmaster.visit;

import java.util.concurrent.Future;
import com.godmaster.visit.process.impl.DataProcessor;

public interface SyncNetworkService extends NetworkService {

	public Future<Response> syncGet(String uri);

	public Future<Response> syncGet(String uri, RequestParams params);

	public <T> Future<T> syncGet(String uri, DataProcessor<T> processor);

	public <T> Future<T> syncGet(String uri, RequestParams params,
			DataProcessor<T> processor);

	public Future<Response> syncPost(String uri, RequestParams params);

	public <T> Future<T> syncPost(String uri, RequestParams params,
			DataProcessor<T> processor);
}
