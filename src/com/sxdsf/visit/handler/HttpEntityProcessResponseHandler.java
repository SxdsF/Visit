package com.sxdsf.visit.handler;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.sxdsf.visit.parse.impl.HttpResponse2HttpEntityParser;
import com.sxdsf.visit.parse.impl.HttpResponseParser;
import com.sxdsf.visit.process.impl.HttpEntityProcessor;

public class HttpEntityProcessResponseHandler<T> implements ResponseHandler<T> {

	private final HttpEntityProcessor<T> processor;
	private final HttpResponseParser<HttpEntity> parser;

	public HttpEntityProcessResponseHandler(HttpEntityProcessor<T> processor) {
		this(new HttpResponse2HttpEntityParser(), processor);
	}

	private HttpEntityProcessResponseHandler(
			HttpResponseParser<HttpEntity> parser,
			HttpEntityProcessor<T> processor) {
		this.processor = processor;
		this.parser = parser;
	}

	@Override
	public T handleResponse(HttpResponse arg0) throws ClientProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return this.processor != null ? this.processor.process(this.parser
				.parse(arg0)) : null;
	}

}
