package com.sxdsf.visit;

import java.io.Closeable;

public interface NetworkService extends Closeable {

	/**
	 * This method used to close the service.
	 */
	public void close();

	/**
	 * This method will return the service is closed or not.
	 * 
	 * @return
	 */
	public boolean isClosed();
}
