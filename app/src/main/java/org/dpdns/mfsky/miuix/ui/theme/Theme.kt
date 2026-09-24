// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.dpdns.mfsky.miuix.ui.settings.AppSettings
import org.dpdns.mfsky.miuix.ui.settings.keyColorFor
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemeController
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle

/**
 * The application theme, based on the Miuix design system.
 *
 * The colour scheme comes from [ThemeController], which is what lets the settings page switch
 * between the fixed light/dark palettes and the Monet-derived ones (seed colour + palette style +
 * spec) while the app is running.
 *
 * Everything inside is rendered with Miuix components only — no Material is involved.
 */
@Composable
fun MIUIXTheme(
    settings: AppSettings,
    content: @Composable () -> Unit,
) {
    val mode = ColorSchemeMode.entries.getOrElse(settings.colorMode) { ColorSchemeMode.System }
    val spec = ThemeColorSpec.entries.getOrElse(settings.colorSpec) { ThemeColorSpec.Spec2021 }
    val style = ThemePaletteStyle.entries.getOrElse(settings.paletteStyle) { ThemePaletteStyle.TonalSpot }
    val keyColor = keyColorFor(settings.keyColorIndex)
    val controller = remember(mode, spec, style, keyColor) {
        when (mode) {
            // The fixed palettes take no seed colour, so they are built from the mode alone.
            ColorSchemeMode.System,
            ColorSchemeMode.Light,
            ColorSchemeMode.Dark,
            -> ThemeController(colorSchemeMode = mode)

            else -> ThemeController(
                colorSchemeMode = mode,
                keyColor = keyColor,
                colorSpec = spec,
                paletteStyle = style,
            )
        }
    }
    MiuixTheme(
        controller = controller,
        content = content,
    )
}
