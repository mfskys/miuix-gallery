// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.ui.unit.LayoutDirection

/**
 * The figures a cascading popup stacks on.
 *
 * A partial port of the `CascadingPopupDefaults` that the inlined `miuix-glass` sources expect;
 * only the members the glass module reads are provided here.
 */
object CascadingPopupDefaults {

    /** How far the trigger row's chevron turns once the second menu is open, in degrees. */
    fun arrowRotation(layoutDirection: LayoutDirection): Float =
        if (layoutDirection == LayoutDirection.Ltr) -90f else 90f
}
