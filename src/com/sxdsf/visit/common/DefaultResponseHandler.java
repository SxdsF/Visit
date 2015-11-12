package com.sxdsf.visit.common;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class DefaultResponseHandler implements ResponseHandler<Response> {

	@Override
	public Response handleResponse(HttpResponse arg0)
			throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		return arg0 != null ? new Response(arg0.getStatusLine(),
				EntityUtils.toByteArray(arg0.getEntity())) : null;
	}

}
