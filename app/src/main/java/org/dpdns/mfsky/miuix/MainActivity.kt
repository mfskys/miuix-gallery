// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import org.dpdns.mfsky.miuix.ui.MiuixCatalogApp
import org.dpdns.mfsky.miuix.ui.settings.AppSettings
import org.dpdns.mfsky.miuix.ui.settings.LocalAppSettings
import org.dpdns.mfsky.miuix.ui.settings.LocalSetAppSettings
import org.dpdns.mfsky.miuix.ui.settings.rememberAppSettings
import org.dpdns.mfsky.miuix.ui.theme.MIUIXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // API 29 guard dropped: minSdk is 33, so the template's version check never took the
        // other branch.
        window.isNavigationBarContrastEnforced = false
        setContent {
            val settings = rememberAppSettings()
            val updateSettings: (AppSettings) -> Unit = remember(settings) { { settings.set(it) } }
            CompositionLocalProvider(
                LocalAppSettings provides settings.current,
                LocalSetAppSettings provides updateSettings,
            ) {
                MIUIXTheme(settings = settings.current) {
                    MiuixCatalogApp()
                }
            }
        }
    }
}
