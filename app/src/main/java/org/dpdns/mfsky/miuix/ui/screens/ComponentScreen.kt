// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package org.dpdns.mfsky.miuix.ui.screens

import androidx.compose.foundation.lazy.LazyListScope
import org.dpdns.mfsky.miuix.ui.catalog.ComponentEntry
import org.dpdns.mfsky.miuix.ui.sections.arrowSection
import org.dpdns.mfsky.miuix.ui.sections.badgeSection
import org.dpdns.mfsky.miuix.ui.sections.basicComponentSection
import org.dpdns.mfsky.miuix.ui.sections.blurTopAppBarSection
import org.dpdns.mfsky.miuix.ui.sections.bottomSheetSection
import org.dpdns.mfsky.miuix.ui.sections.breadcrumbBarSection
import org.dpdns.mfsky.miuix.ui.sections.buttonSection
import org.dpdns.mfsky.miuix.ui.sections.cardSection
import org.dpdns.mfsky.miuix.ui.sections.cascadingListPopupSection
import org.dpdns.mfsky.miuix.ui.sections.checkboxPreferenceSection
import org.dpdns.mfsky.miuix.ui.sections.colorPaletteSection
import org.dpdns.mfsky.miuix.ui.sections.colorSchemeSection
import org.dpdns.mfsky.miuix.ui.sections.checkboxSection
import org.dpdns.mfsky.miuix.ui.sections.colorPickerSection
import org.dpdns.mfsky.miuix.ui.sections.dialogSection
import org.dpdns.mfsky.miuix.ui.sections.dividerSection
import org.dpdns.mfsky.miuix.ui.sections.dropdownMenuSection
import org.dpdns.mfsky.miuix.ui.sections.dropdownSection
import org.dpdns.mfsky.miuix.ui.sections.floatingActionButtonSection
import org.dpdns.mfsky.miuix.ui.sections.floatingNavigationBarSection
import org.dpdns.mfsky.miuix.ui.sections.floatingToolbarSection
import org.dpdns.mfsky.miuix.ui.sections.rangeSliderPreferenceSection
import org.dpdns.mfsky.miuix.ui.sections.rangeSliderSection
import org.dpdns.mfsky.miuix.ui.sections.scaffoldSection
import org.dpdns.mfsky.miuix.ui.sections.glassSegmentedTabRowSection
import org.dpdns.mfsky.miuix.ui.sections.topAppBarSection
import org.dpdns.mfsky.miuix.ui.sections.glassNavigationBarSection
import org.dpdns.mfsky.miuix.ui.sections.glassPopupSection
import org.dpdns.mfsky.miuix.ui.sections.glassSection
import org.dpdns.mfsky.miuix.ui.sections.glassTabRowSection
import org.dpdns.mfsky.miuix.ui.sections.glassTopAppBarSection
import org.dpdns.mfsky.miuix.ui.sections.iconButtonSection
import org.dpdns.mfsky.miuix.ui.sections.iconCascadingDropdownMenuSection
import org.dpdns.mfsky.miuix.ui.sections.iconDropdownMenuSection
import org.dpdns.mfsky.miuix.ui.sections.iconSection
import org.dpdns.mfsky.miuix.ui.sections.iconsSection
import org.dpdns.mfsky.miuix.ui.sections.listPopupSection
import org.dpdns.mfsky.miuix.ui.sections.navigationBarSection
import org.dpdns.mfsky.miuix.ui.sections.navigationRailSection
import org.dpdns.mfsky.miuix.ui.sections.numberPickerSection
import org.dpdns.mfsky.miuix.ui.sections.placeholderSection
import org.dpdns.mfsky.miuix.ui.sections.progressIndicatorSection
import org.dpdns.mfsky.miuix.ui.sections.progressiveBlurSection
import org.dpdns.mfsky.miuix.ui.sections.pullToRefreshSection
import org.dpdns.mfsky.miuix.ui.sections.radioButtonPreferenceSection
import org.dpdns.mfsky.miuix.ui.sections.radioButtonSection
import org.dpdns.mfsky.miuix.ui.sections.scrollBarSection
import org.dpdns.mfsky.miuix.ui.sections.searchBarSection
import org.dpdns.mfsky.miuix.ui.sections.sliderPreferenceSection
import org.dpdns.mfsky.miuix.ui.sections.sliderSection
import org.dpdns.mfsky.miuix.ui.sections.smallTitleSection
import org.dpdns.mfsky.miuix.ui.sections.snackbarSection
import org.dpdns.mfsky.miuix.ui.sections.spinnerSection
import org.dpdns.mfsky.miuix.ui.sections.squircleSection
import org.dpdns.mfsky.miuix.ui.sections.surfaceSection
import org.dpdns.mfsky.miuix.ui.sections.switchPreferenceSection
import org.dpdns.mfsky.miuix.ui.sections.switchSection
import org.dpdns.mfsky.miuix.ui.sections.tabRowSection
import org.dpdns.mfsky.miuix.ui.sections.textFieldSection
import org.dpdns.mfsky.miuix.ui.sections.textSection
import org.dpdns.mfsky.miuix.ui.sections.textureBlurSection
import org.dpdns.mfsky.miuix.ui.sections.textStylesSection
import org.dpdns.mfsky.miuix.ui.sections.tooltipSection
import org.dpdns.mfsky.miuix.ui.sections.verticalSliderSection
import top.yukonga.miuix.kmp.basic.SnackbarHostState

