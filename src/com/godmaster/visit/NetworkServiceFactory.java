package com.godmaster.visit;

import com.godmaster.visit.impl.AsyncNetworkServiceImpl;
import com.godmaster.visit.impl.SyncNetworkServiceImpl;

public class NetworkServiceFactory {
	public static final NetworkService create(UseModeEnum ue) {
		NetworkService ns = null;
		if (ue != null) {
			switch (ue) {
			case ASYNC:
				ns = new AsyncNetworkServiceImpl();
				break;
			case SYNC:
				ns = new SyncNetworkServiceImpl();
				break;
			}
		}
		return ns;
	}
}
