// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalDivider
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.surfaceSection(snackbarHostState: SnackbarHostState) {
    item(key = "surface") {
        SmallTitle(text = "Surface")
        Card(modifier = Modifier.demoCard()) {
            val scope = rememberCoroutineScope()
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "默认 Surface", style = MiuixTheme.textStyles.body1)
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    color = MiuixTheme.colorScheme.surfaceContainerHigh,
                    shadowElevation = 8.dp,
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "高容器色 + 投影", style = MiuixTheme.textStyles.body1)
                    }
                }
                Surface(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了可点击 Surface") } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = "可点击 Surface", style = MiuixTheme.textStyles.body1)
                    }
                }
            }
        }
    }
}

fun LazyListScope.dividerSection() {
    item(key = "divider") {
        SmallTitle(text = "Divider")
        Card(modifier = Modifier.demoCard()) {
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Text(
                    text = "上方内容",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MiuixTheme.textStyles.body1,
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                Text(
                    text = "下方内容",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MiuixTheme.textStyles.body1,
                )
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "左侧",
                        modifier = Modifier.padding(start = 16.dp),
                        style = MiuixTheme.textStyles.body2,
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 12.dp),
                    )
                    Text(
                        text = "右侧",
                        style = MiuixTheme.textStyles.body2,
                    )
                }
            }
        }
    }
}

fun LazyListScope.scrollBarSection() {
    item(key = "scrollbar") {
        SmallTitle(text = "ScrollBar")
        Card(modifier = Modifier.demoCard()) {
            val scrollState = rememberScrollState()
            val adapter = rememberScrollBarAdapter(scrollState)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 12.dp)
                        .verticalScroll(scrollState),
                ) {
                    repeat(24) { index ->
                        Text(
                            text = "列表项 $index",
                            modifier = Modifier.padding(vertical = 6.dp),
                            style = MiuixTheme.textStyles.body2,
                        )
                    }
                }
                VerticalScrollBar(
                    adapter = adapter,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                )
            }
        }
    }
}
