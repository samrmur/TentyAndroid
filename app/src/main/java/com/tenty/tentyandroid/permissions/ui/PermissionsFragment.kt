package com.tenty.tentyandroid.permissions.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.tenty.tentyandroid.R
import com.tenty.tentyandroid.main.presentation.MainNavigator
import com.tenty.tentyandroid.util.extensions.showToast
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.fragment_permissions.*
import javax.inject.Inject

class PermissionsFragment: Fragment(), HasSupportFragmentInjector, View.OnClickListener {
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigator: MainNavigator

    private var requiresSmsPermission: Boolean = false
    private var requiresPhoneStatePermission: Boolean = false
    private var requiresCallLogPermission: Boolean = false

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    companion object {
        const val PERMISSIONS_FRAGMENT_TAG = "PermissionsFragment"

        private const val PERMISSIONS_REQUEST = 0
        private const val BUNDLE_REQUIRES_SMS = "RequiresSms"
        private const val BUNDLE_REQUIRES_PHONE_STATE = "RequiresPhoneState"
        private const val BUNDLE_REQUIRES_CALL_LOG = "RequiresCallLog"

        fun newPermissionsInstance(
            requiresSms: Boolean,
            requiresPhoneState: Boolean,
            requiresCallLog: Boolean
        ): PermissionsFragment {
            val fragment = PermissionsFragment()

            val bundle = Bundle()
            bundle.putBoolean(BUNDLE_REQUIRES_SMS, requiresSms)
            bundle.putBoolean(BUNDLE_REQUIRES_PHONE_STATE, requiresPhoneState)
            bundle.putBoolean(BUNDLE_REQUIRES_CALL_LOG, requiresCallLog)
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            requiresSmsPermission = bundle.getBoolean(BUNDLE_REQUIRES_SMS)
            requiresPhoneStatePermission = bundle.getBoolean(BUNDLE_REQUIRES_PHONE_STATE)
            requiresCallLogPermission = bundle.getBoolean(BUNDLE_REQUIRES_CALL_LOG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_permissions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_accept.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val permissionsRequired: ArrayList<String> = arrayListOf()

        if (requiresSmsPermission)
            permissionsRequired.add(Manifest.permission.RECEIVE_SMS)
        if (requiresPhoneStatePermission)
            permissionsRequired.add(Manifest.permission.READ_PHONE_STATE)
        if (requiresCallLogPermission)
            permissionsRequired.add(Manifest.permission.READ_CALL_LOG)

        if (permissionsRequired.isNotEmpty())
            requestPermissions(
                permissionsRequired.toArray(arrayOf()),
                PERMISSIONS_REQUEST
            )
        else
            navigator.toSpamList(false, true)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST -> {
                if (hasAllPermissions(grantResults))
                    navigator.toSpamList(false, true)
                else {
                    showToast(R.string.msg_permission_denied)
                    checkPermissions()
                }
            }
        }
    }

    private fun checkPermissions() {
        context?.let {
            requiresSmsPermission = ContextCompat.checkSelfPermission(it, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
            requiresPhoneStatePermission = ContextCompat.checkSelfPermission(it, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
            requiresCallLogPermission = ContextCompat.checkSelfPermission(it, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED
        }
    }

    private fun hasAllPermissions(grantResults: IntArray): Boolean {
        grantResults.forEach { result ->
            if (result == PackageManager.PERMISSION_DENIED)
                return false
        }

        return true
    }
}