package com.lyscraft.apparel.compose.theme

import androidx.compose.material3.Typography

private val DefaultTypography = Typography()
val MontserratTypography = Typography(
    displayLarge = DefaultTypography.displayLarge.copy(fontFamily = Montserrat),
    displayMedium = DefaultTypography.displayMedium.copy(fontFamily = Montserrat),
    displaySmall = DefaultTypography.displaySmall.copy(fontFamily = Montserrat),

    headlineLarge = DefaultTypography.headlineLarge.copy(fontFamily = Montserrat),
    headlineMedium = DefaultTypography.headlineMedium.copy(fontFamily = Montserrat),
    headlineSmall = DefaultTypography.headlineSmall.copy(fontFamily = Montserrat),

    titleLarge = DefaultTypography.titleLarge.copy(fontFamily = Montserrat),
    titleMedium = DefaultTypography.titleMedium.copy(fontFamily = Montserrat),
    titleSmall = DefaultTypography.titleSmall.copy(fontFamily = Montserrat),

    bodyLarge = DefaultTypography.bodyLarge.copy(fontFamily = Montserrat),
    bodyMedium = DefaultTypography.bodyMedium.copy(fontFamily = Montserrat),
    bodySmall = DefaultTypography.bodySmall.copy(fontFamily = Montserrat),

    labelLarge = DefaultTypography.labelLarge.copy(fontFamily = Montserrat),
    labelMedium = DefaultTypography.labelMedium.copy(fontFamily = Montserrat),
    labelSmall = DefaultTypography.labelSmall.copy(fontFamily = Montserrat)
)
