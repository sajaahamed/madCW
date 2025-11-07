package com.example.madcw.Componnent

import android.R
import android.R.attr.contentDescription
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import androidx.activity.SystemBarStyle.Companion.light
import androidx.compose.foundation.Image
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.blue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.madcw.viewmodel.LoginViewModel



@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(),modifier: Modifier= Modifier) {
    var indexNumber by remember { mutableStateOf(" ") }
    var password by remember { mutableStateOf("") }

    val loginResponse by viewModel.loginResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center){



        Row {
            Spacer(modifier = Modifier.height(  60.dp).width(110.dp))

        }

        Row {
            Spacer(modifier = Modifier.height(  60.dp).width(110.dp))
            Text(text = "Welcome",
                fontSize = 40.sp,
                fontStyle = FontStyle.Normal)
        }
        Row {
            Spacer(modifier = Modifier.height(  60.dp).width(60.dp))
            OutlinedTextField(
                modifier = Modifier.height(60.dp)
                    .width(300.dp),
                value = indexNumber,
                onValueChange = { indexNumber = it },
                label = { Text("Enter Index Number") },


                )
        }
        Spacer(modifier = Modifier.height(  20.dp))

        Row {
            Spacer(modifier = Modifier.height(  60.dp).width(60.dp))
            OutlinedTextField(
                modifier = Modifier.height(60.dp)
                    .width(300.dp),
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                )
        }
        Spacer(modifier = Modifier.height(  20.dp).width(10.dp))

        Row {
            Spacer(
                modifier = Modifier
                    .height(60.dp)
                    .width(90.dp)
            )

            Button(
                modifier = Modifier
                    .height(40.dp)
                    .width(250.dp),
                onClick = {
                    viewModel.loginUser(indexNumber, password)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.holo_blue_dark)
                ),
                enabled = !isLoading
            ) {
                Text(if (isLoading) "Logging in..." else "Login")
            }


        }

    }




}

@Preview(showBackground = true)
@Composable
fun ScreenPreview(){
    LoginScreen( modifier= Modifier)

}