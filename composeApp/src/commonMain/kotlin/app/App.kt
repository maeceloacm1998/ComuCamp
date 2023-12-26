@file:OptIn(ExperimentalResourceApi::class)

package app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinApplication
import register.di.RegisterViewModelModule
import register.feature.welcome.WelcomeScreen
import theme.typography

fun appModules() = listOf(
    RegisterViewModelModule.modules
)

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModules())
    }) {
        MaterialTheme(
            typography = typography,
        ) {
            Navigator(
                screen = WelcomeScreen(),
            ) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}