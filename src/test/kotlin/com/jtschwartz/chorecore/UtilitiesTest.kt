package com.jtschwartz.chorecore

import com.jtschwartz.chorecore.internal.ChoreCoreException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class UtilitiesTest {
	
	@Test
	fun cloneTest() {
		val actual = Test(0)
		var expected = actual
		assertEquals(expected, actual)
		expected = actual.clone()
		assertNotEquals(expected, actual)
	}
	
	@Test
	fun `Attempt catch all`() {
		assertDoesNotThrow {
			attempt {
				throw Exception()
			}
		}
	}
	
	@Test
	fun `Attempt ignore only specific exceptions`() {
		assertDoesNotThrow {
			attempt(ChoreCoreException::class) {
				throw ChoreCoreException("Test")
			}
		}
		
		assertThrows<ChoreCoreException> {
			attempt(Exception::class) {
				throw ChoreCoreException("Test")
			}
		}
	}
}

private class Test(var num: Int)


