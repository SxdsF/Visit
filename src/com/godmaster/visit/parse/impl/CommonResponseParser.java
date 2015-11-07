package com.godmaster.visit.parse.impl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

public class CommonResponseParser extends ResponseParser {

	@Override
	public HttpEntity parse(HttpResponse response) {
		// TODO Auto-generated method stub
		HttpEntity entity = null;
		if (response != null) {
			StatusLine sl = response.getStatusLine();
			if (sl != null) {
				ResponseParser parser = ResponseParserFactory.create(sl
						.getStatusCode());
				if (parser != null) {
					entity = parser.parse(response);
				}
			}
		}
		return entity;
	}

}
