package com.jtschwartz.chorecore

import java.time.LocalDateTime

object Logging {
	private fun printToError(msg: String) {
		System.err.print(msg)
	}
	
	private fun log(func: (String) -> Unit, category: Char, label: String, msg: String) {
		func("${LocalDateTime.now()} | $category/$label: $msg\n")
	}
	
	fun printErr(msg: String) {
		printToError("Error: $msg")
	}
	
	fun printlnErr(msg: String) {
		printErr("$msg \n")
	}
	
	fun printWarn(msg: String) {
		printToError("Warn: $msg")
	}
	
	fun printlnWarn(msg: String) {
		printWarn("$msg \n")
	}
	
	fun e (label: String, msg: String) {
		log(::printToError,'E', label, msg)
	}
	
	fun w (label: String, msg: String) {
		log(::printToError,'W', label, msg)
	}
	
	fun d (label: String, msg: String) {
		log(::print,'D', label, msg)
	}
	
	fun i (label: String, msg: String) {
		log(::print,'I', label, msg)
	}
	
	fun category (category: Char, label: String, msg: String) {
		log(::print,category, label, msg)
	}
}
