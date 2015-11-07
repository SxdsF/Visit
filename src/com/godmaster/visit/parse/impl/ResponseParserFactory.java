package com.godmaster.visit.parse.impl;

import org.apache.http.HttpStatus;
import com.godmaster.visit.parse.ResponseEnum;

public class ResponseParserFactory {
	static final ResponseParser create(int statusCode) {
		ResponseParser parser = null;
		if (0 < statusCode && statusCode < HttpStatus.SC_OK) {
			// 20151107 sunbowen 在这里返回1xx
			parser = create(ResponseEnum.INFORMATIONAL);
		} else if (HttpStatus.SC_OK <= statusCode
				&& statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
			// 20151107 sunbowen 在这里返回2xx
			parser = create(ResponseEnum.SUCCESS);
		} else if (HttpStatus.SC_MULTIPLE_CHOICES <= statusCode
				&& statusCode < HttpStatus.SC_BAD_REQUEST) {
			// 20151107 sunbowen 在这里返回3xx
			parser = create(ResponseEnum.REDIRECTION);
		} else if (HttpStatus.SC_BAD_REQUEST <= statusCode
				&& statusCode < HttpStatus.SC_INTERNAL_SERVER_ERROR) {
			// 20151107 sunbowen 在这里返回4xx
			parser = create(ResponseEnum.CLIENT_ERROR);
		} else if (HttpStatus.SC_INTERNAL_SERVER_ERROR <= statusCode) {
			// 20151107 sunbowen 在这里返回5xx
			parser = create(ResponseEnum.SERVER_ERROR);
		} else {
			// 20151107 sunbowen 在这里返回0
			parser = create(ResponseEnum.NO_RESPONSE);
		}
		return parser;
	}

	static final ResponseParser create(ResponseEnum responseEnum) {
		ResponseParser parser = null;
		if (responseEnum != null) {
			switch (responseEnum) {
			case CLIENT_ERROR:
				parser = new ResponseParser4ClientError();
				break;
			case INFORMATIONAL:
				parser = new ResponseParser4Informational();
				break;
			case NO_RESPONSE:
				parser = new ResponseParser4NoResponse();
				break;
			case REDIRECTION:
				parser = new ResponseParser4Redirection();
				break;
			case SERVER_ERROR:
				parser = new ResponseParser4ServerError();
				break;
			case SUCCESS:
				parser = new ResponseParser4Success();
				break;
			default:
				parser = new ResponseParser4NoResponse();
				break;
			}
		}

		return parser;
	}
}
