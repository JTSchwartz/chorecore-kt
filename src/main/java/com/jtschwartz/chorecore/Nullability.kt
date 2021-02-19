package com.jtschwartz.chorecore


object Nullability {
	fun Any?.notNull(): Boolean {
		return !this.isNull()
	}
	
	fun Any?.isNull(): Boolean {
		return this == null
	}
}
