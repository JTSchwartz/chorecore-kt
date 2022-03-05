@file:JvmName("Utilities")

package com.jtschwartz.chorecore

import com.google.gson.Gson
import kotlin.reflect.KClass

/**
 *  Deep clones an object.
 *
 *  @author Jacob Schwartz
 */

inline fun <reified T> T.clone(): T {
	return Gson().fromJson(Gson().toJson(this, T::class.java), T::class.java)
}

/**
 * A replacement for a try block with a catch statement that simply ignores what is thrown. Allows
 *
 * Can also act as a filter and continue to throw errors that are not specified as ignorable.
 *
 * @author Jacob Schwartz
 * @param exceptions A filter for exceptions that are expected and can be ignored, ignores all exceptions if left empty.
 * @param block The block of code you would instead place in a try block.
 */

fun attempt(vararg exceptions: KClass<out Exception>, block: () -> Unit) {
	try {
		block()
	} catch (e: Exception) {
		if (exceptions.isNotEmpty() && e::class !in exceptions) {
			throw e
		}
	}
}
