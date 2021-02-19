package com.jtschwartz.chorecore

import com.google.gson.Gson
import com.jtschwartz.chorecore.Nullability.isNull

/**
 *  Miscellaneous utilities to simplify code
 *
 *  @author Jacob Schwartz
 *  @since 1/19/20201
 */
object Utilities {
	private var verboseLogging = true
	
	fun toggleVerboseLogging() {
		verboseLogging = !verboseLogging
	}
	
	fun Any?.whileIs(vararg funcs: () -> Unit): Any? {
		val original = if (this.isNull()) null else clone(this!!)
		for (i  in funcs.indices) {
			val returnValue = funcs[i]()
			if (this != original) return returnValue
		}
		
		return null
	}
	
	inline fun <reified T:Any> clone(obj: T): T {
		return Gson().fromJson(Gson().toJson(obj, T::class.java), T::class.java)
	}
}
