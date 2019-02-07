package com.tenty.tentyandroid.util.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(resourceId: Int, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, resourceId, length).show()
}