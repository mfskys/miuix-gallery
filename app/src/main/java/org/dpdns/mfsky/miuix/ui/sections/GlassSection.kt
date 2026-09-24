// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassMaterial
import top.yukonga.miuix.kmp.glass.GlassMaterials
import top.yukonga.miuix.kmp.glass.GlassShape
import top.yukonga.miuix.kmp.glass.GlassShadows
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.GlassStyles
import top.yukonga.miuix.kmp.glass.glassPanel
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val glassMaterials: List<Pair<String, GlassMaterial>> =
    listOf(
        "PuredThin Light" to GlassMaterials.PuredThinGlassLight,
        "PuredThin Dark" to GlassMaterials.PuredThinGlassDark,
        "PopupView Light" to GlassMaterials.PopupViewGlassLight,
        "PopupView Dark" to GlassMaterials.PopupViewGlassDark,
        "ActionBarMask Light" to GlassMaterials.ActionBarMaskLight,
        "ActionBarMask Dark" to GlassMaterials.ActionBarMaskDark,
    )

private val glassShadows: List<Pair<String, top.yukonga.miuix.kmp.glass.GlassShadow?>> =
    listOf(
        "无" to null,
        "低" to GlassShadows.Low,
        "中" to GlassShadows.Regular,
        "高" to GlassShadows.High,
        "极高" to GlassShadows.ExtraHigh,
    )

fun LazyListScope.glassSection() {
    item(key = "glass") {
        val backdrop = rememberLayerBackdrop()
        var materialIndex by remember { mutableIntStateOf(0) }
        var shadowIndex by remember { mutableIntStateOf(2) }
        var darkStroke by remember { mutableStateOf(false) }
        var shading by remember { mutableStateOf(true) }

        SmallTitle(text = "Glass")
        Card(modifier = Modifier.demoCard()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .layerBackdrop(backdrop)
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        Color(0xFF6A5ACD),
                                        Color(0xFF20B2AA),
                                        Color(0xFFFF8C00),
                                    ),
                                ),
                            ),
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(width = 150.dp, height = 92.dp)
                            .glassPanel(
                                backdrop = backdrop,
                                shape = GlassShape(24.dp),
                                style = GlassStyles.CommonMediumRegularLowLight,
                                material = glassMaterials[materialIndex].second,
                                stroke = if (darkStroke) GlassStrokes.BigDark else GlassStrokes.BigLight,
                                shadow = glassShadows[shadowIndex].second,
                                shading = shading,
                            ),
                    )
                }
                Text(
                    text = glassMaterials[materialIndex].first,
                    style = MiuixTheme.textStyles.body2,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    TextButton(
                        text = "材质",
                        onClick = { materialIndex = (materialIndex + 1) % glassMaterials.size },
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(
                        text = glassShadows[shadowIndex].first,
                        onClick = { shadowIndex = (shadowIndex + 1) % glassShadows.size },
                        modifier = Modifier.weight(1f),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    TextButton(
                        text = if (darkStroke) "暗描边" else "亮描边",
                        onClick = { darkStroke = !darkStroke },
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(
                        text = if (shading) "折射开" else "折射关",
                        onClick = { shading = !shading },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
