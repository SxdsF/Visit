package com.godmaster.visit.parse.impl;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import android.util.Log;

public class HttpResponseParser4NoResponse extends HttpResponseParser<Void> {

	private static final String TAG = "HttpResponseParser4NoResponse";

	@Override
	public Void parse(HttpResponse response) {
		// TODO Auto-generated method stub
		if (response != null) {
			StatusLine status = response.getStatusLine();
			if (status != null) {
				Log.e(TAG,
						status.getStatusCode() + " " + status.getReasonPhrase());
			}
		}
		return null;
	}

}
