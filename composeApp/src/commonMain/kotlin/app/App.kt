package app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import register.feature.welcome.WelcomeScreen
import theme.typography


@Composable
fun App() {
    MaterialTheme(
        typography = typography,
    ) {
        Navigator(screen = WelcomeScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}