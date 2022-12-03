package com.dziaber.studentbeansdemo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dziaber.studentbeansdemo.R
import com.dziaber.studentbeansdemo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val email = binding.email
        val password = binding.password
        val loginButton = binding.login

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            loginButton.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                email.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                handleLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                handleLoginSuccess(loginResult.success)
            }
        })

        email.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                    email.text.toString(),
                    password.text.toString()
            )
        }

        password.apply {
            doAfterTextChanged {
                loginViewModel.loginDataChanged(
                        email.text.toString(),
                        password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                email.text.toString(),
                                password.text.toString()
                        )
                }
                false
            }

            loginButton.setOnClickListener {
                loginViewModel.login(email.text.toString(), password.text.toString())
            }
        }
        return binding.root
    }

    private fun handleLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(requireContext().applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoginSuccess(userData: LoggedInUserView) {
        Toast.makeText(requireContext().applicationContext, "Welcome back, "+userData.displayName, Toast.LENGTH_SHORT).show()
        this.findNavController().navigate(R.id.action_loginFragment_to_photosFragment)
    }
}