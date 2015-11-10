package com.godmaster.visit.process.queue;

import java.util.LinkedList;
import java.util.Queue;
import com.godmaster.visit.process.Processor;

public class ProcessorQueueBuilder {

	private Queue<Processor<?, ?>> queue = null;

	protected ProcessorQueueBuilder() {
		this.queue = new LinkedList<>();
	}

	public final ProcessorQueueBuilder create() {
		return new ProcessorQueueBuilder();
	}

	public void append(Processor<?, ?> processor) {
	}

	public final Queue<Processor<?, ?>> build() {
		return this.queue;
	}
}
