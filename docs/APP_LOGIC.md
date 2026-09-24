# App 逻辑说明

本文说明这个演示 App 自身是怎么组织的。

## 1. 启动链

```
MainActivity
  └─ MIUIXTheme(settings)          主题，由 miuix 的 ThemeController 驱动
      └─ MiuixCatalogApp()         骨架：Scaffold + 顶栏 + 内容 + 底栏
```

- `MainActivity` 读持久化设置，并通过 `CompositionLocal` 下发（`LocalAppSettings` / `LocalSetAppSettings`）
- 主题随设置变化重建，改外观立即生效

## 2. 导航

底栏四个目的地由 `ui/catalog/Catalog.kt` 的 `Category` 枚举定义：

| 目的地 | 内容 |
| --- | --- |
| 基础 | 基础组件 |
| 扩展与浮层 | 设置项组件 + 弹窗类 |
| 主题与玻璃 | 配色/排版/圆角/图标 + 玻璃材质组件 |
| 设置 | 设置页（无组件条目） |

底栏默认用玻璃形态；设置里可切换为标准形态。

## 3. 组件清单与路由

`ui/catalog/Catalog.kt`：

- `ComponentEntry(id, name, category, summary)` 一条组件
- `componentCatalog` 全部条目
- `entriesOf(category)` 按分类取；`searchCatalog(query)` 搜索

`ui/screens/ComponentScreen.kt` 的 `when (entry.id)` 把 id 映射到对应演示页。三者必须一一对应（当前 63 / 63）。

## 4. 设置页

`ui/settings/AppSettings.kt` + `ui/screens/SettingsScreen.kt`

- `AppSettings` 是数据类，每次改动写入 `SharedPreferences`，重启后保留
- 可改：色彩模式、强调色、配色风格、色彩规范、顶栏模糊、玻璃底栏
- 另有「恢复默认设置」

## 5. 演示页

`ui/sections/` 下每个文件含若干 `fun LazyListScope.xxxSection()`，由路由调用。

新增一个演示页要改三处：

1. 写 `xxxSection()`
2. 清单加一条 `ComponentEntry`
3. 路由加一个 `"id" -> xxxSection()` 分支

## 6. 骨架细节

- **顶栏高度是实测的**：大标题可能换行，内容内边距按实测高度对齐，避免标题与列表重叠
- **滚动位置**：首页与详情页各自持有独立的滚动状态，从详情返回时首页停在原处
- **模糊**：顶栏后方的渐进模糊可在设置里关闭；关闭时顶栏改为不透明，避免内容穿透
