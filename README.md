# ChoreCore

![Maven Central](https://img.shields.io/maven-central/v/com.jtschwartz/chorecore)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/jtschwartz/chorecore-kt)
![GitHub Issues](https://img.shields.io/github/issues/jtschwartz/chorecore-kt)

For using this library in Java, it is recommended to read the documentation <a href="" target="_blank">here</a>. All capabilities still exist, but Java requires different method calls.

<a id="alphabet"></a>Alphabet
---
#### `Alphabet`
An enum of lists of the English alphabet in order, in a number of specified formats.

| Name                 | Subset                                                                                                                             |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------|
| LOWERCASE            | ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'] |
| UPPERCASE            | ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'] |
| LOWERCASE_VOWELS     | ['a', 'e', 'i', 'o', 'u', 'y']                                                                                                     | 
| UPPERCASE_VOWELS     | ['A', 'E', 'I', 'O', 'U', 'Y']                                                                                                     |
| LOWERCASE_NON_VOWELS | ['b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z']                               |
| UPPERCASE_NON_VOWELS | ['B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z']                               |

### Operator
#### `Alphabet[Int] -> Char`
The get operator has been overloaded on this enum so that indexes can be called directly from the enum rather than their `subset` property: `LOWERCASE[0]`

<a id="fraction"></a>Fraction
---
#### `Fraction`
An enumeration of Unicode symbols for fractions.

| Name           | Symbol | Value  |
|----------------|--------|--------|
| ONE_HALF       | ½      | 0.5    |
| ONE_THIRD      | ⅓      | 0.(3)  |
| ONE_QUARTER    | ¼      | 0.25   |
| ONE_FIFTH      | ⅕      | 0.2    |
| ONE_SIXTH      | ⅙      | 0.1(6) |
| ONE_SEVENTH    | ⅐      | 0.143  |
| ONE_EIGHTH     | ⅛      | 0.125  |
| ONE_NINTH      | ⅑      | 0.(1)  |
| ONE_TENTH      | ⅒      | 0.1    |
| TWO_THIRDS     | ⅔      | 0.6    |
| TWO_FIFTHS     | ⅖      | 0.4    |
| THREE_QUARTERS | ¾      | 0.75   |
| THREE_FIFTHS   | ⅗      | 0.6    |
| THREE_EIGHTHS  | ⅜      | 0.375  |
| FOUR_FIFTHS    | ⅘      | 0.8    |
| FIVE_SIXTHS    | ⅚      | 0.833  |
| FIVE_EIGHTHS   | ⅝      | 0.625  |
| SEVEN_EIGHTHS  | ⅞      | 0.875  |

### Operator
#### `Fraction[Int] -> Fraction`
This _static_ operator retrieves the `Fraction` whose `value` exactly matches the provided parameter, otherwise a `ChoreCoreException` is thrown if an exact match is not found.
<br/><br/>

There are also a number of static methods for retrieving and utilizing these enums:

#### `closest(value: Double | Float): Fraction`
This method operates very similarly to `get`, but will only throw an exception if the parameter is outside the range `0.0..1.0`. As long as the parameter is within the range, the `Fraction` with the closest `value` is returned.
<br/><br/>
#### `toClosestSymbol(number: Double | Float | String): String`
In order to directly utilize the `symbol` functionality of the enum, this method will accept any real Double or Float, or even a String that matches the syntax of a fraction or decimal number and return it in a cleaner format.

<a id="math"></a>Math
---
### Patterns
#### `STRING_DECIMAL_FORMAT` = `\\d*(\\.\\d*)?`
Decimal formatted float/double or integer: `2`
<br/><br/>
#### `STRING_FRACTION_FORMAT` = `[1-9]\\d*/[1-9]\\d*`
Lone fraction: `5/2`
<br/><br/>
#### `STRING_INTEGER_AND_FRACTION_FORMAT` = `[1-9]+ [1-9]\\d*/[1-9]\\d*`
Fraction with integer base: `2 1/2`

### Methods
#### `<T: Number> closest(needle: T, array: List<T>): T`
Finds the closest number in an array to the given needle.
<br/><br/>
#### `parseFractionString(fraction: String): Double`
Intended for converting fraction strings to a numerical value, but will convert any value which matches one of three patterns: `STRING_DECIMAL_FORMAT`, `STRING_FRACTION_FORMAT`,`STRING_INTEGER_AND_FRACTION_FORMAT`.
### Extensions
#### `ClosedRange<Double | Float>.step(step: Double | Float): Iterable<Double | Float>`
This extension method creates the expected infix `step` functionality for `Double` and `Float` ranges, most importantly so that they can be used in loops:
```kotlin
for (i in -2.0..2.0 step 0.5) {
	print("$i, ")
}
// -2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0,
```

Though they will not be individually documented for simplicity’s sake, this class also contains the following math operators: `+`, `-`, `*`, `/`, and `%` for `Int`, `Long`, `Float`, and `Double` to be able to perform on `Time` enumerations directly, rather than needing to call their `inMilliseconds` property. It should be noted that all values are returned in their respective return type's, except for `Int`, which will always return as a `Long` in these mathematics operations.

<a id="nullability"></a>Nullability
---
### Extensions
#### `<T>.isNull()`
A more idiomatic method for checking if a value is null.
<br/><br/>
#### `<T>.isNotNull()`
Negated version of `isNull()`
<br/><br/>
#### `<T,R> T.ifNull(returnIfNotNull: R?, block: (T) -> R): R?`
Again, on the basis of a more idiomatic method, `ifNull` is just an if block. It can be used as a normal block of code like so:

```kotlin
foo.ifNull {
	println("foo is null")
}
```

However, its return ability is slightly different. Obviously if a return value is being requested, there needs to be a value to return if `foo` is not null. Rather than an else block, this is an additional parameter you can pass, if there is no parameter given though, the method will default to `foo` (if it is of the proper type) or simply return null.
<br/><br/>
#### `<T,R> T.ifNotNull(returnIfNotNull: R?, block: (T) -> R): R?`
A negated version of `ifNull`.

<a id="strings"></a>Strings
---
### Operators
#### `String / (String | Char | Regex | Pattern) -> List<String>`
Wrapped `String.split` in an operator
<br/><br/>
#### `String - Int -> String`
If the Int is positive, this operation will drop the given number of characters from the beginning of the String. If the value is null, the count will be dropped from the end.
<br/><br/>
#### `-String -> String`
#### `--String`
#### `String--`
Both the unaryMinus and both Decrement operations perform the same action: trimming the String of whitespace on both ends end. The execution order of the Prefix and Postfix notations of the Decrement operations work the same as any other. 
<br/><br/>
### Extensions
#### `String.capitalize(): String`
Replaces the Kotlin's deprecated `String.capitalize()`
<br/><br/>
#### `String.sentenceCase(): String`
Split's a String on periods and convert's all characters to lowercase, and runs `String.capitalize()` on them individually before again joining with periods.
<br/><br/>
#### `String.replaceByRegexMap(map: Map<Regex, String>): String`
#### `String.replaceByMap(map: Map<String, String>): String`
Use a map to perform a replacement using each Key-Value pair, where the Key is what should be replaced by the Value. Both methods run the same, if the Key is a String, it just gets converted to Regex first. These could not be overloaded as parameterized types on interfaces do not mitigate signature clashes

<a id="time"></a>Time
---
An enumeration of Time units and their corresponding value in milliseconds.
_Note: Months have been excluded from the list as there are too many variations._

| Unit         | inMilliseconds (Long) |
|--------------|-----------------------|
| MILLISECONDS | 1                     |
| SECOND       | 1000 * MILLISECONDS   |
| MINUTE       | 60 * SECOND           |
| HOUR         | 60 * MINUTE           |
| DAY          | 24 * HOUR             |
| WEEK         | 7 * DAY               |
| YEAR         | 365 * DAY             |
| LEAP_YEAR    | 366 * DAY             |

### Operators
#### `Time (+ | - | * | / | %) (Int | Long | Float | Double) -> Long`
#### `(Int | Long | Float | Double) (+ | - | * | / | %) Time -> Long`
Provided operators so that the `inMilliseconds` property doesn't have to be called
<br/><br/>
#### `Time(Int | Long | Float | Double): Long`)`
Same as multiplication operator.

<a id="utilities"></a>Utility
---
### Extensions
#### `T.clone(): T`
Deep clone of any object.


