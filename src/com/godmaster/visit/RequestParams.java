package com.godmaster.visit;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class RequestParams {
	private final Map<String, String> uriParams = new HashMap<>();

	private static final String TAG = "RequestParams";

	public RequestParams() {
	}

	public RequestParams(Map<String, String> params) {
		this.uriParams.putAll(params);
	}

	public void put(String key, String value) {
		if (key != null && value != null) {
			this.uriParams.put(key, value);
		}
	}

	public List<NameValuePair> getParamsList() {
		List<NameValuePair> list = null;
		if (!this.uriParams.isEmpty()) {
			Set<Entry<String, String>> set = this.uriParams.entrySet();
			if (set != null) {
				list = new ArrayList<>();
				for (Entry<String, String> entry : set) {
					if (entry != null) {
						list.add(new BasicNameValuePair(entry.getKey(), entry
								.getValue()));
					}
				}
			}
		}
		return list;
	}

	public HttpEntity createFormEntity() {
		HttpEntity entity = null;
		List<NameValuePair> list = this.getParamsList();
		if (list != null && !list.isEmpty()) {
			try {
				entity = new UrlEncodedFormEntity(list, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				Log.e(TAG, e.getMessage());
			}
		}
		return entity;
	}
}
