package comunexo.components.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Quiz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import theme.Background
import theme.ComunexoPrimary
import theme.ComunexoPrimaryLight
import theme.CustomDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarComunexo(
    modifier: Modifier = Modifier,
    title: String,
    onFaqListener: () -> Unit,
    onNavigationListener: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = title,
                color = ComunexoPrimary
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Background,
            navigationIconContentColor = Background
        ),
        navigationIcon = {
            IconButton(onClick = { onNavigationListener() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "voltar",
                    tint = ComunexoPrimary
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier.padding(end = CustomDimensions.padding10)
            ) {
                IconButton(
                    onClick = { onFaqListener() },
                    modifier = Modifier
                        .size(CustomDimensions.padding35)
                        .clip(CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = ComunexoPrimaryLight
                    )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Quiz,
                        tint = ComunexoPrimary,
                        contentDescription = "Localized description"
                    )
                }
            }
        },
    )
}