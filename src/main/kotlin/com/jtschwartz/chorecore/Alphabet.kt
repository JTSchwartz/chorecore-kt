@file:JvmName("Alphabet")

package com.jtschwartz.chorecore

/**
 * List of the English alphabet in order, in a number of specified formats
 *
 * @property subset A subset of the English alphabet
 * @author Jacob Schwartz
 */

enum class Alphabet(val subset: List<Char>) {
	LOWERCASE(listOf('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')),
	UPPERCASE(LOWERCASE.subset.map { letter -> letter.uppercase().toCharArray().first() }),
	LOWERCASE_VOWELS(listOf('a','e','i','o','u','y')),
	UPPERCASE_VOWELS(LOWERCASE_VOWELS.subset.map { letter -> letter.uppercase().toCharArray().first() }),
	LOWERCASE_NON_VOWELS(listOf('b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z')),
	UPPERCASE_NON_VOWELS(LOWERCASE_NON_VOWELS.subset.map { letter -> letter.uppercase().toCharArray().first() });
	
	operator fun get(index: Int) = this.subset[index]
}


