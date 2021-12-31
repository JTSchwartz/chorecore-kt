package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.ChoreCoreException
import kotlin.math.*

/**
 *  An enumeration of Unicode symbols for fractions.
 *
 *  @property symbol Unicode symbols for the corresponding fraction
 *  @property value Numerical value of the corresponding Unicode symbol
 *  @author Jacob Schwartz
 */

enum class Fraction(val symbol: String, val value: Double) {
	ONE_HALF("½", 1.0 / 2.0),
	ONE_THIRD("⅓", 1.0 / 3.0),
	ONE_QUARTER("¼", 1.0 / 4.0),
	ONE_FIFTH("⅕", 1.0 / 5.0),
	ONE_SIXTH("⅙", 1.0 / 6.0),
	ONE_SEVENTH("⅐", 1.0 / 7.0),
	ONE_EIGHTH("⅛", 1.0 / 8.0),
	ONE_NINTH("⅑", 1.0 / 9.0),
	ONE_TENTH("⅒", 1.0 / 10.0),
	TWO_THIRDS("⅔", 2.0 / 3.0),
	TWO_FIFTHS("⅖", 2.0 / 5.0),
	THREE_QUARTERS("¾", 3.0 / 4.0),
	THREE_FIFTHS("⅗", 3.0 / 5.0),
	THREE_EIGHTHS("⅜", 3.0 / 8.0),
	FOUR_FIFTHS("⅘", 4.0 / 5.0),
	FIVE_SIXTHS("⅚", 5.0 / 6.0),
	FIVE_EIGHTHS("⅝", 5.0 / 8.0),
	SEVEN_EIGHTHS("⅞", 7.0 / 8.0);
	
	companion object {
		
		/**
		 *  Gets the enum corresponding to the given value.
		 *
		 *  @param value Double that is meant to correspond to a Fraction.
		 *  @throws ChoreCoreException Thrown if a corresponding Fraction does not exist.
		 *
		 *  @author Jacob Schwartz
		 */
		
		operator fun get(value: Double): Fraction {
			val indexOfFraction = values().map { fraction -> fraction.value }.indexOf(value)
			if (indexOfFraction < 0) throw ChoreCoreException("Fraction does not exist for value $value")
			return values()[indexOfFraction]
		}
		
		/**
		 *  Returns the closest fraction to the provided Double.
		 *
		 *  @param fraction Double to be converted to the closest Fraction.
		 *  @throws ChoreCoreException Thrown if given value is not within 0 >= n >= 1.
		 *  @author Jacob Schwartz
		 */
		
		fun closest(fraction: Double): Fraction {
			if (fraction !in 0.0..1.0) {
				throw ChoreCoreException("Expected value to be between 0 and 1, received value: $this")
			}
			return Fraction[closest(fraction, values().map { it.value })]
		}
		
		/**
		 *  Returns the closest fraction to the provided Float.
		 *
		 *  @param fraction Float to be converted to the closest Fraction.
		 *  @throws ChoreCoreException Thrown if given value is not within 0 >= n >= 1.
		 *  @author Jacob Schwartz
		 */
		
		fun closest(fraction: Float): Fraction = closest(fraction.toDouble())
		
		/**
		 *  Converts the given Double to the closest String (symbol) form.
		 *
		 *  @param number Double to be converted to a String format.
		 *  @author Jacob Schwartz
		 */
		
		fun toClosestSymbol(number: Double): String {
			val isNegative = number < 0
			var base = floor(abs(number)).toInt()
			val fraction = abs(number) - base
			return if (fraction < 0.05) {
				if (isNegative) base *= -1
				base.toString()
			} else {
				val symbol = closest(fraction).symbol
				"${if (isNegative) "-" else ""}${if (base != 0) "$base " else ""}$symbol"
			}
		}
		
		/**
		 *  Converts the given Float to the closest String (symbol) form.
		 *
		 *  @param fraction Float to be converted to a String format.
		 *  @author Jacob Schwartz
		 */
		
		fun toClosestSymbol(fraction: Float): String  = toClosestSymbol(fraction.toDouble())
		
		/**
		 *  Accepts a number string in either decimal or numerator-denominator format and converts to the closest String (symbol) form.
		 *
		 *  @param fraction String to be reformatted as in a cleaner format.
		 *  @author Jacob Schwartz
		 */
		
		fun toClosestSymbol(fraction: String): String = toClosestSymbol(parseFractionString(fraction))
	}
}
