// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.menu.OverlayIconDropdownMenu
import top.yukonga.miuix.kmp.preference.CheckboxPreference
import top.yukonga.miuix.kmp.preference.RadioButtonPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.checkboxPreferenceSection() {
    item(key = "checkboxpreference") {
        SmallTitle(text = "CheckboxPreference")
        Card(modifier = Modifier.demoCard()) {
            var first by remember { mutableStateOf(true) }
            var second by remember { mutableStateOf(false) }
            CheckboxPreference(
                title = "已选中",
                checked = first,
                onCheckedChange = { first = it },
            )
            CheckboxPreference(
                title = "未选中",
                checked = second,
                onCheckedChange = { second = it },
                summary = "带副标题的设置项",
            )
            CheckboxPreference(
                title = "禁用状态",
                checked = true,
                onCheckedChange = null,
                enabled = false,
            )
        }
    }
}

fun LazyListScope.radioButtonPreferenceSection() {
    item(key = "radiobuttonpreference") {
        SmallTitle(text = "RadioButtonPreference")
        Card(modifier = Modifier.demoCard()) {
            var selected by remember { mutableIntStateOf(0) }
            val options = remember { listOf("选项一", "选项二", "选项三") }
            options.forEachIndexed { index, text ->
                RadioButtonPreference(
                    title = text,
                    selected = selected == index,
                    onClick = { selected = index },
                    summary = if (index == 1) "带副标题" else null,
                )
            }
            RadioButtonPreference(
                title = "禁用状态",
                selected = false,
                onClick = null,
                enabled = false,
            )
        }
    }
}

fun LazyListScope.switchPreferenceSection() {
    item(key = "switchpreference") {
        SmallTitle(text = "SwitchPreference")
        Card(modifier = Modifier.demoCard()) {
            var checked by remember { mutableStateOf(true) }
            var checked2 by remember { mutableStateOf(false) }
            SwitchPreference(
                title = "开关设置项",
                checked = checked,
                onCheckedChange = { checked = it },
            )
            SwitchPreference(
                title = "带副标题",
                checked = checked2,
                onCheckedChange = { checked2 = it },
                summary = "副标题说明文字",
            )
            SwitchPreference(
                title = "末尾显示状态",
                checked = checked2,
                onCheckedChange = { checked2 = it },
                endActions = {
                    Text(
                        text = if (checked2) "开" else "关",
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
            )
            SwitchPreference(
                title = "禁用状态",
                checked = true,
                onCheckedChange = {},
                enabled = false,
            )
        }
    }
}

fun LazyListScope.sliderPreferenceSection() {
    item(key = "sliderpreference") {
        SmallTitle(text = "SliderPreference")
        Card(modifier = Modifier.demoCard()) {
            var value by remember { mutableFloatStateOf(0.4f) }
            var value2 by remember { mutableFloatStateOf(0.6f) }
            SliderPreference(
                title = "滑动设置项",
                value = value,
                onValueChange = { value = it },
                valueText = "${(value * 100).toInt()}",
            )
            SliderPreference(
                title = "自定义范围",
                value = value2,
                onValueChange = { value2 = it },
                summary = "支持副标题与数值显示",
                valueText = "${(value2 * 100).toInt()}%",
            )
            SliderPreference(
                title = "禁用状态",
                value = 0.3f,
                onValueChange = {},
                enabled = false,
            )
        }
    }
}

fun LazyListScope.iconDropdownMenuSection() {
    item(key = "icondropdownmenu") {
        SmallTitle(text = "IconDropdownMenu")
        Card(modifier = Modifier.demoCard()) {
            var selected by remember { mutableIntStateOf(0) }
            val texts = remember { listOf("选项 A", "选项 B", "选项 C") }
            val entry = remember(selected) {
                DropdownEntry(
                    items = texts.mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = selected == index,
                            onClick = { selected = index },
                        )
                    },
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(
                    text = "当前选择：${texts[selected]}",
                    modifier = Modifier.padding(end = 16.dp),
                    style = MiuixTheme.textStyles.body2,
                )
                OverlayIconDropdownMenu(entry = entry) {
                    Icon(imageVector = MiuixIcons.More, contentDescription = "更多")
                }
            }
        }
    }
}
