package pl.mareklangiewicz.tixyplayground

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.math.absoluteValue
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

// https://tixy.land/

val tixies = listOf<(Float, Float, Float, Float) -> Float>(
//    { t, i, x, y -> x / 16 },
//    { t, i, x, y -> y / 16 },
//    { t, i, x, y -> y / 8 - 1 },
//    { t, i, x, y -> i / 120 - 1 },
//    { t, i, x, y -> y - 7.5f },
//    { t, i, x, y -> y - t * 4 },
//    { t, i, x, y -> y - x },
//    { t, i, x, y -> sin(x) },
//    { t, i, x, y -> sin(t) },
//    { t, i, x, y -> sin(x + t) },
//    { t, i, x, y -> sin(t * y - x) },
//    { t, i, x, y -> sin(i.pow(2)) },
    { t, i, x, y -> cos(t + i + x * y) },
//    { t, i, x, y -> sin(x/2) - sin(x-t) - y+6 },
//    { t, i, x, y -> (x-y) - sin(t) * 16 },
    { t, i, x, y -> (x-y)/24 - sin(t) },
//    { t, i, x, y -> sin(t*5) * tan(t*7) },
    { t, i, x, y -> (x-8)*(y-8) - sin(t)*64 },
//    { t, i, x, y -> -.4f/(hypot(x-t%10,y-t%8)-t%2*9) },
//    { t, i, x, y -> sin(t - sqrt(x.pow(2) + y.pow(2))) },
//    { t, i, x, y -> sin(t - sqrt((x - 7.5f).pow(2) + (y - 6f).pow(2))) },
//    { t, i, x, y -> y-t*3+9+3*cos(x*3-t)-5*sin(x*7) },
    { t, i, x, y -> 1f/32*tan(t/64*x*tan(i-x)) },
    { t, i, x, y -> sin(2*atan((y-7.5f)/(x-7.5f))+5*t) },
//    { t, i, x, y ->
//        ((if (x + y < 16) 1f else 0f) * (if (x + y < 8) 1 else -1)).takeIf { it != 0f } ?: if (x - y < 1) 1f else 0f
//    },
    { t, i, x, y -> sin(y / 8f + t) },
//    { t, i, x, y -> Random.nextInt(-100, 100) / 100f }
)

@Composable
fun Timer(delayMs: Long = 50) = (1..Int.MAX_VALUE).asFlow()
    .map { delay(delayMs); it * delayMs / 1000f }
    .collectAsState(0f)

@Composable
fun TixyLand(type: Int = 4, timer: State<Float> = Timer()) {

    val t by timer

    Canvas(Modifier.background(Color.Black).size((16 * 16).dp)) {
        for (i in 0..255) {
            val y = (i / 16).toFloat()
            val x = (i % 16).toFloat()
            val v = tixies[type](t, i.toFloat(), x, y).coerceIn(-1f, 1f)
            drawCircle(
                color = if (v < 0) Color.Red else Color.White,
                radius = v.absoluteValue * 8,
                center = Offset(x * 16 + 8, y * 16 + 8)
            )
        }
    }
}

@Composable
fun TixyLands() {
    val timer = Timer()
    Column(Modifier.fillMaxSize().background(Color.Black)) {
        Text("ccc", modifier = Modifier.semantics { text = AnnotatedString("ccc") })
        Row {
            TixyLand(0, timer)
            TixyLand(1, timer)
        }
        Row {
            TixyLand(2, timer)
            TixyLand(3, timer)
        }
        Row {
            TixyLand(4, timer)
            TixyLand(5, timer)
        }
    }
}

