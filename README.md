# miuix-gallery

> **miuix-gallery** —— [miuix](https://github.com/mfskys/miuix) 的 Android 演示应用（组件展示）。
> 界面与主题全部由 miuix 组件构建，本仓库只做展示，不含业务逻辑。

## 它有什么

- 底栏四个目的地：**基础 / 扩展与浮层 / 主题与玻璃 / 设置**
- 60+ 个组件演示页，每页展示一个 miuix 组件
- **设置页是真的能改整个 App 的**：色彩模式（跟随系统 / 浅色 / 深色 / 动态取色）、强调色、配色风格、色彩规范、顶栏模糊、玻璃底栏开关，改完立即生效并持久化

## 构建

```
./gradlew :app:assembleDebug
```

- JDK 21（Android Studio 自带的即可）
- Android SDK；`minSdk 33`、`targetSdk` 见 `app/build.gradle.kts`
- Kotlin / AGP / Compose 版本统一在 `gradle/libs.versions.toml`

## 目录

| 路径 | 内容 |
| --- | --- |
| `app/src/main/java/org/dpdns/mfsky/miuix/ui/` | App 自身代码：骨架、组件清单、设置、演示页 |
| `app/src/main/java/top/yukonga/miuix/kmp/` | 上游尚未发布的实现，临时内联；上游发布后改为 Maven 依赖 |
| `docs/` | 说明文档 |

## 依赖

`miuix-ui` / `miuix-preference` / `miuix-icons` / `miuix-blur` / `miuix-shader`，版本见 `gradle/libs.versions.toml` 的 `miuix`。

## 许可

Apache-2.0，见 [LICENSE](LICENSE)。
