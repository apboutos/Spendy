package com.apboutos.spendy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.apboutos.spendy.ui.theme.SpendyTheme

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(true) }
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()  // Fill the max size of the parent
            .padding(32.dp), // Add padding if needed
        contentAlignment = Alignment.TopCenter // Center the content of the Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            val logo: Painter = painterResource(id = R.drawable.logo) // Replace with your logo resource
            Image(
                painter = logo,
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(300.dp) // Adjust the size as needed
                    .padding(bottom = 16.dp) // Space below the image
            )

            Spacer(
                modifier = Modifier.height(64.dp)
            )

            TextField(
                modifier = Modifier.size(400.dp,60.dp),
                value = username,
                onValueChange = {username = it},
                label = { Text("Username")},
                maxLines = 1,
                textStyle = TextStyle(color = Color.Cyan)
            )

            Spacer(
                modifier = Modifier.height(64.dp)
            )

            TextField(
                modifier = Modifier.size(400.dp,60.dp),
                value = password,
                onValueChange = { password = it },
                textStyle = TextStyle(color = Color.Cyan),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = "Password", color = Color.Blue)},
                maxLines = 1
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = "Remember Me"
                )
            }

            Spacer(
                modifier = Modifier.height(64.dp)
            )

            Button(
                modifier = Modifier.size(200.dp,60.dp),
                onClick = { isVisible = !isVisible }) {
                Text(
                    text = "Login",
                    fontSize = TextUnit(30F, TextUnitType.Sp)
                )
            }

            Spacer(
                modifier = Modifier.height(64.dp)
            )

            if (isVisible) {
                Text(
                    text = "I don't have an account",
                    fontSize = TextUnit(20F, TextUnitType.Sp)
                )
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpendyTheme {
        LoginScreen()
    }
}