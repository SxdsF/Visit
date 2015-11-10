package com.godmaster.visit.process.queue;

import java.util.LinkedList;
import java.util.Queue;
import com.godmaster.visit.process.Processor;

/**
 * This class used to build a queue for processor. I recommend that you create a
 * queue for each request,because every request will consume a queue.
 * 
 * @author sunbowen
 * 
 */
public class ProcessorQueueBuilder {

	private Queue<Processor<?, ?>> queue = null;

	protected ProcessorQueueBuilder() {
		this.queue = new LinkedList<>();
	}

	public static final ProcessorQueueBuilder create() {
		return new ProcessorQueueBuilder();
	}

	public void append(Processor<?, ?> processor) {
		this.queue.offer(processor);
	}

	public final Queue<Processor<?, ?>> build() {
		return this.queue;
	}
}
