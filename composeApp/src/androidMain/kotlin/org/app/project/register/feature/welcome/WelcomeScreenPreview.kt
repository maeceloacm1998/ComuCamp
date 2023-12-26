@file:OptIn(ExperimentalResourceApi::class, ExperimentalResourceApi::class)

package org.app.project.register.feature.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.ExperimentalResourceApi
import register.feature.welcome.WelcomeScreen

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen().Content()
}