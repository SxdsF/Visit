package com.sxdsf.visit;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;

public class Response {
	private StatusLine statusLine;
	private HttpEntity entity;

	public Response(StatusLine statusLine, HttpEntity entity) {
		this.statusLine = statusLine;
		this.entity = entity;
	}

	public StatusLine getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	public HttpEntity getEntity() {
		return entity;
	}

	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}

}
