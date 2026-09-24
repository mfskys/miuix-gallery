// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.searchBarSection() {
    item(key = "searchbar") {
        SmallTitle(text = "SearchBar")
        Card(modifier = Modifier.demoCard()) {
            var query by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
            val source = remember {
                listOf("顶部栏", "按钮", "卡片", "对话框", "搜索栏", "开关", "滑动条", "标签页")
            }
            val results = if (query.isBlank()) source else source.filter { it.contains(query.trim()) }

            SearchBar(
                inputField = {
                    InputField(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = { expanded = false },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                    )
                },
                onExpandedChange = { expanded = it },
                expanded = expanded,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (results.isEmpty()) {
                        Text(
                            text = "没有匹配「${query.trim()}」的组件",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                        )
                    } else {
                        results.forEach { result ->
                            Text(
                                text = result,
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
