package com.godmaster.visit.parse;

public interface Parser<T, V> {
	public T parse(V v);
}
