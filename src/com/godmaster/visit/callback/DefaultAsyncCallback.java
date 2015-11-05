package com.godmaster.visit.callback;

import java.lang.ref.SoftReference;
import com.godmaster.visit.Event;
import com.godmaster.visit.callback.exception.NoProcessorException;
import com.godmaster.visit.process.Processor;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class DefaultAsyncCallback<T> implements AsyncCallback {

	private Handler handler;
	private Processor processor;

	private static final String TAG = "DefaultAsyncCallback";

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public DefaultAsyncCallback() {
		this(null);
	}

	public DefaultAsyncCallback(Processor processor) {
		this.processor = processor;
		this.handler = new ResponseHandler<T>(this, Looper.getMainLooper());
	}

	/**
	 * Regardless of success or failure,it will invoke this callback method.
	 */
	public void onCompleted() {
	}

	/**
	 * It will invoke this callback method in success.
	 * 
	 * @param <T>
	 * 
	 * @param response
	 */
	public abstract void onSuccess(T response);

	/**
	 * It will invoke this callback method when it falls.
	 * 
	 * @param t
	 */
	public abstract void onFailure(Throwable t);

	/**
	 * This method used to send event messages to this callback.
	 */
	@Override
	public void sendEvent(Event event) {
		// TODO Auto-generated method stub
		if (this.handler != null && event != null) {
			Message msg = Message.obtain();
			msg.what = event.messageCode;
			msg.obj = event;
			this.handler.sendMessage(msg);
		}
	}

	private void handleMessage(Message message) throws NoProcessorException {
		if (message != null) {
			switch (message.what) {
			case Event.COMPLETED:
				this.onCompleted();
				break;
			case Event.SUCCESS:
				Event success = (Event) message.obj;
				if (success != null) {
					if (this.processor != null) {
						T t = this.processor.<T> process(success.entity);
						this.onSuccess(success != null ? t : null);
					} else {
						throw new NoProcessorException();
					}
				}
				break;
			case Event.FAILURE:
				Event failure = (Event) message.obj;
				if (failure != null) {
					this.onFailure(failure != null ? failure.throwable : null);
				}
				break;
			default:
				Log.v(TAG, "No match");
			}
		}
	}

	private static class ResponseHandler<T> extends Handler {
		private SoftReference<DefaultAsyncCallback<T>> callbackReference;

		public ResponseHandler(DefaultAsyncCallback<T> callback, Looper looper) {
			super(looper);
			this.callbackReference = new SoftReference<DefaultAsyncCallback<T>>(
					callback);
		}

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (this.callbackReference != null) {
				DefaultAsyncCallback<T> callback = this.callbackReference.get();
				if (callback != null) {
					try {
						callback.handleMessage(msg);
					} catch (NoProcessorException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					}
				}
			}
		}
	}
}
