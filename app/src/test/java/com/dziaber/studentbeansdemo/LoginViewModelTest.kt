package com.dziaber.studentbeansdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dziaber.studentbeansdemo.data.LoginRepository
import com.dziaber.studentbeansdemo.data.Result
import com.dziaber.studentbeansdemo.data.model.LoggedInUser
import com.dziaber.studentbeansdemo.ui.login.LoginViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.io.IOException

class LoginViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun invalidLogin() {
        val mockLoginRepository = mock<LoginRepository>{
            on {login("damian@123.co", "Abc123456")} doReturn
                    Result.Error(IOException())
        }
        val loginViewModel = LoginViewModel(mockLoginRepository)
        loginViewModel.login("damian@123.co", "Abc123456")
        Assert.assertNull(loginViewModel.loginResult.value?.success)
        Assert.assertNotNull(loginViewModel.loginResult.value?.error)
    }

    @Test
    fun validLogin() {
        val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "dmin")
        val mockLoginRepository = mock<LoginRepository>{
            on {login("damian@123.co", "Abc123456")} doReturn
                    Result.Success(fakeUser)
        }
        val loginViewModel = LoginViewModel(mockLoginRepository)

        loginViewModel.login("damian@123.co", "Abc123456")
        Assert.assertNull(loginViewModel.loginResult.value?.error)
        Assert.assertNotNull(loginViewModel.loginResult.value?.success)
    }
}