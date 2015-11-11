package com.sxdsf.visit.handler;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

import com.sxdsf.visit.parse.impl.HttpResponseParser;
import com.sxdsf.visit.process.Processor;

public class CommonProcessResponseHandler<T, V> implements ResponseHandler<T> {

	private final Processor<T, V> processor;
	private final HttpResponseParser<V> parser;

	public CommonProcessResponseHandler(HttpResponseParser<V> parser,
			Processor<T, V> processor) {
		this.parser = parser;
		this.processor = processor;
	}

	@Override
	public T handleResponse(HttpResponse arg0) throws ClientProtocolException,
			IOException {
		// TODO Auto-generated method stub
		return (this.parser != null && this.processor != null) ? this.processor
				.process(this.parser.parse(arg0)) : null;
	}

}
