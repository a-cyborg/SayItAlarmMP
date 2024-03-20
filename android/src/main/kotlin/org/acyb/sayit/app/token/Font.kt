package org.acyb.sayit.app.token

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Font {
    private val base = TextStyle(fontFamily = FontFamily.Monospace)

    object Display {
        val l = base.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        )

        val m = base.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        )

        val s = base.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        )
    }

    val display = Display

    object Headline {
        private val headlineBase = base.copy(fontWeight = FontWeight.Medium)

        val l = headlineBase.copy(
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        )

        val m = headlineBase.copy(
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        )

        val s = headlineBase.copy(
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        )
    }

    val headline = Headline

    object Title {
        val l = base.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        )

        val m = base.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )

        val s = base.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        )
    }

    val title = Title

    object Label {
        val l = base.copy(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,   // 14.sp
            lineHeight = 23.sp,
            letterSpacing = 0.1.sp
        )

        val m = base.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )

        val s = base.copy(
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    }

    val label = Label

    object Body {
        private val bodyBase = base.copy(fontWeight = FontWeight.Medium)

        val l = bodyBase.copy(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )

        val m = bodyBase.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        )

        val s = bodyBase.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
    }

    val body = Body
}