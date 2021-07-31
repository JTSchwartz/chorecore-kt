@file:JvmName("Utilities")

package com.jtschwartz.chorecore

import com.google.gson.Gson

/**
 *  Deep clones an object.
 *
 *  @author Jacob Schwartz
 */

inline fun <reified T> T.clone(): T {
	return Gson().fromJson(Gson().toJson(this, T::class.java), T::class.java)
}
