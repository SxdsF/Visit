package com.godmaster.visit.process;

public interface Processor {
	public <T, K> T process(K k);
}
