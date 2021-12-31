package com.jtschwartz.chorecore

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.ArithmeticException
import kotlin.math.abs

internal class TimeTest {
	@Test
	fun inMillisecondsProperty() {
		assertEquals(1, Time.MILLISECONDS.inMilliseconds)
		assertEquals(1e+3.toLong(), Time.SECOND.inMilliseconds)
		assertEquals(6e+4.toLong(), Time.MINUTE.inMilliseconds)
		assertEquals(3.6e+6.toLong(), Time.HOUR.inMilliseconds)
		assertEquals(8.64e+7.toLong(), Time.DAY.inMilliseconds)
		assertEquals(6.048e+8.toLong(), Time.WEEK.inMilliseconds)
		assertEquals(3.1536e+10.toLong(), Time.YEAR.inMilliseconds)
		assertEquals(3.16224e+10.toLong(), Time.LEAP_YEAR.inMilliseconds)
	}
	
	@Test
	fun timeIntOperatorTest() {
		for (i in -15..15) {
			Time.values().forEach { timeUnit ->
				assertEquals(timeUnit.inMilliseconds + i, timeUnit + i)
				assertEquals(timeUnit.inMilliseconds - i, timeUnit - i)
				assertEquals(timeUnit.inMilliseconds * i, timeUnit * i)
				assertEquals(timeUnit.inMilliseconds * i, timeUnit(i))
				
				assertEquals(i + timeUnit.inMilliseconds, i + timeUnit)
				assertEquals(i - timeUnit.inMilliseconds, i - timeUnit)
				assertEquals(i * timeUnit.inMilliseconds, i * timeUnit)
				assertEquals(i / timeUnit.inMilliseconds, i / timeUnit)
				assertEquals(i % timeUnit.inMilliseconds, i % timeUnit)
				
				if (i == 0) {
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds / 0, timeUnit / 0)
					}
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds % 0, timeUnit % 0)
					}
				} else {
					assertEquals(timeUnit.inMilliseconds / i, timeUnit / i)
					assertEquals(timeUnit.inMilliseconds % i, timeUnit % i)
				}
			}
		}
	}
	
	@Test
	fun timeLongOperatorTest() {
		for (i in -15L..15L) {
			Time.values().forEach { timeUnit ->
				assertEquals(timeUnit.inMilliseconds + i, timeUnit + i)
				assertEquals(timeUnit.inMilliseconds - i, timeUnit - i)
				assertEquals(timeUnit.inMilliseconds * i, timeUnit * i)
				assertEquals(timeUnit.inMilliseconds * i, timeUnit(i))
				
				assertEquals(i + timeUnit.inMilliseconds, i + timeUnit)
				assertEquals(i - timeUnit.inMilliseconds, i - timeUnit)
				assertEquals(i * timeUnit.inMilliseconds, i * timeUnit)
				assertEquals(i / timeUnit.inMilliseconds, i / timeUnit)
				assertEquals(i % timeUnit.inMilliseconds, i % timeUnit)
				
				if (i == 0L) {
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds / 0, timeUnit / 0)
					}
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds % 0, timeUnit % 0)
					}
				} else {
					assertEquals(timeUnit.inMilliseconds / i, timeUnit / i)
					assertEquals(timeUnit.inMilliseconds % i, timeUnit % i)
				}
			}
		}
	}
	
	@Test
	fun timeFloatOperatorTest() {
		for (i in -15F..15F step 0.5F) {
			Time.values().forEach { timeUnit ->
				if (abs(i % 1.0F) == 0.0F) {
					assertEquals(timeUnit.inMilliseconds + i, (timeUnit + i).toFloat())
					assertEquals(timeUnit.inMilliseconds - i, (timeUnit - i).toFloat())
					assertEquals(timeUnit.inMilliseconds * i, (timeUnit * i).toFloat())
					assertEquals(timeUnit.inMilliseconds * i, timeUnit(i).toFloat())
				}
				
				assertEquals(i + timeUnit.inMilliseconds, i + timeUnit)
				assertEquals(i - timeUnit.inMilliseconds, i - timeUnit)
				assertEquals(i * timeUnit.inMilliseconds, i * timeUnit)
				assertEquals(i / timeUnit.inMilliseconds, i / timeUnit)
				assertEquals(i % timeUnit.inMilliseconds, i % timeUnit)
				
				if (i == 0F) {
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds / 0, timeUnit / 0)
					}
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds % 0, timeUnit % 0)
					}
				} else {
					assertEquals((timeUnit.inMilliseconds / i).toDouble(), (timeUnit / i).toDouble(), 1.0)
					if (abs(i % 1.0F) == 0.0F) {
						assertEquals(timeUnit.inMilliseconds % i, (timeUnit % i).toFloat())
					}
				}
			}
		}
	}
	
	@Test
	fun timeDoubleOperatorTest() {
		for (i in -15.0..15.0 step 0.5) {
			Time.values().forEach { timeUnit ->
				if (abs(i % 1.0) == 0.0) {
					assertEquals(timeUnit.inMilliseconds + i, (timeUnit + i).toDouble())
					assertEquals(timeUnit.inMilliseconds - i, (timeUnit - i).toDouble())
					assertEquals(timeUnit.inMilliseconds * i, (timeUnit * i).toDouble())
					assertEquals(timeUnit.inMilliseconds * i, timeUnit(i).toDouble())
				}
				
				assertEquals(i + timeUnit.inMilliseconds, i + timeUnit)
				assertEquals(i - timeUnit.inMilliseconds, i - timeUnit)
				assertEquals(i * timeUnit.inMilliseconds, i * timeUnit)
				assertEquals(i / timeUnit.inMilliseconds, i / timeUnit)
				assertEquals(i % timeUnit.inMilliseconds, i % timeUnit)
				
				if (i == 0.0) {
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds / 0, timeUnit / 0)
					}
					assertThrows<ArithmeticException> {
						assertEquals(timeUnit.inMilliseconds % 0, timeUnit % 0)
					}
				} else {
					assertEquals(timeUnit.inMilliseconds / i, (timeUnit / i).toDouble(), 1.0)
					if (abs(i % 1.0) == 0.0) {
						assertEquals(timeUnit.inMilliseconds % i, (timeUnit % i).toDouble())
					}
				}
			}
		}
	}
}
