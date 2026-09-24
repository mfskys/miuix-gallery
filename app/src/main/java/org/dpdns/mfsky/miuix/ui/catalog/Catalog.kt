// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.catalog

/**
 * The destinations of the bottom bar, in order.
 *
 * [BASIC] and [EXTENDED] group the components, [DEMO] lists the full-page
 * showcases, and [SETTINGS] is the settings page, which has no entries of its own.
 */
enum class Category(
    val label: String,
    val shortLabel: String,
) {
    BASIC("基础组件", "基础"),
    EXTENDED("扩展与主题", "扩展"),
    DEMO("演示页面", "演示"),
    SETTINGS("设置", "设置"),
}

/**
 * One entry of the catalog: a single Miuix component plus its demo page id.
 */
data class ComponentEntry(
    val id: String,
    val name: String,
    val category: Category,
    val summary: String,
)

/**
 * The full component catalog, mirroring the components shipped by miuix
 * (see `miuix-ui/basic`, `miuix-ui/overlay`, `miuix-ui/window`, `miuix-preference`).
 */
val componentCatalog: List<ComponentEntry> =
    listOf(
        // region Basic
        ComponentEntry("scaffold", "Scaffold", Category.BASIC, "页面骨架：topBar / bottomBar / FAB / Snackbar"),
        ComponentEntry("topappbar", "TopAppBar", Category.BASIC, "可折叠标题栏，支持大标题与副标题"),
        ComponentEntry("blurtopappbar", "BlurTopAppBar", Category.BASIC, "大标题折叠时逐渐模糊的顶栏"),
        ComponentEntry("floatingnavigationbar", "FloatingNavigationBar", Category.BASIC, "悬浮导航栏"),
        ComponentEntry("surface", "Surface", Category.BASIC, "基础容器，承载背景与内容"),
        ComponentEntry("card", "Card", Category.BASIC, "圆角卡片，可点击"),
        ComponentEntry("basiccomponent", "BasicComponent", Category.BASIC, "通用列表项：标题 / 摘要 / 首尾插槽"),
        ComponentEntry("smalltitle", "SmallTitle", Category.BASIC, "分组小标题"),
        ComponentEntry("text", "Text", Category.BASIC, "文本，含 Miuix 排版样式"),
        ComponentEntry("button", "Button", Category.BASIC, "按钮，含多种尺寸与状态"),
        ComponentEntry("iconbutton", "IconButton", Category.BASIC, "纯图标按钮"),
        ComponentEntry("icon", "Icon", Category.BASIC, "图标，来自 MiuixIcons"),
        ComponentEntry("divider", "Divider", Category.BASIC, "分割线"),
        ComponentEntry("badge", "Badge", Category.BASIC, "角标"),
        ComponentEntry("checkbox", "Checkbox", Category.BASIC, "复选框"),
        ComponentEntry("radiobutton", "RadioButton", Category.BASIC, "单选框"),
        ComponentEntry("switch", "Switch", Category.BASIC, "开关"),
        ComponentEntry("slider", "Slider", Category.BASIC, "滑动条"),
        ComponentEntry("rangeslider", "RangeSlider", Category.BASIC, "范围滑块"),
        ComponentEntry("verticalslider", "VerticalSlider", Category.BASIC, "垂直滑块"),
        ComponentEntry("progressindicator", "ProgressIndicator", Category.BASIC, "进度指示器，确定与不确定"),
        ComponentEntry("textfield", "TextField", Category.BASIC, "输入框"),
        ComponentEntry("searchbar", "SearchBar", Category.BASIC, "搜索栏"),
        ComponentEntry("tabrow", "TabRow", Category.BASIC, "标签行"),
        ComponentEntry("navigationbar", "NavigationBar", Category.BASIC, "底部导航栏"),
        ComponentEntry("navigationrail", "NavigationRail", Category.BASIC, "侧边导航栏"),
        ComponentEntry("floatingactionbutton", "FloatingActionButton", Category.BASIC, "悬浮操作按钮"),
        ComponentEntry("floatingtoolbar", "FloatingToolbar", Category.BASIC, "悬浮工具栏"),
        ComponentEntry("pulltorefresh", "PullToRefresh", Category.BASIC, "下拉刷新"),
        ComponentEntry("snackbar", "Snackbar", Category.BASIC, "轻提示"),
        ComponentEntry("tooltip", "Tooltip", Category.BASIC, "悬浮提示（长按或悬停触发）"),
        ComponentEntry("numberpicker", "NumberPicker", Category.BASIC, "数字选择器"),
        ComponentEntry("colorpicker", "ColorPicker", Category.BASIC, "取色器"),
        ComponentEntry("colorpalette", "ColorPalette", Category.BASIC, "色板"),
        ComponentEntry("breadcrumbbar", "BreadcrumbBar", Category.BASIC, "面包屑导航"),
        ComponentEntry("scrollbar", "ScrollBar", Category.BASIC, "滚动条"),
        ComponentEntry("blur", "Blur", Category.BASIC, "纹理模糊与混合色"),
        ComponentEntry("progressiveblur", "ProgressiveBlur", Category.BASIC, "渐进模糊：方向 / 范围 / 曲线"),
        // endregion
        // region Extended · Preference
        ComponentEntry("arrowpreference", "ArrowPreference", Category.EXTENDED, "带箭头的设置项"),
        ComponentEntry("checkboxpreference", "CheckboxPreference", Category.EXTENDED, "带复选框的设置项"),
        ComponentEntry("radiobuttonpreference", "RadioButtonPreference", Category.EXTENDED, "带单选框的设置项"),
        ComponentEntry("switchpreference", "SwitchPreference", Category.EXTENDED, "带开关的设置项"),
        ComponentEntry("sliderpreference", "SliderPreference", Category.EXTENDED, "带滑动条的设置项"),
        ComponentEntry("rangesliderpreference", "RangeSliderPreference", Category.EXTENDED, "带范围滑块的设置项"),
        ComponentEntry("dropdownpreference", "DropdownPreference", Category.EXTENDED, "下拉选择设置项（Overlay / Window）"),
        ComponentEntry("spinnerpreference", "SpinnerPreference", Category.EXTENDED, "微调选择设置项（Overlay / Window）"),
        ComponentEntry("dropdownmenu", "DropdownMenu", Category.EXTENDED, "下拉菜单（Overlay / Window）"),
        ComponentEntry("icondropdownmenu", "IconDropdownMenu", Category.EXTENDED, "带图标下拉菜单（Overlay / Window）"),
        ComponentEntry("iconcascadingdropdownmenu", "IconCascadingDropdownMenu", Category.EXTENDED, "带图标级联菜单（Overlay / Window）"),
        // endregion
        // region Extended · Overlay
        ComponentEntry("dialog", "Dialog", Category.EXTENDED, "对话框（Overlay / Window）"),
        ComponentEntry("bottomsheet", "BottomSheet", Category.EXTENDED, "底部抽屉（Overlay / Window）"),
        ComponentEntry("listpopup", "ListPopup", Category.EXTENDED, "列表弹窗（Overlay / Window）"),
        ComponentEntry("cascadinglistpopup", "CascadingListPopup", Category.EXTENDED, "级联列表弹窗（Overlay / Window）"),
        // endregion
        // region Appearance · Theme
        ComponentEntry("colorscheme", "ColorScheme", Category.EXTENDED, "亮色 / 暗色配色方案一览"),
        ComponentEntry("textstyles", "TextStyles", Category.EXTENDED, "排版样式一览"),
        ComponentEntry("squircle", "Squircle", Category.EXTENDED, "超椭圆圆角"),
        ComponentEntry("icons", "Icons", Category.EXTENDED, "图标库一览"),
        // endregion
        // region Appearance · Glass
        ComponentEntry("glass", "Glass", Category.EXTENDED, "OS4 玻璃材质：材质 / 描边 / 阴影 / 折射"),
        ComponentEntry("glasstabrow", "GlassTabRow", Category.EXTENDED, "玻璃标签行"),
        ComponentEntry("glassnavigationbar", "GlassNavigationBar", Category.EXTENDED, "玻璃底部导航栏"),
        ComponentEntry("glasstopappbar", "GlassTopAppBar", Category.EXTENDED, "玻璃标题栏"),
        ComponentEntry("glasspopup", "GlassPopup", Category.EXTENDED, "玻璃弹窗与菜单"),
        ComponentEntry("glasssegmentedtabrow", "GlassSegmentedTabRow", Category.EXTENDED, "玻璃分段控件：隐私与安全页那种（白色轨道，选中靠字重，无可见滑块）"),
        // endregion
    )

fun entriesOf(category: Category): List<ComponentEntry> = componentCatalog.filter { it.category == category }

fun searchCatalog(query: String): List<ComponentEntry> {
    val keyword = query.trim()
    if (keyword.isEmpty()) return emptyList()
    return componentCatalog.filter {
        it.name.contains(keyword, ignoreCase = true) || it.summary.contains(keyword, ignoreCase = true)
    }
}
