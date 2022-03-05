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
 *  @param block Block of code to execute if the context object is null.
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
 *  @param block Block of code to execute if the context object is not null.
 *  @return Return value is the lambda result, unless the context object is null, in which case `returnIfNull` is returned.
 */

inline fun <T, R> T?.ifNotNull(returnIfNull: R? = null, block: (T) -> R): R? {
	return if (this.isNotNull()) block(this!!) else returnIfNull
}

/**
 * Combines the standard library's `isNotNull()` and `isNotEmpty()
 *
 * @author Jacob Schwartz
 * @return Only returns true if the String is not null and not empty.`
 */

fun CharSequence?.isNotNullOrEmpty() = isNotNull() && this!!.isNotEmpty()

/**
 * Combines the standard library's `isNotNull()` and `isNotBlank()
 *
 * @author Jacob Schwartz
 * @return Only returns true if the String is not null and not empty/exclusively whitespace.`
 */

fun CharSequence?.isNotNullOrBlank() = isNotNull() && this!!.isNotBlank()

/**
 * Combines the standard library's `isNotNull()` and `isNotEmpty()
 *
 * @author Jacob Schwartz
 * @return Only returns true if the Collection is not null and not empty.`
 */

fun <T> Collection<T>?.isNotNullOrEmpty() = isNotNull() && this!!.isNotEmpty()
