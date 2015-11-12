package com.sxdsf.visit.process.impl;

import org.apache.http.HttpEntity;

public abstract class ParseFromStringProcessor<T> extends
		HttpEntityProcessor<T> {

	@Override
	public T process(HttpEntity v) {
		// TODO Auto-generated method stub
		return this
				.parseFromString(new HttpEntity2StringProcessor().process(v));
	}

	protected abstract T parseFromString(String response);

}
