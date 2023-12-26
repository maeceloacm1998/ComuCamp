@file:OptIn(ExperimentalResourceApi::class)

package register.feature.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import components.button.OutlinedButtonCustom
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import register.feature.registerusername.RegisterUserNameRoute
import theme.Background
import theme.CustomDimensions
import theme.Primary
import utils.Spacer

@ExperimentalResourceApi
internal class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigation = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Background)
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = CustomDimensions.padding20),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(CustomDimensions.padding50)

                Image(painterResource("img_camping.xml"), null)

                Spacer(CustomDimensions.padding50)

                Text(
                    text = "BEM VINDO AO COMUCAMP",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = Primary,
                )

                Spacer(CustomDimensions.padding30)

                Text(
                    text = "Participe de quizzes, compartilhe suas aventuras e ganhe prêmios no ComuCamp!",
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }

            OutlinedButtonCustom(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = CustomDimensions.padding20)
                    .padding(bottom = CustomDimensions.padding40),
                title = "Avançar", onButtonListener = {
                    navigation.push(RegisterUserNameRoute())
                })
        }
    }
}