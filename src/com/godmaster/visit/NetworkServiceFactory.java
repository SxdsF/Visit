package com.godmaster.visit;

import com.godmaster.visit.impl.AsyncNetworkService;

public class NetworkServiceFactory {
	public static final NetworkService create(UseModeEnum ue) {
		NetworkService ns = null;
		if (ue != null) {
			switch (ue) {
			case ASYNC:
				ns = new AsyncNetworkService();
				break;
			case SYNC:
				break;
			default:
				break;

			}
		}
		return ns;
	}
}
