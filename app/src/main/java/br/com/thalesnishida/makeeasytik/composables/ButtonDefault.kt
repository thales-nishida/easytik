package br.com.thalesnishida.makeeasytik.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import br.com.thalesnishida.makeeasytik.ui.theme.MakeEasyTikAppTheme

@Composable
fun ButtonDefault(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        content()
    }
}

@Preview
@Composable
private fun PreviewDefaultButton() {
    MakeEasyTikAppTheme {
        ButtonDefault(
            onClick = { }
        ) {
            Text("Button")
        }
    }
}

@Preview(backgroundColor = 0xFFF, showBackground = true)
@Composable
private fun PreviewDefaultButtonDisabled() {
    MakeEasyTikAppTheme {
        ButtonDefault(
            onClick = { },
            enabled = false
        ) {
            Text("Button")
        }
    }
}