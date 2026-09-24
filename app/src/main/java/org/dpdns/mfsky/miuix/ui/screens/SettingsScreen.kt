// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.dpdns.mfsky.miuix.ui.settings.AppSettings
import org.dpdns.mfsky.miuix.ui.settings.KeyColors
import org.dpdns.mfsky.miuix.ui.settings.LocalAppSettings
import org.dpdns.mfsky.miuix.ui.settings.LocalSetAppSettings
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle

private val ColorModeLabels = listOf(
    "跟随系统",
    "浅色",
    "深色",
    "动态取色 · 跟随系统",
    "动态取色 · 浅色",
    "动态取色 · 深色",
)

private val PaletteStyleLabels = ThemePaletteStyle.entries.map { labelOf(it) }

private fun labelOf(style: ThemePaletteStyle): String = when (style) {
    ThemePaletteStyle.TonalSpot -> "TonalSpot · 色调点缀"
    ThemePaletteStyle.Neutral -> "Neutral · 中性"
    ThemePaletteStyle.Vibrant -> "Vibrant · 鲜艳"
    ThemePaletteStyle.Expressive -> "Expressive · 表现力"
    ThemePaletteStyle.Rainbow -> "Rainbow · 彩虹"
    ThemePaletteStyle.FruitSalad -> "FruitSalad · 果盘"
    ThemePaletteStyle.Monochrome -> "Monochrome · 单色"
    ThemePaletteStyle.Fidelity -> "Fidelity · 保真"
    ThemePaletteStyle.Content -> "Content · 内容"
}

private val ColorSpecLabels = listOf("Material 2021", "Material 2025")

/**
 * The settings page: it really changes the app rather than showing components off.
 *
 * Everything here maps onto a field of [AppSettings], which the theme and the app skeleton read
 * back — so a switch flipped here is visible everywhere immediately.
 */
fun LazyListScope.settingsContent() {
    item(key = "settings-appearance") {
        val settings = LocalAppSettings.current
        val setSettings = LocalSetAppSettings.current
        val dynamicColor = settings.colorMode >= 3

        SmallTitle(text = "外观")
        Card(modifier = Modifier.demoSettingsCard()) {
            OverlayDropdownPreference(
                title = "色彩模式",
                summary = "浅色 / 深色 / 动态取色",
                items = ColorModeLabels,
                selectedIndex = settings.colorMode,
                onSelectedIndexChange = { setSettings(settings.copy(colorMode = it)) },
            )
            AnimatedVisibility(visible = dynamicColor) {
                Column {
                    KeyColorPicker(
                        selectedIndex = settings.keyColorIndex,
                        onSelect = { setSettings(settings.copy(keyColorIndex = it)) },
                    )
                    OverlayDropdownPreference(
                        title = "配色风格",
                        summary = "由强调色生成整套配色的算法",
                        items = PaletteStyleLabels,
                        selectedIndex = settings.paletteStyle,
                        onSelectedIndexChange = { setSettings(settings.copy(paletteStyle = it)) },
                    )
                    OverlayDropdownPreference(
                        title = "色彩规范",
                        summary = "Material 3 的色彩生成规范版本",
                        items = ColorSpecLabels,
                        selectedIndex = settings.colorSpec,
                        onSelectedIndexChange = { setSettings(settings.copy(colorSpec = it)) },
                    )
                }
            }
        }
    }

    item(key = "settings-effects") {
        val settings = LocalAppSettings.current
        val setSettings = LocalSetAppSettings.current
        val blurSupported = isRuntimeShaderSupported()

        SmallTitle(text = "效果与导航")
        Card(modifier = Modifier.demoSettingsCard()) {
            SwitchPreference(
                title = "顶栏模糊",
                summary = if (blurSupported) {
                    "顶部大标题栏后的渐进模糊"
                } else {
                    "当前设备不支持运行时着色器"
                },
                checked = settings.enableBlur && blurSupported,
                enabled = blurSupported,
                onCheckedChange = { setSettings(settings.copy(enableBlur = it)) },
            )
            SwitchPreference(
                title = "玻璃底部导航栏",
                summary = "关闭后改用标准的 NavigationBar",
                checked = settings.useGlassNavigationBar,
                onCheckedChange = { setSettings(settings.copy(useGlassNavigationBar = it)) },
            )
        }
    }

    item(key = "settings-reset") {
        val setSettings = LocalSetAppSettings.current
        SmallTitle(text = "其它")
        Card(modifier = Modifier.demoSettingsCard()) {
            ArrowPreference(
                title = "恢复默认设置",
                summary = "把外观与效果重置为初始值",
                onClick = { setSettings(AppSettings()) },
            )
        }
    }

    item(key = "settings-about") {
        Card(modifier = Modifier.demoSettingsCard()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "MIUIX 演示 App",
                    style = MiuixTheme.textStyles.title4,
                )
                Text(
                    text = "基于 miuix 0.9.4 · Apache-2.0",
                    modifier = Modifier.padding(top = 4.dp),
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
                Text(
                    text = "界面与主题全部由 miuix 组件构建，本 App 只做演示，不含任何商业内容。",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
        }
    }
}

/**
 * A row of seed colours for the dynamic colour schemes; the selected one gets a ring.
 */
@Composable
private fun KeyColorPicker(
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(
            text = "强调色 · ${KeyColors.getOrNull(selectedIndex - 1)?.first ?: "默认"}",
            style = MiuixTheme.textStyles.body1,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            KeyColors.forEachIndexed { index, (_, color) ->
                val selected = selectedIndex == index + 1
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(color)
                        .then(
                            if (selected) {
                                Modifier.border(
                                    width = 2.dp,
                                    color = MiuixTheme.colorScheme.onBackground,
                                    shape = CircleShape,
                                )
                            } else {
                                Modifier
                            },
                        )
                        .clickable { onSelect(index + 1) },
                )
            }
        }
    }
}

private fun Modifier.demoSettingsCard(): Modifier = this
    .padding(horizontal = 12.dp)
    .padding(bottom = 12.dp)
