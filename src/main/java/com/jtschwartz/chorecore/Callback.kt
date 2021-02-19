package com.jtschwartz.chorecore

abstract class Callback {
	private var arguments: Unit? = null
	
	fun invoke() {
		callback(arguments)
	}
	
	fun build(args: Unit?) {
		this.arguments = args
	}
	
	abstract fun callback(unit: Unit?)
}
