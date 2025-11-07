package com.example.madcw.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcw.api.RetrofItInstance
import com.example.madcw.Entity.LoginRequest
import com.example.madcw.Entity.LoginRespones
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginResponse = MutableStateFlow<LoginRespones?>(null)
    val loginResponse = _loginResponse.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun loginUser(indexNumber: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val trimmedIndex = indexNumber.trim()
            val trimmedPassword = password.trim()

            Log.d("LoginViewModel", "Sending login: index='$trimmedIndex', password='$trimmedPassword'")

            try {
                val response = RetrofItInstance.instance.loginUser(
                    LoginRequest(trimmedIndex, trimmedPassword)
                )

                if (response.isSuccessful) {
                    Log.d("LoginViewModel", "Login success: ${response.body()}")
                    _loginResponse.value = response.body()
                } else {
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = errorBody ?: "Login failed: ${response.code()}"
                    Log.d("LoginViewModel", "Login failed: $errorBody")
                }

            } catch (e: Exception) {
                _errorMessage.value = "Network error: ${e.message}"
                Log.e("LoginViewModel", "Exception during login", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
