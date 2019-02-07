package com.tenty.tentyandroid.audio.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.audio.presentation.AudioPresenter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.dialog_audio.*
import javax.inject.Inject

class AudioFragment: Fragment(), View.OnClickListener {
    @Inject
    lateinit var presenter: AudioPresenter

    private var phoneNumber: String? = null

    companion object {
        const val AUDIO_FRAGMENT_TAG = "AudioFragment"

        private const val BUNDLE_PHONE_NUMBER = "PhoneNumber"

        fun newAudioFragment(phoneNumber: String): AudioFragment {
            val dialog = AudioFragment()

            val bundle = Bundle()
            bundle.putString(BUNDLE_PHONE_NUMBER, phoneNumber)
            dialog.arguments = bundle

            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null)
            phoneNumber = bundle.getString(BUNDLE_PHONE_NUMBER)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_audio, container, false)

    override fun onClick(view: View?) {
    }

    private fun displayError() {
        button_play_pause.visibility = View.GONE
        seek_bar.visibility = View.GONE
        text_error.visibility = View.VISIBLE
    }
}