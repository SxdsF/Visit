package com.godmaster.visit;

public class ProtocolFactory {
	public static final Protocol getInstance(ProtocolEnum pe) {
		Protocol protocol = null;
		if (pe != null) {
			switch (pe) {
			case HTTP:
				protocol = new HttpProtocol();
				break;
			case HTTPS:
				protocol = new HttpsProtocol();
				break;
			case NO_MATCH:
				protocol = new NoMatchProtocol();
			default:
				protocol = new NoMatchProtocol();
				break;
			}
		}
		return protocol;
	}

	public static final Protocol getInstance(String scheme) {
		Protocol protocol = null;
		if (scheme != null) {
			if (scheme.equals(ProtocolEnum.HTTP.getScheme())) {
				protocol = getInstance(ProtocolEnum.HTTP);
			} else if (scheme.equals(ProtocolEnum.HTTPS.getScheme())) {
				protocol = getInstance(ProtocolEnum.HTTPS);
			} else {
				protocol = getInstance(ProtocolEnum.NO_MATCH);
			}
		}
		return protocol;
	}
}
