package com.sxdsf.visit.common;

import org.apache.http.StatusLine;

public class Response {
	private StatusLine statusLine;
	private byte[] entity;

	public Response(StatusLine statusLine, byte[] entity) {
		this.statusLine = statusLine;
		this.entity = entity;
	}

	public StatusLine getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	public byte[] getEntity() {
		return entity;
	}

	public void setEntity(byte[] entity) {
		this.entity = entity;
	}

}
