package com.godmaster.visit;

import org.apache.http.HttpEntity;

public final class Event {
	public int messageCode;
	public HttpEntity entity;
	public Throwable throwable;

	public static final int COMPLETED = 0;
	public static final int SUCCESS = 1;
	public static final int FAILURE = 2;
}
