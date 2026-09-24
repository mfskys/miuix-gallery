// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.RichTooltipBox
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.rememberTooltipState
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Info
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.tooltipSection() {
    item(key = "tooltip") {
        SmallTitle(text = "Tooltip")
        Card(modifier = Modifier.demoCard()) {
            val scope = rememberCoroutineScope()
            val plainState = rememberTooltipState(isPersistent = true)
            val richState = rememberTooltipState(isPersistent = true)
            var actionCount by remember { mutableIntStateOf(0) }

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "长按图标显示提示，接鼠标时指向图标即可。也可以点下面的按钮直接触发。",
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    TooltipBox(text = "编辑", state = plainState) {
                        IconButton(onClick = { scope.launch { plainState.show() } }) {
                            Icon(imageVector = MiuixIcons.Edit, contentDescription = "编辑")
                        }
                    }
                    RichTooltipBox(
                        title = "富文本提示",
                        text = "富提示可以带标题、正文，以及一个操作按钮。",
                        actionText = "知道了",
                        onActionClick = { actionCount++ },
                        state = richState,
                    ) {
                        IconButton(onClick = { scope.launch { richState.show() } }) {
                            Icon(imageVector = MiuixIcons.Info, contentDescription = "富文本提示")
                        }
                    }
                }
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(onClick = { scope.launch { plainState.show() } }) {
                        Text(text = "显示普通提示")
                    }
                    Button(onClick = { scope.launch { richState.show() } }) {
                        Text(text = "显示富提示")
                    }
                }
                Text(
                    text = if (actionCount > 0) "已点击「知道了」$actionCount 次" else "尚未触发操作按钮",
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
        }
    }
}
