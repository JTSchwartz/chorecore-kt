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
		
		@JvmStatic
		fun capitalizeTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of("hello world", "Hello world"),
			Arguments.of("hello World", "Hello World"),
			Arguments.of("HELLO WORLD", "HELLO WORLD")
																	)
		
		@JvmStatic
		fun sentenceCaseTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of("hello World", "Hello world"),
			Arguments.of("hello. world", "Hello. World"),
			Arguments.of("hello. world.", "Hello. World."),
			Arguments.of("HELLO. World", "Hello. World")
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
		val actual = "         String            "
		val expected = "String"
		assertEquals(expected, -actual)
	}
	
	@Test
	fun decOperatorTest() {
		var actual = "         String            "
		val expected = "String"
		--actual
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("capitalizeTestArguments")
	fun capitalizeTest(input: String, expected: String) {
		val actual = input.capitalize()
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("sentenceCaseTestArguments")
	fun sentenceCaseTest(input: String, expected: String) {
		val actual = input.sentenceCase()
		assertEquals(expected, actual)
	}
	
	@Test
	fun replaceByRegexMapTest() {
		val map = mapOf(
			"[eo]".toRegex() to "a",
			"A".toRegex() to "z",
			"\\s+".toRegex() to "_",
			"[,!]".toRegex() to ""
					   )
		val actual = helloWorld.replaceByRegexMap(map)
		val expected = "Halla_Warld"
		assertEquals(expected, actual)
	}
	
	@Test
	fun replaceByMapTest() {
		val map = mapOf(
			"e" to "a",
			"o" to "a",
			"A" to "z",
			" " to "_",
			"," to "",
			"!" to ""
		               )
		val actual = helloWorld.replaceByMap(map)
		val expected = "Halla_Warld"
		assertEquals(expected, actual)
	}
}
