package com.tenty.tentyandroid.audio.presentation

import android.media.MediaPlayer
import com.tenty.tentyandroid.util.lifecycle.LifecycleJob
import kotlinx.coroutines.experimental.*
import javax.inject.Inject

class AudioPresenter @Inject constructor(
    private val view: AudioView,
    private val job: LifecycleJob,
    private val mediaPlayer: MediaPlayer
): MediaPlayer.OnErrorListener {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        mediaPlayer.prepare()
    }

    override fun onError(player: MediaPlayer?, what: Int, extra: Int): Boolean {
        view.onPlayerError(player, what, extra)
        return true
    }

    fun start(url: String) {
        scope.launch {
            if (mediaPlayer.isPlaying)
                mediaPlayer.stop()

            mediaPlayer.reset()
            mediaPlayer.prepare()

            mediaPlayer.setDataSource(url)
            mediaPlayer.start()
        }
    }

    fun stop() {
        scope.launch {
            mediaPlayer.stop()
        }
    }

    fun resume() {
        scope.launch {
            mediaPlayer.start()
        }
    }

    fun pause() {
        scope.launch {
            mediaPlayer.stop()
        }
    }
}