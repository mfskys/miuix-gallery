// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Add
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.Info
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Search
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.iconSection() {
    item(key = "icon") {
        SmallTitle(text = "Icon")
        Card(modifier = Modifier.demoCard()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = MiuixIcons.Home, contentDescription = "首页")
                Icon(imageVector = MiuixIcons.Settings, contentDescription = "设置")
                Icon(imageVector = MiuixIcons.Search, contentDescription = "搜索")
                Icon(
                    imageVector = MiuixIcons.Info,
                    contentDescription = "信息",
                    tint = MiuixTheme.colorScheme.primary,
                )
                Icon(
                    imageVector = MiuixIcons.Add,
                    contentDescription = "新增",
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}

fun LazyListScope.iconButtonSection(snackbarHostState: SnackbarHostState) {
    item(key = "iconbutton") {
        SmallTitle(text = "IconButton")
        Card(modifier = Modifier.demoCard()) {
            val scope = rememberCoroutineScope()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了「新增」") } },
                ) {
                    Icon(imageVector = MiuixIcons.Add, contentDescription = "新增")
                }
                IconButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了「编辑」") } },
                ) {
                    Icon(imageVector = MiuixIcons.Edit, contentDescription = "编辑")
                }
                IconButton(onClick = {}, enabled = false) {
                    Icon(imageVector = MiuixIcons.More, contentDescription = "更多")
                }
                IconButton(
                    onClick = { scope.launch { snackbarHostState.showSnackbar("点击了「设置」") } },
                    backgroundColor = MiuixTheme.colorScheme.surfaceContainerHigh,
                ) {
                    Icon(imageVector = MiuixIcons.Settings, contentDescription = "设置")
                }
            }
            Text(
                text = "第三个为禁用态；最后一个使用了自定义背景色",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}
