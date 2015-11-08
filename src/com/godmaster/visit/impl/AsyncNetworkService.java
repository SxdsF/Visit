package com.godmaster.visit.impl;

import java.util.List;
import java.util.concurrent.Future;
import com.godmaster.visit.NetworkService;

public class AsyncNetworkService implements NetworkService {

	@Override
	public void cancel(Future<?> future) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel(List<Future<?>> futureList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isclosed() {
		// TODO Auto-generated method stub
		return false;
	}

}
