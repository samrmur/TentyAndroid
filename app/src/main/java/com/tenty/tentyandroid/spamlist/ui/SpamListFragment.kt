package com.tenty.tentyandroid.spamlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.audio.ui.AudioFragment.Companion.AUDIO_FRAGMENT_TAG
import com.tenty.tentyandroid.audio.ui.AudioFragment.Companion.newAudioFragment
import com.tenty.tentyandroid.spamlist.viewmodel.SpamListViewModel
import com.tenty.tentyandroid.spamlist.viewmodel.SpamListViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_spam_list.*
import javax.inject.Inject

class SpamListFragment: Fragment() {
    @Inject
    lateinit var factory: SpamListViewModelFactory

    private lateinit var viewModel: SpamListViewModel

    companion object {
        const val SPAM_LIST_FRAGMENT_TAG = "SpamListFragment"
        fun newSpamListInstance(): SpamListFragment = SpamListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_spam_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(SpamListViewModel::class.java)
        setupRecyclerView()
        getSpam()
    }

    private fun setupRecyclerView() {
        if (spam_list.adapter == null) {
            spam_list.adapter = SpamListAdapter {
                newAudioFragment(it.phoneNumber).show(fragmentManager, AUDIO_FRAGMENT_TAG)
            }

            with(spam_list) {
                layoutManager = LinearLayoutManager(activity)
                addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
                itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun getSpam() {
        viewModel.getSpamList().observe(viewLifecycleOwner, Observer { spam ->
            (spam_list.adapter as SpamListAdapter).updateList(spam)

            if (spam_list.adapter!!.itemCount > 0)
                empty_view.visibility = View.GONE
            else
                empty_view.visibility = View.VISIBLE
        })
    }
}