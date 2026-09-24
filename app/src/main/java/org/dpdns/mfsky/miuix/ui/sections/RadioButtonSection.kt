// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.RadioButton
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.preference.RadioButtonPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.radioButtonSection() {
    item(key = "radioButton") {
        SmallTitle(text = "RadioButton")
        RadioButtonGroupDemo()
        SmallTitle(text = "RadioButtonPreference")
        RadioButtonPreferenceDemo()
    }
}

@Composable
private fun RadioButtonGroupDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Card(modifier = Modifier.demoCard()) {
        Column {
            listOf("选项 A", "选项 B", "选项 C").forEachIndexed { index, label ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedIndex = index }
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(text = label, style = MiuixTheme.textStyles.body1)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = true, onClick = null, enabled = false)
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "禁用的单选框",
                    style = MiuixTheme.textStyles.body1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
        }
    }
}

@Composable
private fun RadioButtonPreferenceDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    listOf("选项 A", "选项 B", "选项 C").forEachIndexed { index, title ->
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            RadioButtonPreference(
                title = title,
                summary = if (selectedIndex == index) "已选中" else "未选中",
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
            )
        }
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        RadioButtonPreference(
            title = "禁用的选项",
            summary = "这一项不可选",
            selected = true,
            enabled = false,
            onClick = {},
        )
    }
}
