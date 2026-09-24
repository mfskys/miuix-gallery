// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.FloatingToolbar
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Add
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.floatingActionButtonSection(snackbarHostState: SnackbarHostState) {
    item(key = "floatingactionbutton") {
        SmallTitle(text = "FloatingActionButton")
        Card(modifier = Modifier.demoCard()) {
            val scope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(16.dp),
            ) {
                FloatingActionButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了「新增」") } },
                    modifier = Modifier.align(Alignment.Center),
                ) {
                    Icon(imageVector = MiuixIcons.Add, contentDescription = "新增")
                }
                FloatingActionButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了「编辑」") } },
                    modifier = Modifier.align(Alignment.CenterEnd),
                ) {
                    Icon(imageVector = MiuixIcons.Edit, contentDescription = "编辑")
                }
            }
        }
    }
}

fun LazyListScope.floatingToolbarSection(snackbarHostState: SnackbarHostState) {
    item(key = "floatingtoolbar") {
        SmallTitle(text = "FloatingToolbar")
        Card(modifier = Modifier.demoCard()) {
            val scope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                contentAlignment = Alignment.Center,
            ) {
                FloatingToolbar(showDivider = true) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        IconButton(
                            onClick = { scope.launch { snackbarHostState.showSnackbar("工具栏：编辑") } },
                        ) {
                            Icon(imageVector = MiuixIcons.Edit, contentDescription = "编辑")
                        }
                        IconButton(
                            onClick = { scope.launch { snackbarHostState.showSnackbar("工具栏：新增") } },
                        ) {
                            Icon(imageVector = MiuixIcons.Add, contentDescription = "新增")
                        }
                        IconButton(
                            onClick = { scope.launch { snackbarHostState.showSnackbar("工具栏：删除") } },
                        ) {
                            Icon(imageVector = MiuixIcons.Delete, contentDescription = "删除")
                        }
                        IconButton(
                            onClick = { scope.launch { snackbarHostState.showSnackbar("工具栏：更多") } },
                        ) {
                            Icon(imageVector = MiuixIcons.More, contentDescription = "更多")
                        }
                    }
                }
            }
            Text(
                text = "悬浮工具栏，可带分隔线",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.pullToRefreshSection() {
    item(key = "pulltorefresh") {
        SmallTitle(text = "PullToRefresh")
        Card(modifier = Modifier.demoCard()) {
            var refreshing by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
            ) {
                PullToRefresh(
                    isRefreshing = refreshing,
                    onRefresh = {
                        refreshing = true
                        scope.launch {
                            delay(1500)
                            refreshing = false
                        }
                    },
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(16) { index ->
                            Text(
                                text = "下拉刷新列表项 $index",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }
            }
        }
    }
}