fun LazyListScope.componentContent(
    entry: ComponentEntry,
    snackbarHostState: SnackbarHostState,
) {
    when (entry.id) {
        "scaffold" -> scaffoldSection()
        "topappbar" -> topAppBarSection()
        "glasssegmentedtabrow" -> glassSegmentedTabRowSection()
        "text" -> textSection()
        "button" -> buttonSection()
        "card" -> cardSection(snackbarHostState)
        "surface" -> surfaceSection(snackbarHostState)
        "divider" -> dividerSection()
        "scrollbar" -> scrollBarSection()
        "icon" -> iconSection()
        "iconbutton" -> iconButtonSection(snackbarHostState)
        "basiccomponent" -> basicComponentSection()
        "smalltitle" -> smallTitleSection()
        "switch" -> switchSection()
        "checkbox" -> checkboxSection()
        "radiobutton" -> radioButtonSection()
        "slider" -> sliderSection()
        "progressindicator" -> progressIndicatorSection()
        "textfield" -> textFieldSection()
        "searchbar" -> searchBarSection()
        "tabrow" -> tabRowSection()
        "navigationbar" -> navigationBarSection()
        "navigationrail" -> navigationRailSection()
        "floatingactionbutton" -> floatingActionButtonSection(snackbarHostState)
        "floatingtoolbar" -> floatingToolbarSection(snackbarHostState)
        "pulltorefresh" -> pullToRefreshSection()
        "numberpicker" -> numberPickerSection()
        "colorpicker" -> colorPickerSection()
        "colorpalette" -> colorPaletteSection()
        "breadcrumbbar" -> breadcrumbBarSection()
        "badge" -> badgeSection()
        "tooltip" -> tooltipSection()
        "snackbar" -> snackbarSection(snackbarHostState)
        "dialog" -> dialogSection()
        "bottomsheet" -> bottomSheetSection()
        "dropdownpreference" -> dropdownSection()
        "dropdownmenu" -> dropdownMenuSection()
        "spinnerpreference" -> spinnerSection()
        "arrowpreference" -> arrowSection(snackbarHostState)
        "checkboxpreference" -> checkboxPreferenceSection()
        "radiobuttonpreference" -> radioButtonPreferenceSection()
        "switchpreference" -> switchPreferenceSection()
        "sliderpreference" -> sliderPreferenceSection()
        "icondropdownmenu" -> iconDropdownMenuSection()
        "iconcascadingdropdownmenu" -> iconCascadingDropdownMenuSection()
        "listpopup" -> listPopupSection()
        "cascadinglistpopup" -> cascadingListPopupSection()
        "colorscheme" -> colorSchemeSection()
        "textstyles" -> textStylesSection()
        "squircle" -> squircleSection()
        "blur" -> textureBlurSection()
        "progressiveblur" -> progressiveBlurSection()
        "rangeslider" -> rangeSliderSection()
        "verticalslider" -> verticalSliderSection()
        "rangesliderpreference" -> rangeSliderPreferenceSection()
        "floatingnavigationbar" -> floatingNavigationBarSection()
        "blurtopappbar" -> blurTopAppBarSection()
        "icons" -> iconsSection()
        "glass" -> glassSection()
        "glasstabrow" -> glassTabRowSection()
        "glassnavigationbar" -> glassNavigationBarSection()
        "glasstopappbar" -> glassTopAppBarSection()
        "glasspopup" -> glassPopupSection()
        else -> placeholderSection(entry)
    }
}
