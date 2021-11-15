package com.glogachev.myfavoriteteam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MyFavoriteTeamTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = MyFavoriteTeamColors(
        primaryText = Color(0xFF050510),
        secondaryText = Color(0xFF97979B),
        descriptionText = Color(0xFF55555C),
        hintColor = Color(0xFFC3C3C6),
        actionText = Color(0xFF6534FF),
        primaryBackground = Color.White,
        secondaryBackground = Color(0xFFF7F7F8),
    )

    val typography = MyFavoriteTeamTypography(
        regular13 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        ),
        regular15 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        regular16 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        regular17 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        medium14 = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        medium15 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        medium16 = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        semiBold15 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        ),
        semiBold16 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        ),
        semiBold17 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp
        ),
        semiBold20 = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        ),
        bold24 = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
    )

    CompositionLocalProvider(
        LocalMyFavoriteTeamColors provides colors,
        LocalJetHabitTypography provides typography,
        content = content
    )
}