package com.jtschwartz.chorecore

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class AlphabetTest {
	companion object {
		@JvmStatic
		fun alphabetTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0, 'a'),
			Arguments.of(25, 'z'),
			Arguments.of(9, 'j'),
			Arguments.of(13, 'n'),
			Arguments.of(26, null))
		
		@JvmStatic
		fun vowelTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0, 'a'),
			Arguments.of(2, 'i'),
			Arguments.of(5, 'y'),
			Arguments.of(6, null))
		
		@JvmStatic
		fun nonVowelTestArguments(): Stream<Arguments> = Stream.of(
			Arguments.of(0, 'b'),
			Arguments.of(6, 'j'),
			Arguments.of(13, 'r'),
			Arguments.of(19, 'z'),
			Arguments.of(20, null))
	}
	
	@ParameterizedTest
	@MethodSource("alphabetTestArguments")
	fun lowercaseTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.LOWERCASE[index]
			assertEquals(excepted, actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.LOWERCASE[index]
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("alphabetTestArguments")
	fun uppercaseTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.UPPERCASE[index]
			assertEquals(excepted!!.uppercase().toCharArray().first(), actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.UPPERCASE[index]
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("vowelTestArguments")
	fun lowercaseVowelTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.LOWERCASE_VOWELS[index]
			assertEquals(excepted, actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.LOWERCASE_VOWELS[index]
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("vowelTestArguments")
	fun uppercaseVowelTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.UPPERCASE_VOWELS[index]
			assertEquals(excepted!!.uppercase().toCharArray().first(), actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.UPPERCASE_VOWELS[index]
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("nonVowelTestArguments")
	fun lowercaseNonVowelTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.LOWERCASE_NON_VOWELS[index]
			assertEquals(excepted, actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.LOWERCASE_NON_VOWELS[index]
			}
		}
	}
	
	@ParameterizedTest
	@MethodSource("nonVowelTestArguments")
	fun uppercaseNonVowelTest(index: Int, excepted: Char?) {
		excepted.ifNotNull {
			val actual = Alphabet.UPPERCASE_NON_VOWELS[index]
			assertEquals(excepted!!.uppercase().toCharArray().first(), actual)
		}
		excepted.ifNull {
			assertThrows<IndexOutOfBoundsException> {
				Alphabet.UPPERCASE_NON_VOWELS[index]
			}
		}
	}
}
