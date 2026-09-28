package pl.mareklangiewicz.tixyplayground.androapp

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import pl.mareklangiewicz.tixyplayground.App

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { App() }
  }
}
