package com.sxdsf.visit.process;

public interface Processor<T, V> {
	public T process(V v);
}
