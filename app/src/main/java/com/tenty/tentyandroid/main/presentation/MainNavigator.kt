package com.tenty.tentyandroid.main.presentation

import androidx.fragment.app.Fragment
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.main.ui.MainActivity
import com.tenty.tentyandroid.permissions.ui.PermissionsFragment.Companion.PERMISSIONS_FRAGMENT_TAG
import com.tenty.tentyandroid.permissions.ui.PermissionsFragment.Companion.newPermissionsInstance
import com.tenty.tentyandroid.spamlist.ui.SpamListFragment.Companion.SPAM_LIST_FRAGMENT_TAG
import com.tenty.tentyandroid.spamlist.ui.SpamListFragment.Companion.newSpamListInstance
import com.tenty.tentyandroid.util.extensions.addFragment
import com.tenty.tentyandroid.util.extensions.addFragmentToBackStack
import com.tenty.tentyandroid.util.extensions.addFragmentWithAnim

class MainNavigator(internal val activity: MainActivity) {
    fun toPermissions(
        requiresSms: Boolean,
        requiresPhoneState: Boolean,
        requiresCallLog: Boolean,
        addToBackStack: Boolean
    ) {
        createFragment(PERMISSIONS_FRAGMENT_TAG, R.id.fragment_container, addToBackStack, false) {
            newPermissionsInstance(requiresSms, requiresPhoneState, requiresCallLog)
        }
    }

    fun toSpamList(addToBackStack: Boolean, animate: Boolean) {
        createFragment(SPAM_LIST_FRAGMENT_TAG, R.id.fragment_container, addToBackStack, animate) {
            newSpamListInstance()
        }
    }

    private fun createFragment(
        tag: String,
        layoutId: Int,
        addToBackStack: Boolean,
        animate: Boolean,
        fragment: () -> Fragment
    ) {
        if (addToBackStack)
            activity.addFragmentToBackStack(tag, layoutId) { fragment() }
        else if (animate)
            activity.addFragmentWithAnim(tag, layoutId) { fragment() }
        else
            activity.addFragment(tag, layoutId) { fragment() }
    }
}