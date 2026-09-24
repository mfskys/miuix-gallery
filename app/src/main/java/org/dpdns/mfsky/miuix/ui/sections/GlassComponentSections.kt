// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.All
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val demoBackdropBrush = Brush.linearGradient(
    listOf(
        Color(0xFF6A5ACD),
        Color(0xFF20B2AA),
        Color(0xFFFF8C00),
    ),
)

fun LazyListScope.glassTabRowSection() {
    item(key = "glasstabrow") {
        SmallTitle(text = "GlassTabRow")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            var selected by remember { mutableIntStateOf(0) }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp))
                        .layerBackdrop(backdrop)
                        .background(demoBackdropBrush),
                )
                GlassTabRow(
                    tabs = listOf("首页", "组件", "主题"),
                    selectedIndex = selected,
                    onSelect = { selected = it },
                    backdrop = backdrop,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp),
                )
            }
        }
    }
}

fun LazyListScope.glassNavigationBarSection() {
    item(key = "glassnavigationbar") {
        SmallTitle(text = "GlassNavigationBar")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            var selected by remember { mutableIntStateOf(0) }
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
                        .background(demoBackdropBrush),
                )
                GlassNavigationBar(
                    items = listOf(
                        GlassNavigationItem(icon = MiuixIcons.Home, label = "首页"),
                        GlassNavigationItem(icon = MiuixIcons.GridView, label = "组件"),
                        GlassNavigationItem(icon = MiuixIcons.ListView, label = "列表"),
                        GlassNavigationItem(icon = MiuixIcons.All, label = "全部"),
                    ),
                    selectedIndex = selected,
                    onSelect = { selected = it },
                    backdrop = backdrop,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                )
            }
        }
    }
}

fun LazyListScope.glassTopAppBarSection() {
    item(key = "glasstopappbar") {
        SmallTitle(text = "GlassTopAppBar")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            val scrollBehavior = MiuixScrollBehavior()
            val listState = rememberLazyListState()
            val density = LocalDensity.current
            // The bar expands to its large-title height and shrinks again while the list scrolls,
            // so the list clears the tallest height it ever reports — padding it with the live
            // height would drag the list along with the collapse.
            var topBarHeightPx by remember { mutableIntStateOf(0) }
            val topBarPadding = if (topBarHeightPx > 0) {
                with(density) { topBarHeightPx.toDp() }
            } else {
                112.dp
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
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
                                text = "玻璃顶栏下的列表项 $index",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }
                GlassTopAppBar(
                    title = "Glass",
                    largeTitle = "玻璃顶栏",
                    isContentScrolled = listState.canScrollBackward,
                    backdrop = backdrop,
                    scrollBehavior = scrollBehavior,
                    defaultWindowInsetsPadding = false,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .onSizeChanged { if (it.height > topBarHeightPx) topBarHeightPx = it.height },
                )
            }
        }
    }
}

fun LazyListScope.glassPopupSection() {
    item(key = "glasspopup") {
        SmallTitle(text = "GlassPopup")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            val anchor = rememberGlassPopupAnchor()
            var showMenu by remember { mutableStateOf(false) }
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
                        .background(demoBackdropBrush),
                )
                GlassIconButton(
                    onClick = { showMenu = true },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .glassPopupAnchor(anchor, cornerRadius = 24.dp),
                ) {
                    Icon(
                        imageVector = MiuixIcons.More,
                        contentDescription = "更多",
                        modifier = Modifier.glassPopupAnchorContent(anchor),
                    )
                }
                GlassTransformPopup(
                    show = showMenu,
                    onDismissRequest = { showMenu = false },
                    anchor = anchor,
                    backdrop = backdrop,
                    anchorContent = {
                        Icon(imageVector = MiuixIcons.More, contentDescription = null)
                    },
                ) {
                    GlassPopupItem(text = "重命名", onClick = { showMenu = false })
                    GlassPopupItem(text = "复制", onClick = { showMenu = false })
                    GlassPopupItem(text = "删除", onClick = { showMenu = false })
                }
            }
            Text(
                text = "点击圆形玻璃按钮，面板会从按钮处生长出来",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}


fun LazyListScope.glassSegmentedTabRowSection() {
    item(key = "glasssegmentedtabrow") {
        SmallTitle(text = "tab 栏（页签）· 隐私与安全页的真实结构")
        Card(modifier = Modifier.demoCard()) {
            val backdrop = rememberLayerBackdrop()
            val scrollBehavior = MiuixScrollBehavior()
            var selected by remember { mutableIntStateOf(0) }
            Box(modifier = Modifier.fillMaxWidth().height(320.dp)) {
                Box(modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)).layerBackdrop(backdrop).background(demoBackdropBrush)) {
                    Text(
                        text = "页面内容从页签下面滚过去",
                        modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                        style = MiuixTheme.textStyles.body2,
                    )
                }
                GlassTopAppBar(
                    title = "隐私与安全",
                    largeTitle = if (selected == 0) "隐私" else "安全",
                    subtitle = "页签在标题栏内部的下方",
                    backdrop = backdrop,
                    scrollBehavior = scrollBehavior,
                    defaultWindowInsetsPadding = false,
                    bottomContent = {
                        GlassSegmentedTabRow(
                            tabs = listOf("隐私", "安全"),
                            selectedIndex = selected,
                            onSelect = { selected = it },
                            backdrop = backdrop,
                            indicatorColor = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
            Text(
                text = "页签条属于标题栏：放在 GlassTopAppBar 的 bottomContent，横跨整页、紧贴标题下方。",
                modifier = Modifier.padding(16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}
