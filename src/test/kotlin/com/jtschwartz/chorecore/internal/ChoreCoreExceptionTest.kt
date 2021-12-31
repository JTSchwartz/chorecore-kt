package com.jtschwartz.chorecore.internal

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ChoreCoreExceptionTest {
	@Test
	fun messageTest() {
		val expected = "Hello, World!"
		val choreCoreException = ChoreCoreException(expected)
		val actual = choreCoreException.toString()
		assertEquals(expected, actual)
	}
}
