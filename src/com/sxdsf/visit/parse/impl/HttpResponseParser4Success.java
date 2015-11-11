package com.sxdsf.visit.parse.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class HttpResponseParser4Success extends HttpResponseParser<HttpEntity> {

	@Override
	public HttpEntity parse(HttpResponse response) {
		// TODO Auto-generated method stub
		return response != null ? response.getEntity() : null;
	}

}
