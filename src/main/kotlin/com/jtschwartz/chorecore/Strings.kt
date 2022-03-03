@file:JvmName("Strings")

package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.InternalException
import java.lang.reflect.InvocationTargetException
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.reflect.full.memberProperties

/**
 *  Splits this char sequence to a list of strings around occurrences of the specified delimiter.
 *
 *  @author Jacob Schwartz
 *  @param delimiter String to split on.
 */

operator fun String.div(delimiter: String): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence to a list of strings around occurrences of the specified delimiter.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Char to split on.
 */

operator fun String.div(delimiter: Char): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence around matches of the given regular expression.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Regex to split on.
 */

operator fun String.div(delimiter: Regex): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence around matches of the given regular expression.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Pattern to split on.
 */

operator fun String.div(delimiter: Pattern): List<String> = this.split(delimiter)

/**
 * Drops the specified number of characters
 *
 *  @author Jacob Schwartz
 *  @param count Number of characters to be dropped from the beginning if positive, else from the end.
 */

operator fun String.minus(count: Int): String = (if (count > 0) ::drop else ::dropLast)(abs(count))

/**
 *  Trims whitespace from the beginning and end of the String.
 *
 *  @author Jacob Schwartz
 */

operator fun String.unaryMinus(): String = this.trim()

/**
 *  Trims whitespace from the beginning and end of the String.
 *
 *  @author Jacob Schwartz
 */

operator fun String.dec(): String = this.trim()

/**
 *  Replaces the Kotlin deprecated capitalize method to capitalize the first letter of a String.
 *
 *  @author Jacob Schwartz
 */

fun String.capitalize(): String = this.replaceFirstChar { c -> c.uppercase() }

/**
 *  Splits a String on periods (i.e. into individual sentences) and makes each sentence entirely lowercase except for the first character of the first word.
 *
 *  @author Jacob Schwartz
 */

fun String.sentenceCase(): String = this.lowercase().split("\\.\\s+".toRegex()).joinToString(separator = ". ") { it.capitalize() }.trim()

/**
 *  Uses a map to perform a replacement on each Key-Value pair where the Key is the Regex for the substrings which is to be replaced by the Value.
 *
 *  @param map Key<Regex> Value<String> pair by which to replace the Key with the Value
 *  @author Jacob Schwartz
 */

fun String.replaceByRegexMap(map: Map<Regex, String>): String {
	return map.entries.fold(this) { acc, entry ->
		acc.replace(entry.key, entry.value)
	}
}

/**
 *  Uses a map to perform a replacement on each Key-Value pair where the Key is the substring that should be replaced by the Value.
 *
 *  @param map Key<String> Value<String> pair by which to replace the Key with the Value
 *  @author Jacob Schwartz
 */

fun String.replaceByMap(map: Map<String, String>): String = this.replaceByRegexMap(map.mapKeys { it.key.toRegex() })

inline fun <reified T: Any> T.generateString(
	exclude: List<String> = emptyList(),
	excludeNull: Boolean = false,
	excludeUninitialized: Boolean = false,
	excludeNullAndUninitialized: Boolean = false,
	uninitializedValue: String = "UNINITIALIZED",
	displayClassName: Boolean = true,
	openingBracket: String = "(",
	closingBracket: String = ")",
	separator: String = ", ",
	assignment: String = "=",
	crossinline propertyNameHandler: (String) -> String = { it },
	crossinline propertyValueHandler: (Any?) -> String = { it?.toString() ?: "null" }
): String {
	return T::class.memberProperties.asSequence().filter { it.name !in exclude }
		.map { property ->
			val prefix = "${propertyNameHandler(property.name)}$assignment"

			try {
				val value = property.getter.call(this)
				if (value.isNull() && (excludeNull || excludeNullAndUninitialized)) throw InternalException()
				"$prefix${propertyValueHandler(value)}"
			} catch (_: InvocationTargetException) {
				if (excludeUninitialized || excludeNullAndUninitialized) throw InternalException()
				"$prefix$uninitializedValue"
			} catch (_: InternalException) {
				null
			}
		}.filter { !it.isNullOrBlank() }
		.joinToString(separator, "${if (displayClassName) T::class.simpleName else ""}$openingBracket", closingBracket)
}

data class StringGenerationSettings(
	var exclude: List<String> = emptyList(),
	var excludeNull: Boolean = false,
	var excludeUninitialized: Boolean = false,
	var excludeNullAndUninitialized: Boolean = false,
	var uninitializedValue: String = "UNINITIALIZED",
	var displayClassName: Boolean = true,
	var openingBracket: String = "(",
	var closingBracket: String = ")",
	var separator: String = ", ",
	var assignment: String = "=",
	var propertyNameHandler: (String) -> String = { it },
	var propertyValueHandler: (Any?) -> String = { it?.toString() ?: "null" }
)

inline fun <reified T: Any> T.generateString(settings: StringGenerationSettings): String = generateString(
		exclude = settings.exclude,
		excludeNull = settings.excludeNull,
		excludeUninitialized = settings.excludeUninitialized,
		excludeNullAndUninitialized = settings.excludeNullAndUninitialized,
		uninitializedValue = settings.uninitializedValue,
		displayClassName = settings.displayClassName,
		openingBracket = settings.openingBracket,
		closingBracket = settings.closingBracket,
		separator = settings.separator,
		assignment = settings.assignment,
		propertyNameHandler = settings.propertyNameHandler,
		propertyValueHandler = settings.propertyValueHandler
)
