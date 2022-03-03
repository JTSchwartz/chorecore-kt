package com.jtschwartz.chorecore.internal

import java.lang.Exception

class ChoreCoreException(override val message: String): Exception() {
	override fun toString(): String = message
}

class InternalException(): Exception()
