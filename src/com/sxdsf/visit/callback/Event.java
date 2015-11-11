package com.sxdsf.visit.callback;

public final class Event<T> {
	public int messageCode;
	public T messageEntity;

	public static final int COMPLETED = 0;
	public static final int SUCCESS = 1;
	public static final int FAILURE = 2;
	public static final int CANCELLED = 3;
}
