package com.example.madcw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.madcw.Componnent.CoursesScreen
import com.example.madcw.Componnent.DegreesScreen
import com.example.madcw.Componnent.HomeScreen
import com.example.madcw.Componnent.LoginScreen
import com.example.madcw.ui.theme.MadCWTheme
import com.example.madcw.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadCWTheme {
                val navController = rememberNavController()
                val loginViewModel: LoginViewModel = viewModel()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(viewModel = loginViewModel) { loginRes ->
                            // Nav to successful login
                            navController.navigate("home/${loginRes.fullName}") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    }
                    composable(
                        "home/{fullName}",
                        arguments = listOf(navArgument("fullName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val fullName = backStackEntry.arguments?.getString("fullName") ?: ""
                        HomeScreen(fullName = fullName,navController = navController)
                    }
                    composable("courses") {
                        CoursesScreen()
                    }
                    composable("degrees") {
                        DegreesScreen()
                    }

                }
            }
        }
    }
}
