package com.godmaster.visit.callback.exception;

public class NoProcessorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8750173334323075586L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "There is no processor in the callback";
	}

}
