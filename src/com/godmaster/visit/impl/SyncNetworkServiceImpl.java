package com.godmaster.visit.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import android.util.Log;
import com.godmaster.visit.Protocol;
import com.godmaster.visit.ProtocolEnum;
import com.godmaster.visit.RequestParams;
import com.godmaster.visit.ProtocolGroup;
import com.godmaster.visit.SyncNetworkService;
import com.godmaster.visit.basic.BasicMethods;
import com.godmaster.visit.callback.exception.NoResponseException;
import com.godmaster.visit.process.Processor;

public class SyncNetworkServiceImpl implements SyncNetworkService {

	private static final ThreadLocal<ProtocolGroup> threadLocal = new ThreadLocal<>();

	private static final String TAG = "SyncNetworkServiceImpl";

	@Override
	public <T> T syncGet(String uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		T response = null;
		try {
			response = this.syncGet(new URI(uri), params, processor, time, tu);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public <T> T syncGet(String uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncGet(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		T response = null;
		if (uri != null && processor != null) {
			String scheme = uri.getScheme();
			ProtocolGroup sg = threadLocal.get();
			boolean ok = false;
			if (sg != null) {
				ProtocolEnum pe = sg.getProtocolEnum();
				if (pe != null) {
					if (pe.getScheme() != null && pe.getScheme().equals(scheme)) {
						Protocol protocol = sg.getProtocol();
						if (protocol != null) {
							HttpClient hc = protocol.getClient();
							if (hc != null) {
								ok = true;
							}
						}
					}
				}
			}
			if (!ok) {
				sg = ProtocolGroup.create(scheme);
				threadLocal.set(sg);
			}

			// 20151106 sunbowen 再从线程本地变量里拿出来
			sg = threadLocal.get();
			if (sg != null) {
				Protocol protocol = sg.getProtocol();
				if (protocol != null) {
					// 20151106 sunbowen 先不考虑等待时间time
					try {
						HttpEntity entity = BasicMethods.GET(sg.getProtocol()
								.getClient(), uri,
								params != null ? params.getParams() : null);
						response = processor.process(entity);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					} catch (NoResponseException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					}
				}
			}
		}
		return response;
	}

	@Override
	public <T> T syncGet(URI uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncGet(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncPost(String uri, RequestParams params,
			Processor processor, long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		T response = null;
		try {
			response = this.syncPost(new URI(uri), params, processor, time, tu);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
		}
		return response;
	}

	@Override
	public <T> T syncPost(String uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncPost(uri, params, processor, -1, null);
	}

	@Override
	public <T> T syncPost(URI uri, RequestParams params, Processor processor,
			long time, TimeUnit tu) {
		// TODO Auto-generated method stub
		T response = null;
		if (uri != null && processor != null) {
			String scheme = uri.getScheme();
			ProtocolGroup sg = threadLocal.get();
			boolean ok = false;
			if (sg != null) {
				ProtocolEnum pe = sg.getProtocolEnum();
				if (pe != null) {
					if (pe.getScheme() != null && pe.getScheme().equals(scheme)) {
						Protocol protocol = sg.getProtocol();
						if (protocol != null) {
							HttpClient hc = protocol.getClient();
							if (hc != null) {
								ok = true;
							}
						}
					}
				}
			}
			if (!ok) {
				sg = ProtocolGroup.create(scheme);
				threadLocal.set(sg);
			}

			// 20151106 sunbowen 再从线程本地变量里拿出来
			sg = threadLocal.get();
			if (sg != null) {
				Protocol protocol = sg.getProtocol();
				if (protocol != null) {
					// 20151106 sunbowen 先不考虑等待时间time
					try {
						// 20151106 sunbowen 暂时先不写拼凑post的数据
						HttpEntity entity = BasicMethods.POST(sg.getProtocol()
								.getClient(), uri, new StringEntity(""));
						response = processor.process(entity);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					} catch (NoResponseException e) {
						// TODO Auto-generated catch block
						Log.e(TAG, e.getMessage());
					}
				}
			}
		}
		return response;
	}

	@Override
	public <T> T syncPost(URI uri, RequestParams params, Processor processor) {
		// TODO Auto-generated method stub
		return this.syncPost(uri, params, processor, -1, null);
	}

}
