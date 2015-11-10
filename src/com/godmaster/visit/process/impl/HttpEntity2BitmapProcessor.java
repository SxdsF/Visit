package com.godmaster.visit.process.impl;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpEntity2BitmapProcessor extends EntityProcessor<Bitmap> {

	private static final String TAG = "HttpEntity2BitmapProcessor";

	@Override
	public Bitmap process(HttpEntity v) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		InputStream is = null;
		if (v != null) {
			try {
				is = v.getContent();
				bitmap = BitmapFactory.decodeStream(is);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					}
				}
			}
		}
		return bitmap;
	}

}
