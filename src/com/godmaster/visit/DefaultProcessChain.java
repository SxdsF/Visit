package com.godmaster.visit;

import com.godmaster.visit.parse.impl.DefaultResponseParser;

public class DefaultProcessChain extends
		ProcessChain<DefaultResponseHandler, DefaultResponseParser> {

	public DefaultProcessChain() {
		// TODO Auto-generated constructor stub
		this.setResponseHandler(new DefaultResponseHandler());
		this.setParser(new DefaultResponseParser());
	}

}
