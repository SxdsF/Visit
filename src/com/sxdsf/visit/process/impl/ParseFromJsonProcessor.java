package com.sxdsf.visit.process.impl;

import org.apache.http.HttpEntity;
import org.json.JSONObject;

public abstract class ParseFromJsonProcessor<T> extends HttpEntityProcessor<T> {

	@Override
	public T process(HttpEntity v) {
		// TODO Auto-generated method stub
		return this.parseFromJson(new HttpEntity2JsonProcessor().process(v));
	}

	protected abstract T parseFromJson(JSONObject json);

}
