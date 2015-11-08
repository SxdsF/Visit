package com.godmaster.visit;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import android.util.Log;

public abstract class AbstractNetworkService implements NetworkService {

	protected final FutureRequestExecutionService freService;
	protected final AtomicBoolean closed = new AtomicBoolean(false);

	private static final String TAG = "AbstractNetworkService";

	public AbstractNetworkService() {
		this(HttpClients.createDefault(), Executors.newFixedThreadPool(3));
	}

	public AbstractNetworkService(HttpClient client, ExecutorService service) {
		this.freService = new FutureRequestExecutionService(client, service);
	}

	@Override
	public void cancel(Future<?> future) {
		// TODO Auto-generated method stub
		if (future != null && !future.isCancelled() && !future.isDone()) {
			future.cancel(true);
		}
	}

	@Override
	public void cancel(List<Future<?>> futureList) {
		// TODO Auto-generated method stub
		if (futureList != null) {
			for (Future<?> future : futureList) {
				this.cancel(future);
			}
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		if (this.freService != null) {
			if (this.closed.compareAndSet(false, true)) {
				try {
					this.freService.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, e.getMessage());
				}
			}
		}
	}

	@Override
	public boolean isclosed() {
		// TODO Auto-generated method stub
		return this.closed.get();
	}

}
