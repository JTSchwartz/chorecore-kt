package com.jtschwartz.chorecore

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.*

internal class NullabilityTest {
	companion object {
		@JvmStatic
		fun isNullTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(null, true),
			Arguments.of(0, false))
		
		@JvmStatic
		fun isNotNullTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(null, false),
			Arguments.of(0, true))
		
	}
	
	@ParameterizedTest
	@MethodSource("isNullTestArguments")
	fun isNullTest(variable: Any?, expected: Boolean) {
		val actual = variable.isNull()
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNotNullTestArguments")
	fun isNotNullTest(variable: Any?, expected: Boolean) {
		val actual = variable.isNotNull()
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNullTestArguments")
	fun ifNullTest(variable: Any?, expected: Boolean) {
		var actual = false
		
		variable.ifNull {
			actual = true
		}
		
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNotNullTestArguments")
	fun ifNotNullTest(variable: Any?, expected: Boolean) {
		var actual = false
		
		variable.ifNotNull {
			actual = true
		}
		
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNullTestArguments")
	fun ifNullTestWithReturn(variable: Any?, expected: Boolean) {
		var enteredBlock = false
		
		val returnValue = variable.ifNull {
			enteredBlock = true
			0
		}
		
		val actual = returnValue != variable
		assertEquals(expected, enteredBlock)
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNotNullTestArguments")
	fun ifNotNullTestWithReturn(variable: Any?, expected: Boolean) {
		var enteredBlock = false
		
		val returnValue = variable.ifNotNull {
			enteredBlock = true
			0
		}
		
		val actual = returnValue.isNotNull()
		assertEquals(expected, enteredBlock)
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNullTestArguments")
	fun ifNullTestWithReturnAndParameter(variable: Any?, expected: Boolean) {
		var enteredBlock = false
		
		val returnValue = variable.ifNull(1) {
			enteredBlock = true
			0
		}
		
		val actual = returnValue == 0
		assertEquals(expected, enteredBlock)
		assertEquals(expected, actual)
	}
	
	@ParameterizedTest
	@MethodSource("isNotNullTestArguments")
	fun ifNotNullTestWithReturnAndParameter(variable: Any?, expected: Boolean) {
		var enteredBlock = false
		
		val returnValue = variable.ifNotNull(1) {
			enteredBlock = true
			0
		}
		
		val actual = returnValue != 1
		assertEquals(expected, enteredBlock)
		assertEquals(expected, actual)
	}
	
	@Test
	fun `isNotNullOrEmpty on Strings`() {
		assertTrue("NotEmpty".isNotNullOrEmpty())
		assertTrue(" ".isNotNullOrEmpty())
		assertFalse("".isNotNullOrEmpty())
		assertFalse(null.isNotNullOrEmpty())
	}
	
	@Test
	fun `isNotNullOrEmpty on Collections`() {
		assertTrue(listOf(0).isNotNullOrEmpty())
		assertFalse(emptyList<Test>().isNotNullOrEmpty())
		assertFalse(null.isNotNullOrEmpty())
	}
	
	@Test
	fun isNotNullOrBlankTest() {
		assertTrue("NotEmpty".isNotNullOrBlank())
		assertFalse(" ".isNotNullOrBlank())
		assertFalse("".isNotNullOrBlank())
		assertFalse(null.isNotNullOrBlank())
	}
}
