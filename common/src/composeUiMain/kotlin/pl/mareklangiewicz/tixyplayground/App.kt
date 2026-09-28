package pl.mareklangiewicz.tixyplayground
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column(Modifier.background(Color.Black)) {
            Button(onClick = {
                text = "Hello, ${getPlatformName()}"
            }) {
                Text(text)
            }
            TixyLands()
        }
    }
}
