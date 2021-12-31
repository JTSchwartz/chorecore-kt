package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.ChoreCoreException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class FractionTest {
	companion object {
		@JvmStatic
		fun closestTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0.5, Fraction.ONE_HALF),
			Arguments.of(1.0 / 3.0, Fraction.ONE_THIRD),
			Arguments.of(0.24, Fraction.ONE_QUARTER),
			Arguments.of(0.0, Fraction.ONE_TENTH),
			Arguments.of(0.3444, Fraction.ONE_THIRD)
			)
		
		@JvmStatic
		fun toClosestSymbolTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0.5, ".5", "1/2", Fraction.ONE_HALF.symbol),
			Arguments.of(-0.5, "-.5", "-1/2", "-${Fraction.ONE_HALF.symbol}"),
			Arguments.of(1.0, "1.0", "1", "1"),
			Arguments.of(-1.0, "-1.0", "-1", "-1"),
			Arguments.of(1.24, "1.24", "1 6/25", "1 ${Fraction.ONE_QUARTER.symbol}"),
			Arguments.of(1.24, "1.24", "31/25", "1 ${Fraction.ONE_QUARTER.symbol}"),
			Arguments.of(-1.24, "-1.24", "-1 6/25", "-1 ${Fraction.ONE_QUARTER.symbol}"))
	}
	
	@Test
	fun getOperatorTest() {
		assertEquals(Fraction.ONE_HALF, Fraction[0.5])
		assertEquals(Fraction.ONE_THIRD, Fraction[1.0/3.0])
		assertThrows<ChoreCoreException> {
			Fraction[0.24]
		}
	}
	
	@ParameterizedTest
	@MethodSource("closestTestArguments")
	fun closestTest(decimal: Double, expected: Fraction) {
		assertEquals(expected, Fraction.closest(decimal))
		assertEquals(expected, Fraction.closest(decimal.toFloat()))
		assertThrows<ChoreCoreException> {
			val outOfBounds = decimal + 1.1
			Fraction.closest(outOfBounds)
		}
		
	}
	
	@ParameterizedTest
	@MethodSource("toClosestSymbolTestArguments")
	fun toClosestSymbolTest(decimalNumber: Double, decimalString: String, fractionString: String, expected: String) {
		assertEquals(expected, Fraction.toClosestSymbol(decimalNumber))
		assertEquals(expected, Fraction.toClosestSymbol(decimalNumber.toFloat()))
		assertEquals(expected, Fraction.toClosestSymbol(decimalString))
		assertEquals(expected, Fraction.toClosestSymbol(fractionString))
	}
}
