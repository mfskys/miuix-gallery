package org.dpdns.mfsky.miuix.ui.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FloatingToolbar
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalDivider
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.CloudFill
import top.yukonga.miuix.kmp.icon.extended.Download
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.FavoritesFill
import top.yukonga.miuix.kmp.icon.extended.Forward
import top.yukonga.miuix.kmp.icon.extended.Music
import top.yukonga.miuix.kmp.icon.extended.Pause
import top.yukonga.miuix.kmp.icon.extended.Play
import top.yukonga.miuix.kmp.icon.extended.Recording
import top.yukonga.miuix.kmp.icon.extended.Reply
import top.yukonga.miuix.kmp.icon.extended.Send
import top.yukonga.miuix.kmp.icon.extended.Share
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val newsHeadlines = listOf(
    "新款折叠屏发布，上手体验全记录",
    "这个开源项目把组件库卷出了新高度",
    "夏季旅行路线推荐：从海边到山野",
    "AI 修图到底改了什么？一组对比图",
    "城市夜跑指南：路线、装备与安全",
    "一周数字生活好物盘点",
)

private val newsSources = listOf("中新网", "少数派", "36氪", "IT之家", "澎湃新闻", "量子位")

private val videoTitles = listOf(
    "把 miuix 组件拆开看：进阶篇",
    "一分钟看懂超椭圆圆角",
    "自驾 318：日常碎片 Vlog",
    "玻璃材质是怎么画出来的",
    "手机摄影的五个小技巧",
    "桌面美化终极指南",
)

private val videoDurations = listOf("12:08", "01:24", "25:40", "08:16", "05:33", "15:02")

private val liveRooms = listOf("极客实验室", "山野电台", "像素画坊")

private val songs = listOf(
    "夜航星" to "气运联盟",
    "城市黄昏" to "慢车",
    "海边的风" to "南屿",
    "晚风电台" to "路遥",
    "夏至未至" to "白昼",
)

private val hourlyWeather = listOf(
    "现在" to 28,
    "14时" to 29,
    "15时" to 29,
    "16时" to 28,
    "17时" to 27,
    "18时" to 26,
    "19时" to 25,
    "20时" to 24,
)

private val weekWeather = listOf(
    Triple("今天", 22, 29),
    Triple("周四", 21, 28),
    Triple("周五", 22, 30),
    Triple("周六", 23, 31),
    Triple("周日", 22, 29),
    Triple("周一", 20, 27),
    Triple("周二", 19, 26),
)

