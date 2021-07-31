@file:JvmName("Strings")

package com.jtschwartz.chorecore

import java.util.regex.Pattern
import kotlin.math.abs

/**
 *  Splits this char sequence to a list of strings around occurrences of the specified delimiter.
 *
 *  @author Jacob Schwartz
 *  @param delimiter String to split on
 */

operator fun String.div(delimiter: String): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence to a list of strings around occurrences of the specified delimiter.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Char to split on
 */

operator fun String.div(delimiter: Char): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence around matches of the given regular expression.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Regex to split on
 */

operator fun String.div(delimiter: Regex): List<String> = this.split(delimiter)

/**
 *  Splits this char sequence around matches of the given regular expression.
 *
 *  @author Jacob Schwartz
 *  @param delimiter Pattern to split on
 */

operator fun String.div(delimiter: Pattern): List<String> = this.split(delimiter)

/**
 * Drops the specified number of characters
 *
 *  @author Jacob Schwartz
 *  @param count Number of characters to be dropped (from the beginning if positive, else from the end
 */

operator fun String.minus(count: Int): String = (if (count > 0) ::drop else ::dropLast)(abs(count))

/**
 * Trims whitespace from the beginning and end of the String
 *
 *  @author Jacob Schwartz
 */

operator fun String.dec(): String = this.trim()
