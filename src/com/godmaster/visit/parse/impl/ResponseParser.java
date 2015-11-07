package com.godmaster.visit.parse.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import com.godmaster.visit.parse.Parser;

public abstract class ResponseParser implements
		Parser<HttpEntity, HttpResponse> {

	protected ResponseParser parser;

	public ResponseParser getParser() {
		return parser;
	}

	public void setParser(ResponseParser parser) {
		this.parser = parser;
	}
}
