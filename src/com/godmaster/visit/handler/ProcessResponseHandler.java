package com.godmaster.visit.handler;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import com.godmaster.visit.parse.impl.DefaultResponseParser;
import com.godmaster.visit.parse.impl.ResponseParser;
import com.godmaster.visit.process.impl.EntityProcessor;

public class ProcessResponseHandler<T> implements ResponseHandler<T> {

	private EntityProcessor<T> processor;
	private final ResponseParser parser;

	public ProcessResponseHandler(EntityProcessor<T> processor) {
		this(new DefaultResponseParser(), processor);
	}

	private ProcessResponseHandler(ResponseParser parser,
			EntityProcessor<T> processor) {
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
