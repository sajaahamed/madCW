package com.example.madcw.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.madcw.api.RetrofItInstance
import com.example.madcw.Entity.LoginRequest
import com.example.madcw.Entity.LoginRespones
import com.example.madcw.api.LoginApi
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
            try {
                val response = RetrofItInstance.instance.loginUser(LoginRequest(indexNumber,password))
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    _errorMessage.value = "Login failed: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
