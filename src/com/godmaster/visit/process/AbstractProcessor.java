package com.godmaster.visit.process;


public abstract class AbstractProcessor implements Processor {

	protected Processor processor;

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}
}
