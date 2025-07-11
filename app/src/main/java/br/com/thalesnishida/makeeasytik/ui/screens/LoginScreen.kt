package br.com.thalesnishida.makeeasytik.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.thalesnishida.makeeasytik.composables.ButtonDefault
import br.com.thalesnishida.makeeasytik.composables.OutlinedTextFieldDefault
import br.com.thalesnishida.makeeasytik.viewmodels.LoginViewModel
import br.com.thalesnishida.makeeasytik.viewmodels.LoginViewModelImpl

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextFieldDefault(
            value = "Username",
            onValueChange = { },
            label = "Username",
            isError = false
        )

        OutlinedTextFieldDefault(
            value = "Password",
            onValueChange = { },
            label = "password",
            isError = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonDefault(
            modifier = Modifier.width(200.dp),
            onClick = {}
        ) {
            Text("Login")
        }

        ButtonDefault(
            modifier = Modifier.width(200.dp),
            onClick = {}
        ) {
            Text("Cadastrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel<LoginViewModelImpl>()
    )
}