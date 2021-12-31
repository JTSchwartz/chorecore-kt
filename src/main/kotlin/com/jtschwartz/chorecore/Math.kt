@file:JvmName("Math")

package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.ChoreCoreException
import kotlin.math.abs

/**
 *  Regex pattern for a decimal formatted float/double or integer.
 *
 *  @author Jacob Schwartz
 */

val STRING_DECIMAL_FORMAT = "\\d*(\\.\\d*)?".toRegex()

/**
 *  Regex pattern for a lone fraction, (ie 5/2).
 *
 *  @author Jacob Schwartz
 */

val STRING_FRACTION_FORMAT = "[1-9]\\d*/[1-9]\\d*".toRegex()

/**
 *  Regex pattern for a fraction with integer base, (ie 2 1/2).
 *
 *  @author Jacob Schwartz
 */

val STRING_INTEGER_AND_FRACTION_FORMAT = "[1-9]+ [1-9]\\d*/[1-9]\\d*".toRegex()

/**
 *  Finds the closest number in an array to the given needle.
 *
 *  @param needle Value by which to search for within the given array.
 *  @param array Array to search for the closest number in.
 *  @author Jacob Schwartz
 */

fun <T: Number> closest(needle: T, array: List<T>): T {
	needle.toDouble().let {
		return array.reduce { a, b ->
			if (abs(b.toDouble() - it) < (abs(a.toDouble() - it))) b else a
		}
	}
}

/**
 *  Intended for converting fraction strings to a numerical value, but will convert any value which
 *  matches one of three patterns: STRING_DECIMAL_FORMAT, STRING_FRACTION_FORMAT,
 *  STRING_INTEGER_AND_FRACTION_FORMAT.
 *
 *  @param fraction String value to be converted.
 *  @author Jacob Schwartz
 */

fun parseFractionString(fraction: String): Double {
	fraction.trim().let {
		var parsedFraction = it
		var negativity = 1
		if (parsedFraction.first() == '-') {
			negativity = -1
			parsedFraction -= 1
		}
		return (if (STRING_FRACTION_FORMAT.matches(parsedFraction)) {
			val (numerator, denominator) = parsedFraction / "/"
			numerator.toDouble() / denominator.toDouble()
		} else if (STRING_INTEGER_AND_FRACTION_FORMAT.matches(parsedFraction)) {
			val (base, numerator, denominator) = parsedFraction / "[ /]".toRegex()
			(numerator.toDouble() / denominator.toDouble()) + base.toInt()
		} else if (STRING_DECIMAL_FORMAT.matches(parsedFraction)) {
			parsedFraction.toDouble()
		} else {
			throw ChoreCoreException("Fraction must match one of three patterns: ${STRING_DECIMAL_FORMAT.pattern}, ${STRING_FRACTION_FORMAT.pattern}, or ${STRING_INTEGER_AND_FRACTION_FORMAT.pattern}")
		}) * negativity
	}
}

/**
 *  @see Time.plus
 */

operator fun Int.plus(timeUnit: Time): Long = this + timeUnit.inMilliseconds

/**
 *  @see Time.plus
 */

operator fun Long.plus(timeUnit: Time): Long = this + timeUnit.inMilliseconds

/**
 *  @see Time.plus
 */

operator fun Float.plus(timeUnit: Time): Float = this + timeUnit.inMilliseconds

/**
 *  @see Time.plus
 */

operator fun Double.plus(timeUnit: Time): Double = this + timeUnit.inMilliseconds

/**
 *  @see Time.minus
 */

operator fun Int.minus(timeUnit: Time): Long = this - timeUnit.inMilliseconds

/**
 *  @see Time.minus
 */

operator fun Long.minus(timeUnit: Time): Long = this - timeUnit.inMilliseconds

/**
 *  @see Time.minus
 */

operator fun Float.minus(timeUnit: Time): Float = this - timeUnit.inMilliseconds

/**
 *  @see Time.minus
 */

operator fun Double.minus(timeUnit: Time): Double = this - timeUnit.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Int.times(timeUnit: Time): Long = this * timeUnit.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Long.times(timeUnit: Time): Long = this * timeUnit.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Float.times(timeUnit: Time): Float = this * timeUnit.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Double.times(timeUnit: Time): Double = this * timeUnit.inMilliseconds

/**
 *  @see Time.div
 */

operator fun Int.div(timeUnit: Time): Long = this / timeUnit.inMilliseconds

/**
 *  @see Time.div
 */

operator fun Long.div(timeUnit: Time): Long = this / timeUnit.inMilliseconds

/**
 *  @see Time.div
 */

operator fun Float.div(timeUnit: Time): Float = this / timeUnit.inMilliseconds

/**
 *  @see Time.div
 */

operator fun Double.div(timeUnit: Time): Double = this / timeUnit.inMilliseconds

/**
 *  @see Time.rem
 */

operator fun Int.rem(timeUnit: Time): Long = this % timeUnit.inMilliseconds

/**
 *  @see Time.rem
 */

operator fun Long.rem(timeUnit: Time): Long = this % timeUnit.inMilliseconds

/**
 *  @see Time.rem
 */

operator fun Float.rem(timeUnit: Time): Float = this % timeUnit.inMilliseconds

/**
 *  @see Time.rem
 */

operator fun Double.rem(timeUnit: Time): Double = this % timeUnit.inMilliseconds

infix fun ClosedRange<Float>.step(step: Float): Iterable<Float> {
	require(start.isFinite())
	require(endInclusive.isFinite())
	val sequence = generateSequence(start) { previous ->
		if (previous == Float.POSITIVE_INFINITY) return@generateSequence null
		val next = previous + step
		if (next > endInclusive) null else next
	}
	return sequence.asIterable()
}

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
	require(start.isFinite())
	require(endInclusive.isFinite())
	val sequence = generateSequence(start) { previous ->
		if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
		val next = previous + step
		if (next > endInclusive) null else next
	}
	return sequence.asIterable()
}
