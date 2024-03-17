package org.acyb.sayit.app.token

import androidx.compose.ui.graphics.Color

object Color {
    object Surface {
        val standard = ColorPalette.white
        val light = ColorPalette.Secondary.Gray.light
        val dark = ColorPalette.Secondary.Gray.dark
        val moderate = ColorPalette.Secondary.Gray.medium

        object Success {
            val light = ColorPalette.Secondary.Green.light
            val dark = ColorPalette.Secondary.Green.dark
        }
    }

    object Text {
        val standard = ColorPalette.black
        val subtle = ColorPalette.Secondary.Gray.medium
        val inverse = ColorPalette.white
        val success = ColorPalette.Secondary.Green.light
        val info = ColorPalette.Secondary.Blue.light
        val warning = ColorPalette.Secondary.Pink.light
        val danger = ColorPalette.Secondary.Red.light
        val accent = ColorPalette.Primary.Aqua.light
    }

    val text = Text

    private object ColorPalette {
        val white = Color(0xFFFFFFFF)
        val black = Color(0xFF000000)

        object Primary {
            object Aqua {
                val light = Color(0xFF427658)
                val dark = Color(0xFF689D6A)
            }
        }

        object Secondary {
            object Red {
                val light = Color(0xFF9D0006)
                val dark = Color(0xFFCC241D)
            }

            object Green {
                val light = Color(0xFF79740E)
                val dark = Color(0xFF98971A)
            }

            object Yellow {
                val light = Color(0xFFB57614)
                val dark = Color(0xFFD79921)
            }

            object Blue {
                val light = Color(0xFF076678)
                val dark = Color(0xFF458588)
            }

            object Pink {
                val light = Color(0xFF8F3F71)
                val dark = Color(0xFFB16286)
            }

            object Gray {
                val faded = Color(0xFFA89984)
                val light = Color(0xFF928374)
                val medium = Color(0xFF7C6F64)
                val dark = Color(0xFF282828)
            }
        }
    }
}