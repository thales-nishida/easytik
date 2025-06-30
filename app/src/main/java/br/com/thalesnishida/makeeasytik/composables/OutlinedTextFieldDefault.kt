package br.com.thalesnishida.makeeasytik.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.thalesnishida.makeeasytik.ui.theme.textFieldColors

@Composable
fun OutlinedTextFieldDefault(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    isError: Boolean = true,
    errorMessage: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    Column(modifier = modifier.padding(8.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            isError = isError,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = VisualTransformation.None,
            shape = RoundedCornerShape(8.dp),
            colors = textFieldColors(),
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewOutlinedTextFieldDefault() {
    OutlinedTextFieldDefault(
        value = "Teste",
        onValueChange = { },
        label = "teste"
    )
}