package com.jtschwartz.chorecore

/**
 *  An enumeration of Time units and their corresponding value in milliseconds.
 *
 *  *Note: Months have been excluded from the list as there are too many variations.*
 *
 * @property inMilliseconds Number of milliseconds in one instance of the given time unit.
 * @author Jacob Schwartz
 */

enum class Time(val inMilliseconds: Long) {
	MILLISECONDS(1),
	SECOND(1000L * MILLISECONDS),
	MINUTE(60L * SECOND),
	HOUR(60L * MINUTE),
	DAY(24L * HOUR),
	WEEK(7L * DAY),
	YEAR(365L * DAY),
	LEAP_YEAR(366L * DAY);
}

/**
 *  Easy addition of the number by the Time's inMillisecond field.
 *
 *  @param additive Additive for each unit's inMillisecond field.
 *  @author Jacob Schwartz
 */

operator fun Time.plus(additive: Int): Long = additive + this.inMilliseconds

/**
 *  @see Time.plus
 */

operator fun Time.plus(additive: Long): Long = additive + this.inMilliseconds

/**
 *  @see Time.plus
 */

operator fun Time.plus(additive: Float): Long = (additive + this.inMilliseconds).toLong()

/**
 *  @see Time.plus
 */

operator fun Time.plus(additive: Double): Long = (additive + this.inMilliseconds).toLong()

/**
 *  Easy subtraction of the number by the Time's inMillisecond field.
 *
 *  @param subtractive subtractive for each unit's inMillisecond field.
 *  @author Jacob Schwartz
 */

operator fun Time.minus(subtractive: Int): Long = this.inMilliseconds - subtractive

/**
 *  @see Time.minus
 */

operator fun Time.minus(subtractive: Long): Long = this.inMilliseconds - subtractive

/**
 *  @see Time.minus
 */

operator fun Time.minus(subtractive: Float): Long = (this.inMilliseconds - subtractive).toLong()

/**
 *  @see Time.minus
 */

operator fun Time.minus(subtractive: Double): Long = (this.inMilliseconds - subtractive).toLong()

/**
 *  Easy multiplication of the number by the Time's inMillisecond field.
 *
 *  @param multiplier Multiplier for each unit's inMillisecond field.
 *  @author Jacob Schwartz
 */

operator fun Time.times(multiplier: Int): Long = multiplier * this.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Time.times(multiplier: Long): Long = multiplier * this.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Time.times(multiplier: Float): Long = (multiplier * this.inMilliseconds).toLong()

/**
 *  @see Time.times
 */

operator fun Time.times(multiplier: Double): Long = (multiplier * this.inMilliseconds).toLong()

/**
 *  Easy division of the number by the Time's inMillisecond field.
 *
 *  @param divisor divisor for each unit's inMillisecond field.
 *  @author Jacob Schwartz
 */

operator fun Time.div(divisor: Int): Long = this.inMilliseconds / divisor

/**
 *  @see Time.div
 */

operator fun Time.div(divisor: Long): Long = this.inMilliseconds / divisor

/**
 *  @see Time.div
 */

operator fun Time.div(divisor: Float): Long = (this.inMilliseconds / divisor).toLong()

/**
 *  @see Time.div
 */

operator fun Time.div(divisor: Double): Long = (this.inMilliseconds / divisor).toLong()

/**
 *  Easy division of the number by the Time's inMillisecond field.
 *
 *  @param modulus modulus for each unit's inMillisecond field.
 *  @author Jacob Schwartz
 */

operator fun Time.rem(modulus: Int): Long = this.inMilliseconds % modulus

/**
 *  @see Time.rem
 */

operator fun Time.rem(modulus: Long): Long = this.inMilliseconds % modulus

/**
 *  @see Time.rem
 */

operator fun Time.rem(modulus: Float): Long = (this.inMilliseconds % modulus).toLong()

/**
 *  @see Time.rem
 */

operator fun Time.rem(modulus: Double): Long = (this.inMilliseconds % modulus).toLong()

/**
 *  @see Time.times
 */

operator fun Time.invoke(multiplier: Int): Long = multiplier * this.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Time.invoke(multiplier: Long): Long = multiplier * this.inMilliseconds

/**
 *  @see Time.times
 */

operator fun Time.invoke(multiplier: Float): Long = (multiplier * this.inMilliseconds).toLong()

/**
 *  @see Time.times
 */

operator fun Time.invoke(multiplier: Double): Long = (multiplier * this.inMilliseconds).toLong()
