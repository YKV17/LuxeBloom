package com.ykv17.luxebloom.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ykv17.luxebloom.R
import com.ykv17.luxebloom.ui.theme.LuxeBloomTheme

@Composable
fun LoginScreen(navController: NavHostController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "LuxeBloom",
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            WelcomeBackSpannableText()
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {

                Spacer(modifier = Modifier.height(20.dp))
                /*Text(
                    text = "Username",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))*/
                AppTextField(
                    placeholder = "Enter your username",
                    leftImageVector = Icons.Outlined.AccountCircle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                /* Text(
                     text = "Password",
                     color = MaterialTheme.colorScheme.onSurface,
                     fontSize = 14.sp
                 )
                 Spacer(modifier = Modifier.height(4.dp))*/
                AppTextField(
                    placeholder = "Enter your password",
                    leftImageVector = Icons.Outlined.Lock,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Reset Password",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(64.dp))

                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Login")
                }

                SingUpSpannableText()
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    placeholder: String = "",
    leftImageVector: ImageVector,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
) {
    var value by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(imageVector = leftImageVector, contentDescription = "")
        },
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
    /*BasicTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
                innerTextField()
            }


        },
        modifier = modifier
    )*/
}

@Composable
fun SingUpSpannableText() {
    val annotatedString = buildAnnotatedString {
        append("New to LuxeBloom?")
        withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
            append(" SignUp")
        }
    }

    Text(
        text = annotatedString,
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

@Composable
fun WelcomeBackSpannableText() {
    val annotatedString = buildAnnotatedString {
        append("Welcome")
        withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
            append(" Back")
        }
    }

    Text(
        text = annotatedString,
        fontSize = 32.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Thin,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}
