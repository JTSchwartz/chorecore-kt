package com.jtschwartz.chorecore

/**
 *  String utilities to simplify code and aid readability
 *
 *  @author Jacob Schwartz
 *  @since 1/19/20201
 */
object Strings {
	operator fun String?.divAssign(index: Int) {
		this ?: return
		this.substring(0..index)
	}
}
