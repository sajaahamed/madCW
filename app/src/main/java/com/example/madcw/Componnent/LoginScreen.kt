package com.example.madcw.Componnent

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madcw.viewmodel.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), modifier: Modifier = Modifier) {

    var indexNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginResponse by viewModel.loginResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(loginResponse) {
        loginResponse?.let {
            Log.d("LoginScreen", "Login Success: ${it.fullName}")
            Toast.makeText(context, "Welcome ${it.fullName}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome",
            fontSize = 40.sp,
            modifier = Modifier.padding(start = 40.dp, bottom = 40.dp)
        )

        OutlinedTextField(
            value = indexNumber,
            onValueChange = { indexNumber = it },
            label = { Text("Enter Index Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.loginUser(indexNumber.trim(), password.trim())
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Text(text = if (isLoading) "Logging in..." else "Login")
        }

        if (!errorMessage.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = errorMessage ?: "",
                color = androidx.compose.ui.graphics.Color.Red,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    LoginScreen()
}
