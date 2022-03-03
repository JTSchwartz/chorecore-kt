@file:JvmName("Nullability")

package com.jtschwartz.chorecore

/**
 *  Tests if the context object is null.
 *
 *  @author Jacob Schwartz
 */

fun <T> T.isNull(): Boolean {
	return this == null
}

/**
 *  Tests if the context object is not null.
 *
 *  @author Jacob Schwartz
 */

fun <T> T.isNotNull(): Boolean {
	return !this.isNull()
}

/**
 *  Run the provided code block inline if the context object is null.
 *
 *  @author Jacob Schwartz
 *  @param returnIfNotNull Value specified (defaulted to the context object if type allows, else null) to return if the context object is not null.
 *  @return Return value is the lambda result, unless the context object is not null, in which case `returnIfNotNull` is returned.
 */

inline fun <T, reified R> T?.ifNull(returnIfNotNull: R? = if (this is R) this else null, block: (T?) -> R): R? {
	return if (this.isNull()) block(this) else returnIfNotNull
}

/**
 *  Run the provided code block inline if the context object is not null.
 *
 *  @author Jacob Schwartz
 *  @param returnIfNull Value specified (defaulted to null) to return if the context object is null.
 *  @return Return value is the lambda result, unless the context object is null, in which case `returnIfNull` is returned.
 */

inline fun <T, R> T?.ifNotNull(returnIfNull: R? = null, block: (T) -> R): R? {
	return if (this.isNotNull()) block(this!!) else returnIfNull
}
