package com.sxdsf.visit.parse.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

public class HttpResponse2HttpEntityParser extends
		HttpResponseParser<HttpEntity> {

	@Override
	public HttpEntity parse(HttpResponse response) {
		// TODO Auto-generated method stub
		HttpEntity entity = null;
		if (response != null) {
			StatusLine sl = response.getStatusLine();
			if (sl != null) {
				HttpResponseParser<?> parser = HttpResponseParserFactory
						.create(sl.getStatusCode());
				if (parser != null) {
					if (parser.parse(response) != null
							&& parser.parse(response) instanceof HttpEntity) {
						entity = (HttpEntity) parser.parse(response);
					}
				}
			}
		}
		return entity;
	}
}
