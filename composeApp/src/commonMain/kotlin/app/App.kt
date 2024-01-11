@file:OptIn(ExperimentalResourceApi::class)

package app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import comunexo.di.GameModule
import comunexo.feature.game.GameRoute
import comunexo.feature.game.model.GameOption
import comunexo.feature.game.model.OptionItem
import core.firebase.FirebaseModule
import core.utils.gerarIdRandom
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinApplication
import register.di.RegisterViewModelModule
import theme.typography

fun appModules() = listOf(
    FirebaseModule.modules,
    RegisterViewModelModule.modules,
    GameModule.modules
)

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModules())
    }) {

        val optionsFake = listOf(
            OptionItem(
                title = "Item 1",
                category = "Jesus",
                color = "#28A745",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Hipotese",
                category = "Jesus",
                color = "#28A745",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Hospedagem",
                category = "Jesus",
                color = "#28A745",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Item 4",
                category = "Jesus",
                color = "#28A745",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Item 5",
                category = "Cloro",
                color = "#dc3545",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Passaporte",
                category = "Cloro",
                color = "#dc3545",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Item 7",
                category = "Cloro",
                color = "#dc3545",
                isChecked = false,
                isError = false
            ),
            OptionItem(
                title = "Item 8",
                category = "Cloro",
                color = "#dc3545",
                isChecked = false,
                isError = false
            ),
        )

        val id = String().gerarIdRandom()

        val gameOption = GameOption(
            id = id,
            title = "Jogo 1",
            optionItem = optionsFake
        )

        MaterialTheme(
            typography = typography,
        ) {
            Navigator(
                screen = GameRoute(gameOption),
            ) { navigator ->
                SlideTransition(navigator)
            }
//            Navigator(
//                screen = WelcomeScreen(),
//            ) { navigator ->
//                SlideTransition(navigator)
//            }
        }
    }
}