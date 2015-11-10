package com.godmaster.visit.process.impl;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

import android.util.Log;

public class HttpEntity2InputStreamProcessor extends
		EntityProcessor<InputStream> {

	private static final String TAG = "HttpEntity2InputStreamProcessor";

	@Override
	public InputStream process(HttpEntity v) {
		// TODO Auto-generated method stub
		InputStream is = null;
		if (v != null) {
			try {
				is = v.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return is;
	}

}
