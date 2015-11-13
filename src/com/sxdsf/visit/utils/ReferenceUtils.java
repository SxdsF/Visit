package com.sxdsf.visit.utils;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceUtils {
	public static final <T> WeakReference<T> toWeakReference(T t) {
		WeakReference<T> reference = new WeakReference<T>(t);
		t = null;
		return reference;
	}

	public static final <T> SoftReference<T> toSoftReference(T t) {
		SoftReference<T> reference = new SoftReference<T>(t);
		t = null;
		return reference;
	}
}
