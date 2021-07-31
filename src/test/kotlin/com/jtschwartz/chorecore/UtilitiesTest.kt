package com.jtschwartz.chorecore

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UtilitiesTest {
	
	@Test
	fun cloneTest() {
		val actual = Test(0)
		var expected = actual
		assertEquals(expected, actual)
		expected = actual.clone()
		assertNotEquals(expected, actual)
	}
}

private class Test(var num: Int)
