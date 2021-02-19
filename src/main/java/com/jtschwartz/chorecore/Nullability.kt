package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.Utilities.whileIs


object Nullability {
	fun Any?.notNull(): Boolean {
		return !this.isNull()
	}
	
	fun Any?.isNull(): Boolean {
		return this == null
	}
	
	fun Any?.ifNotNull(callback: Callback) {
		this?.let { callback.invoke() }
	}
	
	fun Any?.ifNull(callback: Callback) {
		this?.let { return }
		callback.invoke()
	}
	
	inline fun <reified T: Any> Any?.whileNull(vararg funcs: Lazy<T>): Any? {
		return this.whileIs( *funcs)
	}
}
