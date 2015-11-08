package com.godmaster.visit.process.impl;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import android.util.Log;

public class StringEntityProcessor extends EntityProcessor<String> {

	private static final String TAG = "StringEntityProcessor";

	@Override
	public String process(HttpEntity v) {
		// TODO Auto-generated method stub
		String result = null;
		if (v != null) {
			try {
				result = EntityUtils.toString(v, "UTF-8");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return result;
	}

}
