package com.tenty.tentyandroid.audio.presentation

import android.media.MediaPlayer

interface AudioView {
    fun onPlayerError(player: MediaPlayer?, what: Int, extra: Int)
}