// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.menu.OverlayDropdownMenu
import top.yukonga.miuix.kmp.menu.OverlayIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.menu.WindowDropdownMenu
import top.yukonga.miuix.kmp.overlay.OverlayCascadingListPopup
import top.yukonga.miuix.kmp.overlay.OverlayListPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.listPopupSection() {
    item(key = "listpopup") {
        SmallTitle(text = "ListPopup")
        Card(modifier = Modifier.demoCard()) {
            var show by remember { mutableStateOf(false) }
            Column(modifier = Modifier.padding(16.dp)) {
                Button(onClick = { show = true }) {
                    Text(text = "显示列表弹窗")
                }
            }
            OverlayListPopup(
                show = show,
                onDismissRequest = { show = false },
                enableWindowDim = true,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "列表弹窗内容", style = MiuixTheme.textStyles.title4)
                    Text(
                        text = "点击弹窗外部区域可关闭",
                        modifier = Modifier.padding(top = 8.dp),
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
            }
        }
    }
}

fun LazyListScope.cascadingListPopupSection() {
    item(key = "cascadinglistpopup") {
        SmallTitle(text = "CascadingListPopup")
        Card(modifier = Modifier.demoCard()) {
            var show by remember { mutableStateOf(false) }
            var first by remember { mutableIntStateOf(0) }
            var second by remember { mutableIntStateOf(0) }
            val entries = remember(first, second) {
                listOf(
                    DropdownEntry(
                        items = listOf("分组 A-1", "分组 A-2").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = first == index,
                                onClick = { first = index },
                            )
                        },
                    ),
                    DropdownEntry(
                        items = listOf("分组 B-1", "分组 B-2", "分组 B-3").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = second == index,
                                onClick = { second = index },
                            )
                        },
                    ),
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "已选：A-${first + 1} / B-${second + 1}",
                    style = MiuixTheme.textStyles.body2,
                )
                Button(
                    onClick = { show = true },
                    modifier = Modifier.padding(top = 12.dp),
                ) {
                    Text(text = "显示级联弹窗")
                }
            }
            OverlayCascadingListPopup(
                show = show,
                entries = entries,
                onDismissRequest = { show = false },
            )
        }
    }
}

fun LazyListScope.dropdownMenuSection() {
    item(key = "dropdownmenu") {
        SmallTitle(text = "DropdownMenu")
        Card(modifier = Modifier.demoCard()) {
            var overlayExpanded by remember { mutableStateOf(false) }
            var windowExpanded by remember { mutableStateOf(false) }
            var overlaySelected by remember { mutableIntStateOf(0) }
            var windowSelected by remember { mutableIntStateOf(0) }
            val items = remember { listOf("选项 A", "选项 B", "选项 C", "选项 D") }
            val overlayEntry = remember(overlaySelected) {
                DropdownEntry(
                    items = items.mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = overlaySelected == index,
                            onClick = { overlaySelected = index },
                        )
                    },
                )
            }
            val windowEntry = remember(windowSelected) {
                DropdownEntry(
                    items = items.mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = windowSelected == index,
                            onClick = { windowSelected = index },
                        )
                    },
                )
            }
            OverlayDropdownMenu(
                entry = overlayEntry,
                title = "DropdownMenu (Overlay)",
                summary = if (overlayExpanded) "展开中" else "已选：${items[overlaySelected]}",
                onExpandedChange = { overlayExpanded = it },
            )
            WindowDropdownMenu(
                entry = windowEntry,
                title = "DropdownMenu (Window)",
                summary = if (windowExpanded) "展开中" else "已选：${items[windowSelected]}",
                onExpandedChange = { windowExpanded = it },
            )
        }
    }
}

fun LazyListScope.iconCascadingDropdownMenuSection() {
    item(key = "iconcascadingdropdownmenu") {
        SmallTitle(text = "IconCascadingDropdownMenu")
        Card(modifier = Modifier.demoCard()) {
            var first by remember { mutableIntStateOf(0) }
            var second by remember { mutableIntStateOf(0) }
            val entries = remember(first, second) {
                listOf(
                    DropdownEntry(
                        items = listOf("菜单项 A-1", "菜单项 A-2").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = first == index,
                                onClick = { first = index },
                            )
                        },
                    ),
                    DropdownEntry(
                        items = listOf("菜单项 B-1", "菜单项 B-2").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = second == index,
                                onClick = { second = index },
                            )
                        },
                    ),
                )
            }
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "当前：A-${first + 1} / B-${second + 1}",
                    style = MiuixTheme.textStyles.body2,
                )
                OverlayIconCascadingDropdownMenu(entries = entries) {
                    Icon(imageVector = MiuixIcons.More, contentDescription = "更多")
                }
            }
        }
    }
}
