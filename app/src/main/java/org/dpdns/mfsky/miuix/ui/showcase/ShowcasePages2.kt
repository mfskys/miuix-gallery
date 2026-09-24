package org.dpdns.mfsky.miuix.ui.showcase

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.VerticalDivider
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Add
import top.yukonga.miuix.kmp.icon.extended.Contacts
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.Lock
import top.yukonga.miuix.kmp.icon.extended.Location
import top.yukonga.miuix.kmp.icon.extended.Mic
import top.yukonga.miuix.kmp.icon.extended.Refresh
import top.yukonga.miuix.kmp.icon.extended.Scan
import top.yukonga.miuix.kmp.icon.extended.ScreenMirroring
import top.yukonga.miuix.kmp.icon.extended.Show
import top.yukonga.miuix.kmp.icon.extended.Timer
import top.yukonga.miuix.kmp.icon.extended.VolumeOff
import top.yukonga.miuix.kmp.icon.extended.VolumeUp
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val topApps = listOf(
    "星穹笔记" to "效率",
    "像素画坊" to "创意",
    "云途记账" to "财务",
    "拾光相册" to "照片与视频",
    "浮舟阅读" to "阅读",
    "行迹天气" to "天气",
)

private val pendingUpdates = listOf("织谱 Studio", "卡片抽屉")

private val controlTiles = listOf(
    MiuixIcons.Location to "定位",
    MiuixIcons.Mic to "麦克风",
    MiuixIcons.Scan to "扫一扫",
    MiuixIcons.Timer to "计时器",
    MiuixIcons.ScreenMirroring to "投屏",
    MiuixIcons.Lock to "锁定",
    MiuixIcons.Refresh to "同步",
    MiuixIcons.VolumeOff to "静音",
)

private val albumGroups = listOf(
    "人物" to 128,
    "风景" to 96,
    "美食" to 64,
    "截图" to 210,
)

private val memories = listOf("去年今日", "海边三天", "山里一周", "城市夜行")

private val agendaEvents: Map<Int, List<Triple<String, String, Int>>> = mapOf(
    24 to listOf(
        Triple("产品评审会", "10:00 - 11:30", 0),
        Triple("和设计师过演示页", "14:00 - 15:00", 1),
        Triple("晚上：羽毛球", "19:30 - 21:00", 4),
    ),
    3 to listOf(Triple("月度复盘", "09:30 - 10:30", 2)),
    12 to listOf(Triple("版本 v0.2 发布", "全天", 5)),
    28 to listOf(Triple("团建：城市定向", "13:00 - 18:00", 3)),
)

