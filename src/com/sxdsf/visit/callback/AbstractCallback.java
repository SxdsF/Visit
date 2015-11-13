package com.sxdsf.visit.callback;

import java.lang.ref.SoftReference;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class AbstractCallback<T> implements AsyncCallback<T> {

	private final Handler handler;
	private final AtomicInteger currentStatus = new AtomicInteger(
			Status.INITIAL.getStatusCode());
	private final AtomicLong scheduled = new AtomicLong(-1);
	private final AtomicLong started = new AtomicLong(-1);
	private final AtomicLong ended = new AtomicLong(-1);

	public AbstractCallback() {
		// 20151110 sunbowen 默认的采用main looper来初始化handler，回调消息就可以在UI线程（主线程）执行。
		this.handler = new ResponseHandler<T>(this, Looper.getMainLooper());
	}

	public AbstractCallback(Looper looper) {
		// 20151110 sunbowen 如果传入looper，则用此looper初始化，回调消息就可以在用户指定的线程执行。
		this.handler = new ResponseHandler<T>(this, looper);
	}

	@Override
	public final void onScheduled(long scheduledTime) {
		// TODO Auto-generated method stub
		scheduled.set(scheduledTime);
	}

	@Override
	public final void onStarted(long startedTime) {
		// TODO Auto-generated method stub
		started.set(startedTime);
	}

	@Override
	public final void onEnded(long endedTime) {
		// TODO Auto-generated method stub
		ended.set(endedTime);
		// 20151109 sunbowen 在此发送完成的消息
		this.sendEvent(Event.COMPLETED, null);
	}

	@Override
	public final void completed(T result) {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送成功的消息
		if (this.currentStatus.compareAndSet(Status.INITIAL.getStatusCode(),
				Status.ISCOMPLETED.getStatusCode())) {
			this.<T> sendEvent(Event.SUCCESS, result);
		}
	}

	@Override
	public final void failed(Exception ex) {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送失败的消息
		if (this.currentStatus.compareAndSet(Status.INITIAL.getStatusCode(),
				Status.ISFAILED.getStatusCode())) {
			this.<Exception> sendEvent(Event.FAILURE, ex);
		}
	}

	@Override
	public final void cancelled() {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送取消的消息
		if (this.currentStatus.compareAndSet(Status.INITIAL.getStatusCode(),
				Status.ISCANCELLED.getStatusCode())) {
			this.sendEvent(Event.CANCELLED, null);
		}
	}

	private <V> void sendEvent(int code, V v) {
		Message msg = this.handler.obtainMessage();
		Event<V> event = new Event<>();
		event.messageCode = code;
		event.messageEntity = v;
		msg.obj = event;
		this.handler.sendMessage(msg);
	}

	@SuppressWarnings("unchecked")
	private void handleMessage(Message msg) {
		if (msg != null) {
			Event<?> event = (Event<?>) msg.obj;
			if (event != null) {
				switch (event.messageCode) {
				case Event.SUCCESS:
					this.onSuccess((T) event.messageEntity);
					break;
				case Event.FAILURE:
					this.onFailure((Exception) event.messageEntity);
					break;
				case Event.CANCELLED:
					this.onCancelled();
					break;
				case Event.COMPLETED:
					this.onFinish(this.ended.get() - this.scheduled.get(),
							this.ended.get() - this.started.get());
					break;
				}
			}
		}
	}

	private static class ResponseHandler<T> extends Handler {
		private SoftReference<AbstractCallback<T>> callbackReference;

		public ResponseHandler(AbstractCallback<T> callback, Looper looper) {
			super(looper);
			this.callbackReference = new SoftReference<AbstractCallback<T>>(
					callback);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (this.callbackReference != null) {
				AbstractCallback<T> callback = this.callbackReference.get();
				if (callback != null) {
					callback.handleMessage(msg);
				}
			}
		}
	}

	private enum Status {
		INITIAL(0), ISCOMPLETED(1 << 0), ISFAILED(1 << 1), ISCANCELLED(1 << 2);
		private int statusCode;

		private Status(int statusCode) {
			this.statusCode = statusCode;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

}
