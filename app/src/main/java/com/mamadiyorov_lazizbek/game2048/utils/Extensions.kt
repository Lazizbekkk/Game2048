package com.mamadiyorov_lazizbek.game2048.utils

import com.mamadiyorov_lazizbek.game2048.R

fun setBackgroundMatrixElements(index: Int): Int =
    when (index) {
        0 -> R.drawable.bg_item_0
        2 -> R.drawable.bg_item_2
        4 -> R.drawable.bg_item_4
        8 -> R.drawable.bg_item_8
        16 -> R.drawable.bg_item_16
        32 -> R.drawable.bg_item_32
        64 -> R.drawable.bg_item_64
        128 -> R.drawable.bg_item_128
        256 -> R.drawable.bg_item_256
        512 -> R.drawable.bg_item_512
        1024 -> R.drawable.bg_item_1024
        else -> R.drawable.bg_item_2048
    }
object MyConstants{
    const val COUNT_GAME = "COUNT_GAME"
    const val COUNT_GAME_3 = "COUNT_GAME3"
    const val COUNT_GAME_4 = "COUNT_GAME4"
    const val COUNT_GAME_5 = "COUNT_GAME5"
    const val GAME_ELEMENTS = "GAME-ELEMENTS"
    const val GAME_ELEMENTS_3 = "GAME-ELEMENTS3"
    const val GAME_ELEMENTS_4 = "GAME-ELEMENTS4"
    const val GAME_ELEMENTS_5 = "GAME-ELEMENTS5"
    const val RECORD_GAME = "RECORD_GAME"
    const val RECORD_GAME_3 = "RECORD_GAME3"
    const val RECORD_GAME_4 = "RECORD_GAME4"
    const val RECORD_GAME_5 = "RECORD_GAME5"
    const val SCORE_GAME = "SCORE_GAME"
    const val SCORE_GAME_3 = "SCORE_GAME3"
    const val SCORE_GAME_4 = "SCORE_GAME4"
    const val SCORE_GAME_5 = "SCORE_GAME5"
    const val STATE_SCREEN = "STATE_SCREEN"
    const val LEVEL = "level"
    const val LEVEL_SIZE = "levelSize"
}
