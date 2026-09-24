// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.dpdns.mfsky.miuix.ui.catalog.ComponentEntry
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * The modifier shared by every demo card in the catalog.
 */
fun Modifier.demoCard(): Modifier =
    this
        .padding(horizontal = 12.dp)
        .padding(bottom = 12.dp)

fun LazyListScope.textSection() {
    item(key = "text") {
        SmallTitle(text = "Text")
        Card(modifier = Modifier.demoCard()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(text = "Headline 1", style = MiuixTheme.textStyles.headline1)
                Text(text = "Headline 2", style = MiuixTheme.textStyles.headline2)
                Text(text = "Title 1", style = MiuixTheme.textStyles.title1)
                Text(text = "Title 2", style = MiuixTheme.textStyles.title2)
                Text(text = "Subtitle", style = MiuixTheme.textStyles.subtitle)
                Text(text = "Body 1", style = MiuixTheme.textStyles.body1)
                Text(text = "Body 2", style = MiuixTheme.textStyles.body2)
                Text(text = "Button", style = MiuixTheme.textStyles.button)
                Text(
                    text = "Footnote 1 / 次要文本",
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
                Text(
                    text = "Footnote 2",
                    style = MiuixTheme.textStyles.footnote2,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
        }
    }
}

fun LazyListScope.smallTitleSection() {
    item(key = "smalltitle") {
        SmallTitle(text = "SmallTitle")
        SmallTitle(
            text = "自定义颜色的 SmallTitle",
            textColor = MiuixTheme.colorScheme.primary,
        )
        Card(modifier = Modifier.demoCard()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "SmallTitle 用于分组标题", style = MiuixTheme.textStyles.body2)
            }
        }
    }
}

/**
 * Shown for entries whose demo has not been written yet.
 */
fun LazyListScope.placeholderSection(entry: ComponentEntry) {
    item(key = "placeholder-${entry.id}") {
        SmallTitle(text = entry.name)
        Card(modifier = Modifier.demoCard()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(text = "演示页待实现", style = MiuixTheme.textStyles.body1)
                Text(
                    text = entry.summary,
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
        }
    }
}
