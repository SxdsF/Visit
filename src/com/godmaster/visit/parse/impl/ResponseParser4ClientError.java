package com.godmaster.visit.parse.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class ResponseParser4ClientError extends ResponseParser {

	@Override
	public HttpEntity parse(HttpResponse response) {
		// TODO Auto-generated method stub
		return response != null ? response.getEntity() : null;
	}

}
