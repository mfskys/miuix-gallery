// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BlurTopAppBar
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.preference.RangeSliderPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.rangeSliderPreferenceSection() {
    item(key = "rangesliderpreference") {
        SmallTitle(text = "RangeSliderPreference")
        Card(modifier = Modifier.demoCard()) {
            var range by remember { mutableStateOf(0.2f..0.8f) }
            RangeSliderPreference(
                title = "范围选择",
                value = range,
                onValueChange = { range = it },
                valueText = "%.0f - %.0f".format(range.start * 100, range.endInclusive * 100),
            )
            RangeSliderPreference(
                title = "带副标题",
                value = range,
                onValueChange = { range = it },
                summary = "拖动两端选择区间",
            )
            RangeSliderPreference(
                title = "禁用状态",
                value = 0.3f..0.7f,
                onValueChange = {},
                enabled = false,
            )
        }
    }
}

fun LazyListScope.rangeSliderSection() {
    item(key = "rangeslider") {
        SmallTitle(text = "RangeSlider")
        Card(modifier = Modifier.demoCard()) {
            var range by remember { mutableStateOf(0.25f..0.75f) }
            Box(modifier = Modifier.padding(16.dp)) {
                RangeSlider(
                    value = range,
                    onValueChange = { range = it },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Text(
                text = "当前区间：%.0f - %.0f".format(range.start * 100, range.endInclusive * 100),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.verticalSliderSection() {
    item(key = "verticalslider") {
        SmallTitle(text = "VerticalSlider")
        Card(modifier = Modifier.demoCard()) {
            var left by remember { mutableFloatStateOf(0.3f) }
            var right by remember { mutableFloatStateOf(0.7f) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(48.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VerticalSlider(
                    value = left,
                    onValueChange = { left = it },
                )
                VerticalSlider(
                    value = right,
                    onValueChange = { right = it },
                    reverseDirection = true,
                )
            }
            Text(
                text = "左：正向  右：reverseDirection = true",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.floatingNavigationBarSection() {
    item(key = "floatingnavigationbar") {
        SmallTitle(text = "FloatingNavigationBar")
        Card(modifier = Modifier.demoCard()) {
            var selected by remember { mutableIntStateOf(0) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center,
            ) {
                FloatingNavigationBar(showDivider = false) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FloatingNavigationBarItem(
                            selected = selected == 0,
                            onClick = { selected = 0 },
                            icon = MiuixIcons.Home,
                            label = "首页",
                        )
                        FloatingNavigationBarItem(
                            selected = selected == 1,
                            onClick = { selected = 1 },
                            icon = MiuixIcons.GridView,
                            label = "组件",
                        )
                        FloatingNavigationBarItem(
                            selected = selected == 2,
                            onClick = { selected = 2 },
                            icon = MiuixIcons.ListView,
                            label = "列表",
                        )
                        FloatingNavigationBarItem(
                            selected = selected == 3,
                            onClick = { selected = 3 },
                            icon = MiuixIcons.Settings,
                            label = "设置",
                        )
                    }
                }
            }
        }
    }
}

fun LazyListScope.blurTopAppBarSection() {
    item(key = "blurtopappbar") {
        SmallTitle(text = "BlurTopAppBar")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            val scrollBehavior = MiuixScrollBehavior()
            val listState = rememberLazyListState()
            val density = LocalDensity.current
            // The bar expands to its large-title height and shrinks again while the list scrolls,
            // so the list clears the tallest height it ever reports — padding it with the live
            // height would drag the list along with the collapse. The same measurement drives the
            // alpha below.
            var topBarHeightPx by remember { mutableIntStateOf(0) }
            val topBarPadding = if (topBarHeightPx > 0) {
                with(density) { topBarHeightPx.toDp() }
            } else {
                112.dp
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .layerBackdrop(backdrop)
                        .background(MiuixTheme.colorScheme.surface),
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        contentPadding = PaddingValues(top = topBarPadding),
                    ) {
                        items((0 until 20).toList()) { index ->
                            Text(
                                text = "模糊顶栏下的列表项 $index",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }
                BlurTopAppBar(
                    title = "BlurTopAppBar",
                    largeTitle = "大标题随滚动淡出",
                    largeTitleBlurRadius = 20.dp,
                    scrollBehavior = scrollBehavior,
                    defaultWindowInsetsPadding = false,
                    // The inlined shim cannot blur the large title (upstream forwards to a private
                    // layout), so the fade is driven by how far the bar has travelled instead — it
                    // reaches zero once the bar has collapsed by its own height.
                    titleAlpha = {
                        (1f + scrollBehavior.state.heightOffset / topBarHeightPx.coerceAtLeast(1))
                            .coerceIn(0f, 1f)
                    },
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .onSizeChanged { if (it.height > topBarHeightPx) topBarHeightPx = it.height },
                )
            }
        }
    }
}
