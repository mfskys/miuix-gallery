// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dpdns.mfsky.miuix.ui.catalog.Category
import org.dpdns.mfsky.miuix.ui.catalog.ComponentEntry
import org.dpdns.mfsky.miuix.ui.catalog.entriesOf
import org.dpdns.mfsky.miuix.ui.catalog.searchCatalog
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.homeContent(
    category: Category,
    query: String,
    onQueryChange: (String) -> Unit,
    onOpen: (ComponentEntry) -> Unit,
) {
    item(key = "search-field") {
        var expanded by remember { mutableStateOf(false) }
        InputField(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = { expanded = false },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            label = "搜索组件",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 8.dp, bottom = 4.dp),
        )
    }

    val searching = query.isNotBlank()
    val entries = if (searching) searchCatalog(query) else entriesOf(category)

    item(key = "header-$category") {
        SmallTitle(
            text = if (searching) {
                "搜索结果 · ${entries.size}"
            } else {
                "${category.label} · ${entries.size}"
            },
        )
    }

    item(key = "list-$category") {
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            if (entries.isEmpty()) {
                Text(
                    text = if (searching) "没有匹配的组件" else "该分类暂无组件",
                    modifier = Modifier.padding(16.dp),
                    style = MiuixTheme.textStyles.body2,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            } else {
                entries.forEach { entry ->
                    ArrowPreference(
                        title = entry.name,
                        summary = entry.summary,
                        onClick = { onOpen(entry) },
                    )
                }
            }
        }
    }
}
