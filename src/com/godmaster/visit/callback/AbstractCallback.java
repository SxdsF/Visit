package com.godmaster.visit.callback;

import java.lang.ref.SoftReference;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public abstract class AbstractCallback<T> implements AsyncCallback<T> {

	protected final Handler handler;

	private AbstractCallback() {
		this.handler = new ResponseHandler<T>(this, Looper.getMainLooper());
	}

	@Override
	public void completed(T result) {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送成功的消息
		this.<T> sendEvent(Event.SUCCESS, result);

		// 20151109 sunbowen 在此发送完成的消息
		this.sendEvent(Event.COMPLETED, null);

	}

	@Override
	public void failed(Exception ex) {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送失败的消息
		this.<Exception> sendEvent(Event.FAILURE, ex);

		// 20151109 sunbowen 在此发送完成的消息
		this.sendEvent(Event.COMPLETED, null);
	}

	@Override
	public void cancelled() {
		// TODO Auto-generated method stub
		// 20151109 sunbowen 在此发送取消的消息
		this.sendEvent(Event.CANCELLED, null);

		// 20151109 sunbowen 在此发送完成的消息
		this.sendEvent(Event.COMPLETED, null);
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
					this.onFinish();
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

}
