package com.dziaber.studentbeansdemo
import org.junit.Test
import com.dziaber.studentbeansdemo.ui.login.PasswordErrors
import com.dziaber.studentbeansdemo.ui.login.checkValidity
import org.junit.Assert.*

class PasswordValidityTest {
    @Test
    fun passwordTooShort() {
        val password = "1234"
        val errorsEnumSet = checkValidity(password)
        assertTrue(errorsEnumSet.contains(PasswordErrors.TOO_SHORT))
    }

    @Test
    fun passwordNoNumbers() {
        val password = "aaAAA*"
        val errorsEnumSet = checkValidity(password)
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_NUMBER))
    }

    @Test
    fun passwordNoUppercase() {
        val password = "aa*1234"
        val errorsEnumSet = checkValidity(password)
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_UPPERCASE))
    }

    @Test
    fun passwordNoLowercase() {
        val password = "VV*1234"
        val errorsEnumSet = checkValidity(password)
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_LOWERCASE))
    }

    @Test
    fun passwordEmpty() {
        val password = ""
        val errorsEnumSet = checkValidity(password)
        assertTrue(errorsEnumSet.contains(PasswordErrors.TOO_SHORT))
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_LOWERCASE))
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_UPPERCASE))
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_NUMBER))
        assertTrue(errorsEnumSet.contains(PasswordErrors.NO_SPECIAL_CHAR))
    }

    @Test
    fun passwordValid() {
        val password = "abcDEF123!!!"
        val errorsEnumSet = checkValidity(password)
        assertEquals(errorsEnumSet.size, 0)
    }
}