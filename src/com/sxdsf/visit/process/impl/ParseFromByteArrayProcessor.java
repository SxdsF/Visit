package com.sxdsf.visit.process.impl;

import org.apache.http.HttpEntity;

public abstract class ParseFromByteArrayProcessor<T> extends
		HttpEntityProcessor<T> {

	@Override
	public T process(HttpEntity v) {
		// TODO Auto-generated method stub
		return this.parseFromByteArray(new HttpEntity2ByteArrayProcessor()
				.process(v));
	}

	protected abstract T parseFromByteArray(byte[] response);

}
