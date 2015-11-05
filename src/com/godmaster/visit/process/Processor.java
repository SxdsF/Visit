package com.godmaster.visit.process;

import org.apache.http.HttpEntity;

public abstract class Processor {
	protected Processor processor;

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public abstract <T> T process(HttpEntity entity);
}
