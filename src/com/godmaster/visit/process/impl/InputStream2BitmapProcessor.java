package com.godmaster.visit.process.impl;

import java.io.IOException;
import java.io.InputStream;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.godmaster.visit.process.Processor;

public class InputStream2BitmapProcessor implements
		Processor<Bitmap, InputStream> {

	private static final String TAG = "InputStream2BitmapProcessor";

	@Override
	public Bitmap process(InputStream v) {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(v);
		} finally {
			if (v != null) {
				try {
					v.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, e.getMessage());
				}
			}
		}
		return bitmap;
	}

}
