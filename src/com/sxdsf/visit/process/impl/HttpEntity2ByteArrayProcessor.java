package com.sxdsf.visit.process.impl;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import android.util.Log;

public class HttpEntity2ByteArrayProcessor extends HttpEntityProcessor<byte[]> {

	private static final String TAG = "HttpEntity2ByteArrayProcessor";

	@Override
	public byte[] process(HttpEntity v) {
		// TODO Auto-generated method stub
		byte[] result = null;
		if (v != null) {
			try {
				result = EntityUtils.toByteArray(v);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return result;
	}

}
