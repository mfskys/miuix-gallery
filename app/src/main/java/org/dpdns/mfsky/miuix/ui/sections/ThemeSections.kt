// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Add
import top.yukonga.miuix.kmp.icon.extended.All
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.icon.extended.Clear
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Copy
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.Info
import top.yukonga.miuix.kmp.icon.extended.Layers
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Search
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.squircle.squircleSurface
import top.yukonga.miuix.kmp.theme.MiuixTheme

private data class ColorSample(val name: String, val color: Color)

@Composable
private fun ColorSwatch(sample: ColorSample) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(sample.color),
        )
        Column {
            Text(text = sample.name, style = MiuixTheme.textStyles.body2)
            Text(
                text = sample.color.toString().uppercase(),
                style = MiuixTheme.textStyles.footnote2,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.colorSchemeSection() {
    item(key = "colorscheme") {
        SmallTitle(text = "ColorScheme")
        Card(modifier = Modifier.demoCard()) {
            val scheme = MiuixTheme.colorScheme
            val samples = remember(scheme) {
                listOf(
                    ColorSample("primary", scheme.primary),
                    ColorSample("surface", scheme.surface),
                    ColorSample("surfaceContainerHigh", scheme.surfaceContainerHigh),
                    ColorSample("onBackground", scheme.onBackground),
                    ColorSample("onBackgroundVariant", scheme.onBackgroundVariant),
                    ColorSample("onSurfaceContainer", scheme.onSurfaceContainer),
                    ColorSample("dividerLine", scheme.dividerLine),
                )
            }
            Column(modifier = Modifier.padding(16.dp)) {
                samples.forEach { ColorSwatch(it) }
            }
        }
    }
}

fun LazyListScope.textStylesSection() {
    item(key = "textstyles") {
        SmallTitle(text = "TextStyles")
        Card(modifier = Modifier.demoCard()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(text = "main", style = MiuixTheme.textStyles.main)
                Text(text = "paragraph", style = MiuixTheme.textStyles.paragraph)
                Text(text = "headline1", style = MiuixTheme.textStyles.headline1)
                Text(text = "headline2", style = MiuixTheme.textStyles.headline2)
                Text(text = "title1", style = MiuixTheme.textStyles.title1)
                Text(text = "title2", style = MiuixTheme.textStyles.title2)
                Text(text = "title3", style = MiuixTheme.textStyles.title3)
                Text(text = "title4", style = MiuixTheme.textStyles.title4)
                Text(text = "subtitle", style = MiuixTheme.textStyles.subtitle)
                Text(text = "body1", style = MiuixTheme.textStyles.body1)
                Text(text = "body2", style = MiuixTheme.textStyles.body2)
                Text(text = "button", style = MiuixTheme.textStyles.button)
                Text(text = "footnote1", style = MiuixTheme.textStyles.footnote1)
                Text(text = "footnote2", style = MiuixTheme.textStyles.footnote2)
            }
        }
    }
}

fun LazyListScope.squircleSection() {
    item(key = "squircle") {
        SmallTitle(text = "Squircle")
        Card(modifier = Modifier.demoCard()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .squircleSurface(
                            color = MiuixTheme.colorScheme.primary,
                            cornerRadius = 22.dp,
                        ),
                )
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(MiuixTheme.colorScheme.surfaceContainerHigh),
                )
            }
            Text(
                text = "左为超椭圆圆角，右为普通圆角",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.iconsSection() {
    item(key = "icons") {
        SmallTitle(text = "Icons")
        Card(modifier = Modifier.demoCard()) {
            val icons = listOf(
                "Home" to MiuixIcons.Home,
                "Settings" to MiuixIcons.Settings,
                "Search" to MiuixIcons.Search,
                "Info" to MiuixIcons.Info,
                "Add" to MiuixIcons.Add,
                "Edit" to MiuixIcons.Edit,
                "Delete" to MiuixIcons.Delete,
                "Copy" to MiuixIcons.Copy,
                "More" to MiuixIcons.More,
                "Clear" to MiuixIcons.Clear,
                "Close" to MiuixIcons.Close,
                "Back" to MiuixIcons.Back,
                "GridView" to MiuixIcons.GridView,
                "ListView" to MiuixIcons.ListView,
                "Layers" to MiuixIcons.Layers,
                "All" to MiuixIcons.All,
            )
            Column(modifier = Modifier.padding(16.dp)) {
                icons.chunked(4).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        row.forEach { (name, icon) ->
                            Column(
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(imageVector = icon, contentDescription = name)
                                Text(
                                    text = name,
                                    modifier = Modifier.padding(top = 4.dp),
                                    style = MiuixTheme.textStyles.footnote2,
                                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
