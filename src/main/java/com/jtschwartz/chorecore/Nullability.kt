package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.Utilities.whileIs

/**
 *  Utilities regarding Nullability And Null-Safety to simplify code
 *
 *  @author Jacob Schwartz
 *  @since 1/19/20201
 */
object Nullability {
	fun Any?.notNull(): Boolean {
		return !this.isNull()
	}
	
	fun Any?.isNull(): Boolean {
		return this == null
	}
	
	fun Any?.ifNotNull(callback: () -> Unit) {
		this?.let { callback() }
	}
	
	fun Any?.ifNull(callback: () -> Unit) {
		this?.let { return }
		callback()
	}
	
//	inline fun <reified T: Any> Any?.whileNull(vararg funcs: () -> Unit): Any? {
//		return this.whileIs( *funcs)
//	}
}
