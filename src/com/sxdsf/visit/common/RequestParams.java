package com.sxdsf.visit.common;

import java.io.File;
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
import org.apache.http.entity.FileEntity;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class RequestParams {
	private final Map<String, String> uriParams = new HashMap<>();
	private File file;
	private String contentType;

	private static final String DEFAULT_CONTENTTYPE = "application/octet-stream";

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

	public void put(File file, String contentType) {
		if (file != null && contentType != null && !contentType.isEmpty()) {
			this.clearContent();
			this.file = file;
			this.contentType = contentType;
		}
	}

	public void put(File file) {
		this.put(file, DEFAULT_CONTENTTYPE);
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

	public HttpEntity createEntity() {
		HttpEntity entity = null;
		if (!this.uriParams.isEmpty()) {
			List<NameValuePair> list = this.getParamsList();
			if (list != null && !list.isEmpty()) {
				try {
					entity = new UrlEncodedFormEntity(list, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					Log.e(TAG, e.getMessage());
				}
			}
		} else if (this.file != null) {
			entity = new FileEntity(this.file, this.contentType);
		}

		return entity;
	}

	private void clearContent() {
		this.uriParams.clear();
	}
}
