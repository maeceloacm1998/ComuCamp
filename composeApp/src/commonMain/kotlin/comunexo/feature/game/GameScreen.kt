package comunexo.feature.game

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import comunexo.components.toolbar.ToolbarComunexo
import comunexo.feature.game.model.OptionItem
import kotlinx.coroutines.launch
import theme.Background
import theme.ComunexoPrimary
import theme.ComunexoSecondary
import theme.Error

@Composable
fun GameScreen(
    uiState: GameScreenModelUiState.GameState,
    onCheckedItem: (option: OptionItem) -> Unit
) {
    if (uiState.onLoading) {
        CircularProgressIndicator()
    } else {
        Scaffold(
            topBar = {
                ToolbarComunexo(
                    title = "Comunexo",
                    onFaqListener = {},
                    onNavigationListener = {}
                )
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
                    .background(Background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(4),
                    contentPadding = PaddingValues(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 20.dp
                ) {
                    items(uiState.options, key = { item -> item.title }) { item ->
                        BoxOption(
                            item = item,
                            onCheckedItem = onCheckedItem
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BoxOption(
    item: OptionItem,
    onCheckedItem: (option: OptionItem) -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    val cardContentColor by animateColorAsState(targetValue = if (item.isChecked) ComunexoSecondary else ComunexoPrimary)
    val coroutineScope = rememberCoroutineScope()

    val xOffset = remember {
        Animatable(0f)
    }

    LaunchedEffect(item.isError) {
        if(item.isError) {
            coroutineScope.launch {
                xOffset.animateTo(
                    targetValue = 10f,
                    animationSpec = tween(
                        durationMillis = 150,
                        easing = LinearEasing
                    )
                )

                xOffset.animateTo(
                    targetValue = -10f,
                    animationSpec = tween(
                        durationMillis = 150,
                        easing = LinearEasing
                    )
                )

                xOffset.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 150,
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    OutlinedCard(
        modifier = Modifier
            .height(80.dp)
            .graphicsLayer {
                translationX = xOffset.value
            }
            .clickable {
                onCheckedItem(item)
                clicked = !clicked
            },
        colors = CardDefaults.cardColors(
            containerColor = cardContentColor
        ),
        border = BorderStroke(
            color = if (item.isError) Error else Color.Unspecified,
            width = 3.dp
        )
    ) {
        val textSize = item.title.length
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item.title,
                color = if (item.isError) Color.Red else Color.White,
                fontSize = if (textSize <= 8) 16.sp else 10.sp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

