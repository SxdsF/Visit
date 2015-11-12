package com.sxdsf.visit.process.impl;

import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class HttpEntity2JsonProcessor extends HttpEntityProcessor<JSONObject> {

	private static final String TAG = "HttpEntity2JsonProcessor";

	@Override
	public JSONObject process(HttpEntity v) {
		// TODO Auto-generated method stub
		JSONObject result = null;
		if (v != null) {
			try {
				result = new JSONObject(
						new HttpEntity2StringProcessor().process(v));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return result;
	}

}
