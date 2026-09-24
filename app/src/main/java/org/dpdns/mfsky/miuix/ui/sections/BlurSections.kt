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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val blurDemoBrush = Brush.linearGradient(
    listOf(
        Color(0xFF3A6EA5),
        Color(0xFF6A5ACD),
        Color(0xFFD2691E),
        Color(0xFF2E8B57),
    ),
)

private val blendOptions = listOf(
    "None" to emptyList(),
    "Info Thin Light" to ColorBlendToken.Info_Thin_Light,
    "Info Regular Light" to ColorBlendToken.Info_Regular_Light,
    "Colored Thin Light" to ColorBlendToken.Colored_Thin_Light,
    "Colored Regular Light" to ColorBlendToken.Colored_Regular_Light,
    "Pured Regular Light" to ColorBlendToken.Pured_Regular_Light,
)

private val progressiveDirections = listOf(
    "Top" to ProgressiveBlur.Top,
    "Bottom" to ProgressiveBlur.Bottom,
    "Left" to ProgressiveBlur.Left,
    "Right" to ProgressiveBlur.Right,
)

fun LazyListScope.textureBlurSection() {
    item(key = "blur") {
        SmallTitle(text = "Texture Blur")
        if (!isRuntimeShaderSupported()) {
            Card(modifier = Modifier.demoCard()) {
                Text(
                    text = "当前设备不支持运行时着色器，模糊效果不可用",
                    modifier = Modifier.padding(16.dp),
                    style = MiuixTheme.textStyles.body2,
                )
            }
            return@item
        }
        val backdrop = rememberLayerBackdrop()
        var blurRadius by remember { mutableFloatStateOf(BlurDefaults.BlurRadius) }
        var blendIndex by remember { mutableIntStateOf(0) }
        val blendNames = remember { blendOptions.map { it.first } }

        Card(modifier = Modifier.demoCard()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .layerBackdrop(backdrop)
                            .background(blurDemoBrush),
                    ) {
                        Text(
                            text = "背景内容",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(16.dp),
                            style = MiuixTheme.textStyles.title3,
                            color = Color.White,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(0.6f)
                            .height(120.dp)
                            .textureBlur(
                                backdrop = backdrop,
                                shape = RoundedCornerShape(20.dp),
                                blurRadius = blurRadius,
                                noiseCoefficient = BlurDefaults.NoiseCoefficient,
                                colors = BlurDefaults.blurColors(
                                    blendColors = blendOptions[blendIndex].second,
                                ),
                            ),
                    )
                }
                SliderPreference(
                    title = "模糊半径",
                    value = blurRadius / BlurDefaults.MaxBlurRadius,
                    onValueChange = { blurRadius = it * BlurDefaults.MaxBlurRadius },
                    valueText = "${blurRadius.toInt()}",
                    modifier = Modifier.padding(top = 8.dp),
                )
                OverlayDropdownPreference(
                    title = "混合色",
                    items = blendNames,
                    selectedIndex = blendIndex,
                    onSelectedIndexChange = { blendIndex = it },
                )
            }
        }
    }
}

fun LazyListScope.progressiveBlurSection() {
    item(key = "progressiveblur") {
        SmallTitle(text = "Progressive Blur")
        if (!isRuntimeShaderSupported()) {
            Card(modifier = Modifier.demoCard()) {
                Text(
                    text = "当前设备不支持运行时着色器，模糊效果不可用",
                    modifier = Modifier.padding(16.dp),
                    style = MiuixTheme.textStyles.body2,
                )
            }
            return@item
        }
        val backdrop = rememberLayerBackdrop()
        var blurRadius by remember { mutableFloatStateOf(BlurDefaults.BlurRadius) }
        var startFraction by remember { mutableFloatStateOf(0f) }
        var endFraction by remember { mutableFloatStateOf(1f) }
        var curve by remember { mutableFloatStateOf(1f) }
        var directionIndex by remember { mutableIntStateOf(0) }
        var blendIndex by remember { mutableIntStateOf(0) }
        val directionNames = remember { progressiveDirections.map { it.first } }
        val blendNames = remember { blendOptions.map { it.first } }
        val gradient = remember(directionIndex, startFraction, endFraction, curve) {
            progressiveDirections[directionIndex].second.copy(
                startFraction = startFraction,
                endFraction = endFraction,
                curve = curve,
            )
        }

        Card(modifier = Modifier.demoCard()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                            .layerBackdrop(backdrop)
                            .background(blurDemoBrush),
                    ) {
                        Text(
                            text = "渐进模糊\n${directionNames[directionIndex]} | ${blurRadius.toInt()}",
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            style = MiuixTheme.textStyles.title3,
                            color = Color.White,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .progressiveTextureBlur(
                                backdrop = backdrop,
                                shape = RoundedCornerShape(0.dp),
                                blurRadius = blurRadius,
                                gradient = gradient,
                                noiseCoefficient = BlurDefaults.ProgressiveNoiseCoefficient,
                                colors = BlurDefaults.blurColors(
                                    blendColors = blendOptions[blendIndex].second,
                                ),
                            ),
                    )
                }
                SliderPreference(
                    title = "模糊半径",
                    value = blurRadius / BlurDefaults.MaxBlurRadius,
                    onValueChange = { blurRadius = it * BlurDefaults.MaxBlurRadius },
                    valueText = "${blurRadius.toInt()}",
                    modifier = Modifier.padding(top = 8.dp),
                )
                SliderPreference(
                    title = "起始位置",
                    value = startFraction,
                    onValueChange = { startFraction = it },
                    valueText = "%.2f".format(startFraction),
                )
                SliderPreference(
                    title = "结束位置",
                    value = endFraction,
                    onValueChange = { endFraction = it },
                    valueText = "%.2f".format(endFraction),
                )
                SliderPreference(
                    title = "曲线",
                    value = curve / 4f,
                    onValueChange = { curve = it * 4f },
                    valueText = "%.2f".format(curve),
                )
                OverlayDropdownPreference(
                    title = "方向",
                    items = directionNames,
                    selectedIndex = directionIndex,
                    onSelectedIndexChange = { directionIndex = it },
                )
                OverlayDropdownPreference(
                    title = "混合色",
                    items = blendNames,
                    selectedIndex = blendIndex,
                    onSelectedIndexChange = { blendIndex = it },
                )
            }
        }
    }
}
