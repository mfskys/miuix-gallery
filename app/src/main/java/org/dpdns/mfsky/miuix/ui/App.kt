// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import org.dpdns.mfsky.miuix.ui.catalog.Category
import org.dpdns.mfsky.miuix.ui.catalog.componentCatalog
import org.dpdns.mfsky.miuix.ui.screens.componentContent
import org.dpdns.mfsky.miuix.ui.screens.homeContent
import org.dpdns.mfsky.miuix.ui.screens.settingsContent
import org.dpdns.mfsky.miuix.ui.settings.LocalAppSettings
import org.dpdns.mfsky.miuix.ui.showcase.showcaseCatalog
import org.dpdns.mfsky.miuix.ui.showcase.showcaseContent
import org.dpdns.mfsky.miuix.ui.showcase.showcaseListContent
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.Play
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical

private const val DEMO_ROUTE_PREFIX = "demo:"

@Composable
fun MiuixCatalogApp() {
    val settings = LocalAppSettings.current
    var categoryIndex by rememberSaveable { mutableIntStateOf(0) }
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val backStackIds = rememberSaveable(
        saver = listSaver<SnapshotStateList<String>, String>(
            save = { it.toList() },
            restore = { it.toMutableStateList() },
        ),
    ) { mutableStateListOf<String>() }

    val category = Category.entries.getOrElse(categoryIndex) { Category.BASIC }
    val stackTop = backStackIds.lastOrNull()
    val currentComponent = stackTop?.let { id -> componentCatalog.firstOrNull { it.id == id } }
    val currentDemo = stackTop
        ?.takeIf { it.startsWith(DEMO_ROUTE_PREFIX) }
        ?.let { id -> showcaseCatalog.firstOrNull { it.id == id.removePrefix(DEMO_ROUTE_PREFIX) } }
    val routeId = currentComponent?.id ?: currentDemo?.let { DEMO_ROUTE_PREFIX + it.id }
    var navIndex by remember { mutableIntStateOf(categoryIndex) }
    val snackbarHostState = remember { SnackbarHostState() }
    val backdrop = rememberLayerBackdrop()
    val homeListState = rememberLazyListState()
    val detailListState = rememberLazyListState()
    val listState = if (routeId == null) homeListState else detailListState

    // `MiuixScrollBehavior` remembers its state keyed on this lambda, so it has to be a stable
    // instance: a fresh `{ ... }` on every recomposition would hand back a brand new behaviour and
    // snap the bar back to its expanded state.
    val canScroll: () -> Boolean = remember(listState) {
        { listState.canScrollForward || listState.canScrollBackward }
    }
    val scrollBehavior = MiuixScrollBehavior(canScroll = canScroll)
    val density = LocalDensity.current
    val statusBarInset = with(density) { WindowInsets.statusBars.getTop(this).toDp() }
    val navigationBarInset = with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }

    // The bar really shrinks from its large-title height down to `CollapsedHeight` while the page
    // scrolls, so its live height is not something the content can be padded with: doing that drags
    // the list along with the collapse and recomposes this whole screen on every frame of it. Only
    // the largest height the bar ever reports — its expanded one — is stable, and it is re-measured
    // per destination so that a long title does not leave a gap on a short one.
    var expandedTopBarHeight by rememberSaveable(routeId) { mutableIntStateOf(0) }
    val topBarHeight = if (expandedTopBarHeight > 0) {
        with(density) { expandedTopBarHeight.toDp() }
    } else {
        statusBarInset + 112.dp
    }
    val bottomBarMargin = (navigationBarInset + 12.dp).coerceAtLeast(24.dp)
    // The blur is a setting, and it is only meaningful where the runtime shader exists.
    val blurSupported = isRuntimeShaderSupported() && settings.enableBlur
    // With the blur off a transparent bar would let the list scroll straight through it, so the
    // bar falls back to the opaque surface colour.
    val topBarColor = if (blurSupported) Color.Transparent else MiuixTheme.colorScheme.surface

    val homeBar = when (category) {
        Category.BASIC -> Triple("MIUIX", "MIUIX 组件库", "miuix")
        Category.EXTENDED -> Triple("扩展", "扩展与主题", "偏好设置 · 弹层 · 主题 · 玻璃")
        Category.DEMO -> Triple("演示", "演示页面", "用 miuix 拼出来的完整页面")
        Category.SETTINGS -> Triple("设置", "设置", "外观 · 效果 · 关于")
    }
    val topTitle = currentComponent?.name
        ?: currentDemo?.title
        ?: homeBar.first
    val topLargeTitle = currentComponent?.name
        ?: currentDemo?.title
        ?: homeBar.second
    val topSubtitle = currentComponent?.summary
        ?: currentDemo?.summary
        ?: homeBar.third

    BackHandler(enabled = routeId != null) {
        backStackIds.removeLastOrNull()
    }

    LaunchedEffect(categoryIndex) {
        navIndex = categoryIndex
        homeListState.scrollToItem(0)
    }

    LaunchedEffect(searchQuery) {
        homeListState.scrollToItem(0)
    }

    LaunchedEffect(routeId) {
        scrollBehavior.state.heightOffset = 0f
        scrollBehavior.state.contentOffset = 0f
        if (routeId != null) {
            detailListState.scrollToItem(0)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(state = snackbarHostState)
        },
        contentWindowInsets = WindowInsets.systemBars
            .union(WindowInsets.displayCutout)
            .only(WindowInsetsSides.Horizontal),
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .layerBackdrop(backdrop)
                    .background(MiuixTheme.colorScheme.surface),
            ) {
                // The floating glass bar only exists on the home screen, so a component page must
                // not reserve room for it — but it still has to clear the navigation bar, because
                // the insets above are horizontal-only.
                val contentPadding = PaddingValues(
                    top = topBarHeight,
                    bottom = if (routeId == null) {
                        bottomBarMargin + GlassNavigationBarDefaults.Height + 12.dp
                    } else {
                        24.dp + navigationBarInset
                    },
                )
                val listModifier = Modifier
                    .fillMaxSize()
                    .overScrollVertical()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .padding(padding)

                if (routeId == null) {
                    LazyColumn(
                        state = homeListState,
                        modifier = listModifier,
                        contentPadding = contentPadding,
                    ) {
                        if (category == Category.SETTINGS) {
                            settingsContent()
                        } else if (category == Category.DEMO) {
                            showcaseListContent(
                                onOpen = { entry ->
                                    val route = DEMO_ROUTE_PREFIX + entry.id
                                    if (backStackIds.lastOrNull() != route) {
                                        backStackIds.add(route)
                                    }
                                },
                            )
                        } else {
                            homeContent(
                                category = category,
                                query = searchQuery,
                                onQueryChange = { searchQuery = it },
                                onOpen = { entry ->
                                    // Guard against a double tap pushing the same page twice, which
                                    // would then need two back presses to leave.
                                    if (backStackIds.lastOrNull() != entry.id) {
                                        backStackIds.add(entry.id)
                                    }
                                },
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        state = detailListState,
                        modifier = listModifier,
                        contentPadding = contentPadding,
                    ) {
                        val component = currentComponent
                        val demo = currentDemo
                        if (component != null) {
                            componentContent(
                                entry = component,
                                snackbarHostState = snackbarHostState,
                            )
                        } else if (demo != null) {
                            showcaseContent(
                                entry = demo,
                                snackbarHostState = snackbarHostState,
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
            ) {
                if (blurSupported) {
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .progressiveTextureBlur(
                                backdrop = backdrop,
                                shape = RectangleShape,
                                blurRadius = BlurDefaults.BlurRadius,
                                gradient = ProgressiveBlur.Top,
                                noiseCoefficient = BlurDefaults.ProgressiveNoiseCoefficient,
                                colors = BlurDefaults.blurColors(),
                            ),
                    )
                }
                TopAppBar(
                    title = topTitle,
                    largeTitle = topLargeTitle,
                    subtitle = topSubtitle,
                    color = topBarColor,
                    scrollBehavior = scrollBehavior,
                    modifier = Modifier.onSizeChanged {
                        if (it.height > expandedTopBarHeight) expandedTopBarHeight = it.height
                    },
                    navigationIcon = {
                        if (routeId != null) {
                            IconButton(onClick = { backStackIds.removeLastOrNull() }) {
                                Icon(
                                    imageVector = MiuixIcons.Back,
                                    contentDescription = "返回",
                                )
                            }
                        }
                    },
                )
            }

            if (routeId == null) {
                if (settings.useGlassNavigationBar) {
                    GlassNavigationBar(
                        items = Category.entries.map {
                            GlassNavigationItem(icon = iconFor(it), label = it.shortLabel)
                        },
                        selectedIndex = navIndex,
                        onSelect = { navIndex = it },
                        onCommit = {
                            categoryIndex = it
                            // While a query is active the list shows search results, so a category
                            // switch would otherwise look like nothing happened.
                            searchQuery = ""
                        },
                        backdrop = backdrop,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 24.dp, end = 24.dp, bottom = bottomBarMargin),
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(),
                    ) {
                        NavigationBar {
                            Category.entries.forEachIndexed { index, entry ->
                                NavigationBarItem(
                                    selected = navIndex == index,
                                    onClick = {
                                        navIndex = index
                                        categoryIndex = index
                                        searchQuery = ""
                                    },
                                    icon = iconFor(entry),
                                    label = entry.shortLabel,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun iconFor(category: Category): ImageVector =
    when (category) {
        Category.BASIC -> MiuixIcons.GridView
        Category.EXTENDED -> MiuixIcons.ListView
        Category.DEMO -> MiuixIcons.Play
        Category.SETTINGS -> MiuixIcons.Settings
    }
