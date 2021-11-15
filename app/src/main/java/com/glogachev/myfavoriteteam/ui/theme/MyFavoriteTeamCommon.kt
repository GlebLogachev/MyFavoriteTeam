package com.glogachev.myfavoriteteam.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class MyFavoriteTeamColors(
    val primaryText: Color,
    val secondaryText: Color,
    val descriptionText: Color,
    val hintColor: Color,
    val actionText: Color,
    val primaryBackground: Color,
    val secondaryBackground: Color,
)

@Immutable
data class MyFavoriteTeamTypography(
    val regular13: TextStyle,
    val regular15: TextStyle,
    val regular16: TextStyle,
    val regular17: TextStyle,
    val medium14: TextStyle,
    val medium15: TextStyle,
    val medium16: TextStyle,
    val semiBold15: TextStyle,
    val semiBold16: TextStyle,
    val semiBold17: TextStyle,
    val semiBold20: TextStyle,
    val bold24: TextStyle,
)

object MyFavoriteTeamTheme {
    val colors: MyFavoriteTeamColors
        @Composable
        get() = LocalMyFavoriteTeamColors.current

    val typography: MyFavoriteTeamTypography
        @Composable
        get() = LocalJetHabitTypography.current
}

val LocalMyFavoriteTeamColors = staticCompositionLocalOf<MyFavoriteTeamColors> {
    error("No colors provided")
}

val LocalJetHabitTypography = staticCompositionLocalOf<MyFavoriteTeamTypography> {
    error("No font provided")
}