// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.edit

/**
 * Everything the settings page can change about the app.
 *
 * It is written to [SharedPreferences] on every change, so the app comes back the way it was left.
 */
data class AppSettings(
    /** Index into `ColorSchemeMode.entries`: System / Light / Dark / MonetSystem / MonetLight / MonetDark. */
    val colorMode: Int = 0,
    /** `0` keeps the built-in primary colour, otherwise `1..n` indexes into [KeyColors]. */
    val keyColorIndex: Int = 0,
    /** Index into `ThemePaletteStyle.entries`. */
    val paletteStyle: Int = 0,
    /** Index into `ThemeColorSpec.entries`. */
    val colorSpec: Int = 0,
    /** The progressive blur behind the top bar. */
    val enableBlur: Boolean = true,
    /** The floating OS4 glass bar instead of the regular NavigationBar. */
    val useGlassNavigationBar: Boolean = true,
)

/**
 * Seed colours offered for the dynamic (Monet) colour schemes.
 */
val KeyColors: List<Pair<String, Color>> = listOf(
    "蓝" to Color(0xFF3482FF),
    "绿" to Color(0xFF36D167),
    "紫" to Color(0xFF7C4DFF),
    "黄" to Color(0xFFFFB21D),
    "橙" to Color(0xFFFF5722),
    "粉" to Color(0xFFE91E63),
    "青" to Color(0xFF00BCD4),
)

/** `null` when the built-in primary colour should be kept. */
fun keyColorFor(index: Int): Color? = KeyColors.getOrNull(index - 1)?.second

private const val PREFS_NAME = "miuix_catalog_settings"
private const val KEY_COLOR_MODE = "color_mode"
private const val KEY_KEY_COLOR = "key_color"
private const val KEY_PALETTE_STYLE = "palette_style"
private const val KEY_COLOR_SPEC = "color_spec"
private const val KEY_ENABLE_BLUR = "enable_blur"
private const val KEY_GLASS_NAVIGATION_BAR = "use_glass_navigation_bar"

/**
 * Holds the live [AppSettings] and writes every change through to [SharedPreferences].
 */
@Stable
class AppSettingsState internal constructor(
    private val preferences: SharedPreferences,
    initial: AppSettings,
) {
    var current: AppSettings by mutableStateOf(initial)
        private set

    fun set(settings: AppSettings) {
        current = settings
        preferences.edit {
            putInt(KEY_COLOR_MODE, settings.colorMode)
            putInt(KEY_KEY_COLOR, settings.keyColorIndex)
            putInt(KEY_PALETTE_STYLE, settings.paletteStyle)
            putInt(KEY_COLOR_SPEC, settings.colorSpec)
            putBoolean(KEY_ENABLE_BLUR, settings.enableBlur)
            putBoolean(KEY_GLASS_NAVIGATION_BAR, settings.useGlassNavigationBar)
        }
    }
}

private fun read(preferences: SharedPreferences): AppSettings {
    val defaults = AppSettings()
    return AppSettings(
        colorMode = preferences.getInt(KEY_COLOR_MODE, defaults.colorMode),
        keyColorIndex = preferences.getInt(KEY_KEY_COLOR, defaults.keyColorIndex),
        paletteStyle = preferences.getInt(KEY_PALETTE_STYLE, defaults.paletteStyle),
        colorSpec = preferences.getInt(KEY_COLOR_SPEC, defaults.colorSpec),
        enableBlur = preferences.getBoolean(KEY_ENABLE_BLUR, defaults.enableBlur),
        useGlassNavigationBar = preferences.getBoolean(KEY_GLASS_NAVIGATION_BAR, defaults.useGlassNavigationBar),
    )
}

@Composable
fun rememberAppSettings(): AppSettingsState {
    val context = LocalContext.current
    return remember(context) {
        val preferences = context.applicationContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        AppSettingsState(preferences, read(preferences))
    }
}

/** The settings currently in effect. */
val LocalAppSettings = staticCompositionLocalOf { AppSettings() }

/** Replaces the settings; every change is persisted. */
val LocalSetAppSettings = staticCompositionLocalOf<(AppSettings) -> Unit> { {} }
