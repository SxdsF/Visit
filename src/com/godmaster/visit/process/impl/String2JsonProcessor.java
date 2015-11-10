package com.godmaster.visit.process.impl;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;
import com.godmaster.visit.process.Processor;

public class String2JsonProcessor implements Processor<JSONObject, String> {

	private static final String TAG = "String2JsonProcessor";

	@Override
	public JSONObject process(String v) {
		// TODO Auto-generated method stub
		JSONObject json = null;
		try {
			json = new JSONObject(v);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
		}
		return json;
	}

}