@Composable
internal fun NewsPage(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    var tab by remember { mutableIntStateOf(0) }
    var refreshing by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            tabs = listOf("推荐", "科技", "体育", "娱乐"),
            selectedTabIndex = tab,
            onTabSelected = { tab = it },
            modifier = Modifier.padding(horizontal = 12.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(horizontal = 12.dp),
        ) {
            PullToRefresh(
                isRefreshing = refreshing,
                onRefresh = {
                    refreshing = true
                    scope.launch {
                        delay(1500)
                        refreshing = false
                    }
                },
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(8) { index ->
                        BasicComponent(
                            title = newsHeadlines[(index + tab) % newsHeadlines.size],
                            summary = "${newsSources[index % newsSources.size]} · ${index + 1}小时前",
                            startAction = {
                                ShowcaseThumb(modifier = Modifier.size(56.dp), accent = index)
                            },
                            endActions = {
                                if (index == 0) {
                                    Badge(containerColor = Color(0xFFE5484D), contentColor = Color.White) {
                                        Text(text = "热", style = TextStyle(fontSize = 10.sp))
                                    }
                                }
                            },
                            onClick = {
                                scope.launch { snackbarHostState.showSnackbar("已打开「${newsHeadlines[(index + tab) % newsHeadlines.size]}」") }
                            },
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        SmallTitle(text = "热点聚焦")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Badge(containerColor = Color(0xFFE5484D), contentColor = Color.White) {
                        Text(text = "置顶", style = TextStyle(fontSize = 10.sp))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "今早的发布会，全部重点都在这里了",
                        style = MiuixTheme.textStyles.body1,
                        modifier = Modifier.weight(1f),
                    )
                }
                HorizontalDivider()
                BasicComponent(
                    title = "多设备互联的新玩法，值得每个演示项目试试",
                    summary = "少数派 · 刚刚",
                    startAction = { ShowcaseThumb(modifier = Modifier.size(48.dp), accent = 4) },
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun VideoPage(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallTitle(text = "推荐视频")
        videoTitles.indices.chunked(2).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                row.forEach { titleIndex ->
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(showcaseBrush(titleIndex)),
                        ) {
                            Icon(
                                imageVector = MiuixIcons.Play,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(36.dp)
                                    .align(Alignment.Center),
                            )
                            Badge(
                                containerColor = Color.Black.copy(alpha = 0.55f),
                                contentColor = Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(8.dp),
                            ) {
                                Text(text = videoDurations[titleIndex], style = TextStyle(fontSize = 10.sp))
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = videoTitles[titleIndex],
                            style = MiuixTheme.textStyles.body2,
                        )
                        Text(
                            text = "创作者 ${titleIndex + 1} · ${(titleIndex + 3) * 17}万播放",
                            style = MiuixTheme.textStyles.footnote1,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                        )
                    }
                }
                if (row.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
        SmallTitle(text = "正在直播")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                liveRooms.forEachIndexed { index, name ->
                    BasicComponent(
                        title = name,
                        summary = "${(index + 1) * 2}.${index + 1}万人在看",
                        startAction = {
                            ShowcaseThumb(
                                modifier = Modifier.size(48.dp),
                                accent = index + 4,
                                icon = MiuixIcons.Recording,
                            )
                        },
                        endActions = {
                            Badge(containerColor = Color(0xFFE5484D), contentColor = Color.White) {
                                Text(text = "直播", style = TextStyle(fontSize = 10.sp))
                            }
                        },
                        onClick = {
                            scope.launch { snackbarHostState.showSnackbar("进入「$name」的直播间") }
                        },
                    )
                    if (index < liveRooms.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            var playing by remember { mutableStateOf(true) }
            var liked by remember { mutableStateOf(false) }
            var saved by remember { mutableStateOf(false) }
            FloatingToolbar(showDivider = true) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    IconButton(onClick = { playing = !playing }) {
                        Icon(
                            imageVector = if (playing) MiuixIcons.Pause else MiuixIcons.Play,
                            contentDescription = if (playing) "暂停" else "播放",
                        )
                    }
                    IconButton(onClick = { liked = !liked }) {
                        Icon(
                            imageVector = if (liked) MiuixIcons.FavoritesFill else MiuixIcons.Favorites,
                            contentDescription = "收藏",
                            tint = if (liked) Color(0xFFE5484D) else MiuixTheme.colorScheme.onSurface,
                        )
                    }
                    IconButton(onClick = { saved = !saved }) {
                        Icon(
                            imageVector = MiuixIcons.Download,
                            contentDescription = "下载",
                            tint = if (saved) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface,
                        )
                    }
                    IconButton(onClick = {
                        scope.launch { snackbarHostState.showSnackbar("已复制分享链接") }
                    }) {
                        Icon(imageVector = MiuixIcons.Share, contentDescription = "分享")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun MusicPage() {
    var track by remember { mutableIntStateOf(0) }
    var playing by remember { mutableStateOf(true) }
    var liked by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0.35f) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(showcaseBrush(track)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = MiuixIcons.Music,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.85f),
                    modifier = Modifier.size(56.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = songs[track].first,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            )
            Text(
                text = songs[track].second,
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
            Spacer(modifier = Modifier.height(12.dp))
            IconButton(onClick = { liked = !liked }) {
                Icon(
                    imageVector = if (liked) MiuixIcons.FavoritesFill else MiuixIcons.Favorites,
                    contentDescription = "喜欢",
                    tint = if (liked) Color(0xFFE5484D) else MiuixTheme.colorScheme.onSurface,
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Slider(
                    value = progress,
                    onValueChange = { progress = it },
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "1:24",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                    Text(
                        text = "3:56",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            FloatingToolbar(showDivider = true) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    IconButton(onClick = { track = (track + songs.size - 1) % songs.size }) {
                        Icon(imageVector = MiuixIcons.Reply, contentDescription = "上一首")
                    }
                    IconButton(onClick = { playing = !playing }) {
                        Icon(
                            imageVector = if (playing) MiuixIcons.Pause else MiuixIcons.Play,
                            contentDescription = if (playing) "暂停" else "播放",
                        )
                    }
                    IconButton(onClick = { track = (track + 1) % songs.size }) {
                        Icon(imageVector = MiuixIcons.Forward, contentDescription = "下一首")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        SmallTitle(text = "播放列表")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                songs.forEachIndexed { index, song ->
                    BasicComponent(
                        title = song.first,
                        summary = song.second,
                        startAction = {
                            Box(
                                modifier = Modifier.size(32.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = MiuixTheme.textStyles.body2,
                                    color = if (index == track) {
                                        MiuixTheme.colorScheme.primary
                                    } else {
                                        MiuixTheme.colorScheme.onBackgroundVariant
                                    },
                                )
                            }
                        },
                        endActions = {
                            if (index == track) {
                                Icon(
                                    imageVector = MiuixIcons.Music,
                                    contentDescription = "正在播放",
                                    tint = MiuixTheme.colorScheme.primary,
                                )
                            }
                        },
                        onClick = {
                            track = index
                            progress = 0f
                            playing = true
                        },
                    )
                    if (index < songs.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun WeatherPage() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "28°",
                style = TextStyle(fontSize = 72.sp, fontWeight = FontWeight.Light),
            )
            Text(text = "晴 · 体感 30°", style = MiuixTheme.textStyles.body1)
            Text(
                text = "北京朝阳 · 今天",
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "24小时预报", style = MiuixTheme.textStyles.body1)
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    hourlyWeather.forEachIndexed { index, hour ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Text(
                                text = hour.first,
                                style = MiuixTheme.textStyles.footnote1,
                                color = MiuixTheme.colorScheme.onBackgroundVariant,
                            )
                            Icon(
                                imageVector = MiuixIcons.CloudFill,
                                contentDescription = null,
                                tint = if (index == 0) {
                                    MiuixTheme.colorScheme.primary
                                } else {
                                    MiuixTheme.colorScheme.onBackgroundVariant
                                },
                                modifier = Modifier.size(20.dp),
                            )
                            Text(text = "${hour.second}°", style = MiuixTheme.textStyles.body2)
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "未来7天", style = MiuixTheme.textStyles.body1)
                Spacer(modifier = Modifier.height(8.dp))
                weekWeather.forEachIndexed { index, day ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = day.first,
                            style = MiuixTheme.textStyles.body2,
                            modifier = Modifier.width(56.dp),
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(Color(0xFF4FB6C9), Color(0xFFF2A64B)),
                                    ),
                                ),
                        )
                        Text(
                            text = "${day.second}° ~ ${day.third}°",
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                            modifier = Modifier.width(90.dp),
                        )
                    }
                    if (index < weekWeather.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "62%",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "湿度",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
                VerticalDivider(modifier = Modifier.height(36.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "东南风 2 级",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "风力",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
                VerticalDivider(modifier = Modifier.height(36.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "42 优",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                        color = Color(0xFF48B884),
                    )
                    Text(
                        text = "空气质量",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun ChatPage() {
    val replies = listOf("收到！", "没问题", "哈哈，好", "知道了")
    val messages = remember {
        mutableStateListOf(
            true to "在吗？晚上一起吃饭吗",
            false to "好啊，想吃什么？",
            true to "公司旁边新开的那家云南菜",
            false to "行，六点半老地方见",
        )
    }
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty() && messages.last().first) {
            delay(900)
            messages.add(false to replies[messages.size % replies.size])
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Text(
                text = "今天 19:02",
                style = MiuixTheme.textStyles.footnote1,
                color = MiuixTheme.colorScheme.onBackgroundVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            messages.forEachIndexed { index, message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        alignment = if (message.first) Alignment.End else Alignment.Start,
                    ),
                    verticalAlignment = Alignment.Bottom,
                ) {
                    if (!message.first) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(showcaseBrush(index)),
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (message.first) {
                            MiuixTheme.colorScheme.primary
                        } else {
                            MiuixTheme.colorScheme.secondary
                        },
                    ) {
                        Text(
                            text = message.second,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            style = MiuixTheme.textStyles.body2,
                            color = if (message.first) Color.White else MiuixTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var input by remember { mutableStateOf("") }
            var expanded by remember { mutableStateOf(false) }
            val send: () -> Unit = {
                if (input.isNotBlank()) {
                    messages.add(true to input)
                    input = ""
                }
            }
            InputField(
                query = input,
                onQueryChange = { input = it },
                onSearch = {
                    send()
                    expanded = false
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                label = "发消息…",
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = send) {
                Icon(
                    imageVector = MiuixIcons.Send,
                    contentDescription = "发送",
                    tint = MiuixTheme.colorScheme.primary,
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}
