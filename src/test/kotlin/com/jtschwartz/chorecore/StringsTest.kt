package com.jtschwartz.chorecore

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.regex.Pattern
import java.util.stream.Stream

internal class StringsTest {
	companion object {
		@JvmStatic
		val helloWorld = "Hello, World!"
		
		@JvmStatic
		fun minusOperatorTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0, helloWorld),
			Arguments.of(1, "ello, World!"),
			Arguments.of(4, "o, World!"),
			Arguments.of(-1, "Hello, World"),
			Arguments.of(-3, "Hello, Wor")
																)
	}
	
	@Test
	fun divOperatorStringTest() {
		val delimiter =", "
		val expected = helloWorld.split(delimiter)
		val actual = helloWorld / delimiter
		assertEquals(expected, actual)
	}
	
	@Test
	fun divOperatorCharTest() {
		val delimiter = 'o'
		val expected = helloWorld.split(delimiter)
		val actual = helloWorld / delimiter
		assertEquals(expected, actual)
	}
	
	@Test
	fun divOperatorRegexTest() {
		val delimiter = "[A-Z]".toRegex()
		val expected = helloWorld.split(delimiter)
		val actual = helloWorld / delimiter
		assertEquals(expected, actual)
	}
	
	@Test
	fun divOperatorPatternTest() {
		val delimiter = Pattern.compile("[A-Z]")
		val expected = helloWorld.split(delimiter)
		val actual = helloWorld / delimiter
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("minusOperatorTestArguments")
	fun minusOperatorTest(count: Int, expected: String) {
		val actual = helloWorld - count
		assertEquals(expected, actual)
	}
	
	@Test
	fun unaryMinusOperatorTest() {
		var actual = "         String            "
		val expected = "String"
		--actual
		assertEquals(expected, actual)
	}
}
