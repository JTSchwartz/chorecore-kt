package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.ChoreCoreException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class MathTest {
	companion object {
		@JvmStatic
		fun closestTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(8, listOf(1, 7, 3, 5, 10), 7),
			Arguments.of(0.125, listOf(-1, 4, 7, 8), -1),
			Arguments.of(1, listOf(0, 0.5, 2), 0.5))
		
		@JvmStatic
		fun parseFractionStringTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0.5, listOf(".5", "1/2")),
			Arguments.of(-0.5, listOf("-.5", "-1/2")),
			Arguments.of(1.0, listOf("1.0", "1")),
			Arguments.of(-1.0, listOf("-1.0", "-1")),
			Arguments.of(1.24, listOf("1.24", "1 6/25", "31/25")),
			Arguments.of(-1.24, listOf("-1.24", "-1 6/25", "-31/25")))
	}
	
	@ParameterizedTest
	@MethodSource("closestTestArguments")
	fun <T: Number> closestTest(needle: T, arr: List<T>, expected: T) {
		val actual = closest(needle, arr)
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("parseFractionStringTestArguments")
	fun parseFractionStringTest(expected: Double, inputs: List<String>) {
		inputs.forEach {
			val actual = parseFractionString(it)
			assertEquals(expected, actual)
		}
	}
	
	@Test
	fun parseFractionStringTest_Exception() {
		assertThrows<ChoreCoreException> {
			parseFractionString("abc")
		}
	}
}
