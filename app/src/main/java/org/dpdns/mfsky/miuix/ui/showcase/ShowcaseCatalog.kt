package org.dpdns.mfsky.miuix.ui.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.CloudFill
import top.yukonga.miuix.kmp.icon.extended.Contacts
import top.yukonga.miuix.kmp.icon.extended.Messages
import top.yukonga.miuix.kmp.icon.extended.Music
import top.yukonga.miuix.kmp.icon.extended.Photos
import top.yukonga.miuix.kmp.icon.extended.Play
import top.yukonga.miuix.kmp.icon.extended.Promotions
import top.yukonga.miuix.kmp.icon.extended.Store
import top.yukonga.miuix.kmp.icon.extended.Tasks
import top.yukonga.miuix.kmp.icon.extended.Tune
import top.yukonga.miuix.kmp.theme.MiuixTheme

data class ShowcaseEntry(
    val id: String,
    val title: String,
    val summary: String,
    val icon: ImageVector,
    val accent: Int,
)

val showcaseCatalog: List<ShowcaseEntry> = listOf(
    ShowcaseEntry("news", "资讯", "新闻流：标签、下拉刷新、热点卡片", MiuixIcons.Promotions, 0),
    ShowcaseEntry("video", "视频", "视频流：封面网格、直播、播放工具栏", MiuixIcons.Play, 1),
    ShowcaseEntry("music", "音乐", "播放器：封面、进度、播放列表", MiuixIcons.Music, 2),
    ShowcaseEntry("weather", "天气", "天气：小时与逐日预报", MiuixIcons.CloudFill, 3),
    ShowcaseEntry("chat", "聊天", "会话：气泡与输入栏", MiuixIcons.Messages, 4),
    ShowcaseEntry("store", "应用商店", "商店：排行榜、安装进度", MiuixIcons.Store, 5),
    ShowcaseEntry("control", "控制中心", "快捷开关、亮度与音量", MiuixIcons.Tune, 6),
    ShowcaseEntry("gallery", "相册", "照片网格与相册分组", MiuixIcons.Photos, 7),
    ShowcaseEntry("agenda", "日程", "月历与当日安排", MiuixIcons.Tasks, 8),
    ShowcaseEntry("profile", "个人主页", "资料、关注与内容标签页", MiuixIcons.Contacts, 9),
)

private val showcaseAccents: List<Color> = listOf(
    Color(0xFF5B8DEF),
    Color(0xFF7C6BF2),
    Color(0xFF48B884),
    Color(0xFFF2A64B),
    Color(0xFFE96A6A),
    Color(0xFF4FB6C9),
    Color(0xFF9A6BF0),
    Color(0xFFE96AAE),
    Color(0xFF6BA8F2),
    Color(0xFF57C08A),
)

fun showcaseAccent(index: Int): Color = showcaseAccents[index % showcaseAccents.size]

fun showcaseBrush(index: Int): Brush = Brush.linearGradient(
    listOf(showcaseAccent(index), showcaseAccent(index + 3)),
)

@Composable
internal fun ShowcaseThumb(
    modifier: Modifier = Modifier,
    accent: Int,
    icon: ImageVector? = null,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(showcaseBrush(accent)),
        contentAlignment = Alignment.Center,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Composable
internal fun ShowcaseDot(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color),
    )
}

@Composable
private fun ShowcaseTile(
    entry: ShowcaseEntry,
    onClick: (ShowcaseEntry) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.clickable { onClick(entry) }) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(showcaseBrush(entry.accent)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = entry.icon,
                    contentDescription = entry.title,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = entry.title, style = MiuixTheme.textStyles.body1)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = entry.summary,
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
        }
    }
}

fun LazyListScope.showcaseListContent(onOpen: (ShowcaseEntry) -> Unit) {
    item(key = "showcase-header") {
        SmallTitle(text = "完整页面 · ${showcaseCatalog.size}")
    }
    showcaseCatalog.chunked(2).forEachIndexed { index, row ->
        item(key = "showcase-row-$index") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                row.forEach { entry ->
                    ShowcaseTile(entry = entry, onClick = onOpen, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
    item(key = "showcase-note") {
        Spacer(modifier = Modifier.height(12.dp))
    }
}
