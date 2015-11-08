package com.godmaster.visit;

import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Future;

public interface NetworkService extends Closeable {

	public void cancel(Future<?> future);

	public void cancel(List<Future<?>> futureList);

	public void close();

	public boolean isclosed();
}
