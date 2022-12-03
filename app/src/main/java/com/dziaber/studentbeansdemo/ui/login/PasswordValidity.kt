package com.dziaber.studentbeansdemo.ui.login

import java.util.*
import java.util.regex.Pattern

/**
 * Enum class of possible issues with the password
 */
enum class PasswordErrors {
    TOO_SHORT,
    NO_SPECIAL_CHAR,
    NO_UPPERCASE,
    NO_LOWERCASE,
    NO_NUMBER;
}

/**
 * Patterns used for checking different password requirements
 */
private val SPECIAL_CHARACTER_PATTERN: Pattern = Pattern.compile("[^a-zA-Z0-9]")
private val NUMBER_PATTERN: Pattern = Pattern.compile("[0-9]")
private val LOWERCASE_PATTERN: Pattern = Pattern.compile("[a-z]")
private val UPPERCASE_PATTERN: Pattern = Pattern.compile("[A-Z]")

/**
 * Returns an EnumSet of all current issues with [password],
 * or an empty EnumSet if there are none
 */
fun checkValidity(password: String): EnumSet<PasswordErrors> {
    val enumSet = EnumSet.noneOf(PasswordErrors::class.java)
    if (password.length < 7)
        enumSet.add(PasswordErrors.TOO_SHORT)

    if (!SPECIAL_CHARACTER_PATTERN.matcher(password).find())
        enumSet.add(PasswordErrors.NO_SPECIAL_CHAR)

    if (!NUMBER_PATTERN.matcher(password).find())
        enumSet.add(PasswordErrors.NO_NUMBER)

    if (!UPPERCASE_PATTERN.matcher(password).find())
        enumSet.add(PasswordErrors.NO_UPPERCASE)

    if (!LOWERCASE_PATTERN.matcher(password).find())
        enumSet.add(PasswordErrors.NO_LOWERCASE)

    return enumSet
}