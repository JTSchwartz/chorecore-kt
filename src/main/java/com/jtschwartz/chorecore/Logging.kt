package com.jtschwartz.chorecore

import java.time.LocalDateTime

/**
 *  Logging utilities to better log errors, debugging information, and more
 *
 *  @author Jacob Schwartz
 *  @since 1/19/20201
 */
object Logging {
	private fun printToError(msg: String) {
		System.err.print(msg)
	}
	
	private fun log(func: (String) -> Unit, category: Char, label: String, msg: String) {
		func("${LocalDateTime.now()} | ${getCallerClassName()} | $category/$label: $msg\n")
	}
	
	private fun getCallerClassName(): String? {
		val stElements = Thread.currentThread().stackTrace
		for (i in 1 until stElements.size) {
			val ste = stElements[i]
			if (ste.className != Utilities::class.java.name && ste.className.indexOf("java.lang.Thread") != 0) {
				return ste.className
			}
		}
		return null
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
