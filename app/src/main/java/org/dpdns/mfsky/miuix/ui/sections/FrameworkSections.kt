// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.scaffoldSection() {
    item(key = "scaffold") {
        SmallTitle(text = "Scaffold")
        Card(modifier = Modifier.demoCard()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            ) {
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = "内嵌 Scaffold",
                            defaultWindowInsetsPadding = false,
                        )
                    },
                    bottomBar = {
                        var selected by remember { mutableIntStateOf(0) }
                        NavigationBar(showDivider = false) {
                            NavigationBarItem(
                                selected = selected == 0,
                                onClick = { selected = 0 },
                                icon = MiuixIcons.Home,
                                label = "首页",
                            )
                            NavigationBarItem(
                                selected = selected == 1,
                                onClick = { selected = 1 },
                                icon = MiuixIcons.GridView,
                                label = "组件",
                            )
                        }
                    },
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp),
                    ) {
                        Text(
                            text = "Scaffold 提供 topBar / bottomBar / snackbarHost / 浮层宿主。",
                            style = MiuixTheme.textStyles.body2,
                        )
                        Text(
                            text = "本应用的顶栏是浮在内容之上的透明层，底栏是悬浮的玻璃胶囊。",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MiuixTheme.textStyles.footnote1,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                        )
                    }
                }
            }
        }
    }
}

fun LazyListScope.topAppBarSection() {
    item(key = "topappbar") {
        SmallTitle(text = "TopAppBar")
        Card(modifier = Modifier.demoCard()) {
            Column(modifier = Modifier.fillMaxWidth()) {
                SmallTopAppBar(
                    title = "SmallTopAppBar",
                    subtitle = "固定高度的小标题栏",
                    defaultWindowInsetsPadding = false,
                )
                HorizontalDivider()
                TopAppBar(
                    title = "标题",
                    largeTitle = "大标题顶栏",
                    subtitle = "折叠后只显示 title（此处为静态展示）",
                    defaultWindowInsetsPadding = false,
                )
                HorizontalDivider()
                TopAppBar(
                    title = "带底部的顶栏",
                    largeTitle = "带 bottomContent",
                    defaultWindowInsetsPadding = false,
                    bottomContent = {
                        Text(
                            text = "bottomContent 区域",
                            modifier = Modifier.padding(start = 26.dp, bottom = 8.dp),
                            style = MiuixTheme.textStyles.footnote1,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                        )
                    },
                )
            }
            Text(
                text = "本应用的顶栏在 TopAppBar 之上叠了一层渐进模糊，并让它保持透明。",
                modifier = Modifier.padding(16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}