@Composable
internal fun StorePage(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(showcaseBrush(5))
                    .padding(20.dp),
            ) {
                var downloaded by remember { mutableStateOf(false) }
                Column {
                    Text(
                        text = "编辑精选",
                        style = MiuixTheme.textStyles.footnote1,
                        color = Color.White.copy(alpha = 0.8f),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "本周必下载",
                        style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { downloaded = true },
                        enabled = !downloaded,
                    ) {
                        Text(text = if (downloaded) "已下载" else "立即下载")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SmallTitle(text = "排行榜")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                val installState = remember { mutableStateMapOf<Int, Int>() }
                topApps.forEachIndexed { index, app ->
                    BasicComponent(
                        title = app.first,
                        summary = "${app.second} · ${(topApps.size - index) * 120} MB",
                        startAction = {
                            Box(
                                modifier = Modifier.size(44.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                                    color = if (index < 3) {
                                        showcaseAccent(index)
                                    } else {
                                        MiuixTheme.colorScheme.onBackgroundVariant
                                    },
                                )
                            }
                        },
                        endActions = {
                            when (installState[index] ?: 0) {
                                0 -> TextButton(text = "安装", onClick = { installState[index] = 1 })
                                1 -> {
                                    LinearProgressIndicator(
                                        progress = 0.6f,
                                        modifier = Modifier.width(64.dp),
                                    )
                                    LaunchedEffect(index) {
                                        delay(1800)
                                        installState[index] = 2
                                    }
                                }
                                else -> TextButton(text = "打开", onClick = { installState[index] = 0 })
                            }
                        },
                    )
                    if (index < topApps.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SmallTitle(text = "可更新")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                val updated = remember { mutableStateListOf<Int>() }
                pendingUpdates.forEachIndexed { index, name ->
                    val done = updated.contains(index)
                    BasicComponent(
                        title = name,
                        summary = "新版本优化了整体体验",
                        endActions = {
                            Badge(containerColor = Color(0xFFE5484D), contentColor = Color.White) {
                                Text(text = "更", style = TextStyle(fontSize = 10.sp))
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            TextButton(
                                text = if (done) "已更新" else "更新",
                                onClick = { updated.add(index) },
                                enabled = !done,
                            )
                        },
                        onClick = {
                            scope.launch { snackbarHostState.showSnackbar("查看「$name」的更新说明") }
                        },
                    )
                    if (index < pendingUpdates.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun ControlCenterPage() {
    Column(modifier = Modifier.fillMaxWidth()) {
        val states = remember { mutableStateListOf(true, false, false, true, false, true, false, false) }
        controlTiles.chunked(4).forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                row.forEachIndexed { columnIndex, tile ->
                    val index = rowIndex * 4 + columnIndex
                    val on = states[index]
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (on) showcaseAccent(index) else MiuixTheme.colorScheme.secondary,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { states[index] = !states[index] },
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Icon(
                                imageVector = tile.first,
                                contentDescription = tile.second,
                                tint = if (on) Color.White else MiuixTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = tile.second,
                                style = MiuixTheme.textStyles.footnote1,
                                color = if (on) Color.White else MiuixTheme.colorScheme.onSurface,
                            )
                            Text(
                                text = if (on) "已开启" else "已关闭",
                                style = TextStyle(fontSize = 10.sp),
                                color = if (on) {
                                    Color.White.copy(alpha = 0.75f)
                                } else {
                                    MiuixTheme.colorScheme.onBackgroundVariant
                                },
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                var brightness by remember { mutableStateOf(0.7f) }
                var volume by remember { mutableStateOf(0.4f) }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        imageVector = MiuixIcons.Show,
                        contentDescription = "亮度",
                        tint = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                    Slider(
                        value = brightness,
                        onValueChange = { brightness = it },
                        modifier = Modifier.weight(1f),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Icon(
                        imageVector = MiuixIcons.VolumeUp,
                        contentDescription = "音量",
                        tint = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                    Slider(
                        value = volume,
                        onValueChange = { volume = it },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                var powerSave by remember { mutableStateOf(false) }
                var autoDark by remember { mutableStateOf(false) }
                BasicComponent(
                    title = "省电模式",
                    summary = "降低亮度并限制后台活动",
                    endActions = {
                        Switch(checked = powerSave, onCheckedChange = { powerSave = it })
                    },
                )
                HorizontalDivider()
                BasicComponent(
                    title = "深色模式跟随日落",
                    summary = "日落后自动切换深色配色",
                    endActions = {
                        Switch(checked = autoDark, onCheckedChange = { autoDark = it })
                    },
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun GalleryPage() {
    Column(modifier = Modifier.fillMaxWidth()) {
        SmallTitle(text = "今天")
        (0..8).toList().chunked(3).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                row.forEach { accent ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(showcaseBrush(accent)),
                    )
                }
                if (row.size < 3) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        SmallTitle(text = "相册")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                albumGroups.forEachIndexed { index, album ->
                    BasicComponent(
                        title = album.first,
                        summary = "${album.second} 张照片",
                        startAction = {
                            ShowcaseThumb(modifier = Modifier.size(44.dp), accent = index + 2)
                        },
                    )
                    if (index < albumGroups.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SmallTitle(text = "回忆")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    memories.forEachIndexed { index, title ->
                        Box(
                            modifier = Modifier
                                .width(170.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(showcaseBrush(index + 1)),
                        ) {
                            Text(
                                text = title,
                                style = MiuixTheme.textStyles.body2,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(10.dp),
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
internal fun AgendaPage() {
    var selectedDay by remember { mutableIntStateOf(24) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "9月",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    )
                    Text(
                        text = "2026年",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    listOf("一", "二", "三", "四", "五", "六", "日").forEach { label ->
                        Text(
                            text = label,
                            style = MiuixTheme.textStyles.footnote1,
                            color = MiuixTheme.colorScheme.onBackgroundVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                val cells = buildList {
                    add(null)
                    addAll((1..30).toList())
                    while (size % 7 != 0) {
                        add(null)
                    }
                }
                cells.chunked(7).forEach { week ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        week.forEach { day ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(34.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                if (day != null) {
                                    val selected = day == selectedDay
                                    Box(
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (selected) {
                                                    MiuixTheme.colorScheme.primary
                                                } else {
                                                    Color.Transparent
                                                },
                                            ),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = "$day",
                                            style = MiuixTheme.textStyles.body2,
                                            color = if (selected) {
                                                Color.White
                                            } else {
                                                MiuixTheme.colorScheme.onSurface
                                            },
                                            modifier = Modifier.clickable { selectedDay = day },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    (1..30).forEach { day ->
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (agendaEvents.containsKey(day)) {
                                ShowcaseDot(color = showcaseAccent(day % 7))
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SmallTitle(text = "9月${selectedDay}日")
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                val events = agendaEvents[selectedDay].orEmpty()
                if (events.isEmpty()) {
                    Text(
                        text = "当天没有安排，好好休息。",
                        style = MiuixTheme.textStyles.footnote1,
                        color = MiuixTheme.colorScheme.onBackgroundVariant,
                        modifier = Modifier.padding(16.dp),
                    )
                } else {
                    events.forEachIndexed { index, event ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(36.dp)
                                    .clip(RoundedCornerShape(2.dp))
                                    .background(showcaseAccent(event.third)),
                            )
                            Column {
                                Text(text = event.first, style = MiuixTheme.textStyles.body1)
                                Text(
                                    text = event.second,
                                    style = MiuixTheme.textStyles.footnote1,
                                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                                )
                            }
                        }
                        if (index < events.lastIndex) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
        ) {
            FloatingActionButton(
                onClick = { selectedDay = 24 },
                modifier = Modifier.align(Alignment.BottomEnd),
            ) {
                Icon(
                    imageVector = MiuixIcons.Add,
                    contentDescription = "回到今天",
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
internal fun ProfilePage(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(showcaseBrush(9)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "林",
                    style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                    color = Color.White,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "林小满",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                )
                Text(
                    text = "摄影 / 旅行 / miuix 爱好者",
                    style = MiuixTheme.textStyles.footnote1,
                    color = MiuixTheme.colorScheme.onBackgroundVariant,
                )
            }
            var following by remember { mutableStateOf(false) }
            TextButton(
                text = if (following) "已关注" else "关注",
                onClick = { following = !following },
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)) {
                ProfileStat(value = "128", label = "作品", modifier = Modifier.weight(1f))
                VerticalDivider(modifier = Modifier.height(32.dp))
                ProfileStat(value = "3.2万", label = "粉丝", modifier = Modifier.weight(1f))
                VerticalDivider(modifier = Modifier.height(32.dp))
                ProfileStat(value = "256", label = "关注", modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        var tab by remember { mutableIntStateOf(0) }
        TabRow(
            tabs = listOf("作品", "收藏", "赞过"),
            selectedTabIndex = tab,
            onTabSelected = { tab = it },
            modifier = Modifier.padding(horizontal = 12.dp),
        )
        Spacer(modifier = Modifier.height(12.dp))
        val counts = listOf(6, 4, 5)
        (0 until counts[tab]).toList().chunked(3).forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                row.forEach { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(showcaseBrush(tab * 3 + index)),
                    )
                }
                if (row.size < 3) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)) {
            Column {
                val rows = listOf(
                    Triple("个人资料", "头像、昵称与简介", MiuixIcons.Contacts),
                    Triple("我的收藏", "128 个收藏", MiuixIcons.Favorites),
                    Triple("账号与安全", "登录设备与密码", MiuixIcons.Lock),
                )
                rows.forEachIndexed { index, row ->
                    BasicComponent(
                        title = row.first,
                        summary = row.second,
                        startAction = {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(showcaseBrush(index + 7)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(
                                    imageVector = row.third,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp),
                                )
                            }
                        },
                        onClick = {
                            scope.launch { snackbarHostState.showSnackbar("打开了「${row.first}」") }
                        },
                    )
                    if (index < rows.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
private fun ProfileStat(value: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        )
        Text(
            text = label,
            style = MiuixTheme.textStyles.footnote1,
            color = MiuixTheme.colorScheme.onBackgroundVariant,
        )
    }
}

fun LazyListScope.showcaseContent(
    entry: ShowcaseEntry,
    snackbarHostState: SnackbarHostState,
) {
    item(key = "showcase-${entry.id}") {
        when (entry.id) {
            "news" -> NewsPage(snackbarHostState)
            "video" -> VideoPage(snackbarHostState)
            "music" -> MusicPage()
            "weather" -> WeatherPage()
            "chat" -> ChatPage()
            "store" -> StorePage(snackbarHostState)
            "control" -> ControlCenterPage()
            "gallery" -> GalleryPage()
            "agenda" -> AgendaPage()
            else -> ProfilePage(snackbarHostState)
        }
    }
}
