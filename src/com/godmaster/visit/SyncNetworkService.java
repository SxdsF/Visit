package com.godmaster.visit;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import com.godmaster.visit.process.Processor;

public interface SyncNetworkService extends NetworkService {

	public <T> T syncGet(String uri, RequestParams params, Processor processor,
			long time, TimeUnit tu);

	public <T> T syncGet(String uri, RequestParams params, Processor processor);

	public <T> T syncGet(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu);

	public <T> T syncGet(URI uri, RequestParams params, Processor processor);

	public <T> T syncPost(String uri, RequestParams params,
			Processor processor, long time, TimeUnit tu);

	public <T> T syncPost(String uri, RequestParams params, Processor processor);

	public <T> T syncPost(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu);

	public <T> T syncPost(URI uri, RequestParams params, Processor processor);
}
