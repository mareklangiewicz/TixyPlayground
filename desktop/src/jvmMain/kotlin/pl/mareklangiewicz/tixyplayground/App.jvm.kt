package pl.mareklangiewicz.tixyplayground

import androidx.compose.ui.window.*

fun main() = application {
  Window(onCloseRequest = ::exitApplication, title = "Tixy Playground") {
    App()
  }
}
