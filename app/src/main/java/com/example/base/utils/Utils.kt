package com.example.base.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.base.R
import com.tapadoo.alerter.Alerter

fun showMessage(context: Context, message: String?) {
    Toast.makeText(context, message ?: context.resources.getString(R.string.app_name), Toast.LENGTH_SHORT)
        .show()
}

fun showNoInternetAlert(activity: Activity) {
    Alerter.create(activity)
        .setTitle(activity.resources.getString(R.string.connection_error))
        .setText(activity.resources.getString(R.string.no_internet))
        .setIcon(R.drawable.ic_no_internet)
        .setBackgroundColorRes(R.color.black)
        .enableClickAnimation(true)
        .enableSwipeToDismiss()
        .show()
}

fun showLoadingDialog(activity: Activity?, hint: String?): Dialog? {
    if (activity == null || activity.isFinishing) {
        return null
    }
    val progressDialog = Dialog(activity)
    progressDialog.show()
    if (progressDialog.window != null) {
        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    progressDialog.setContentView(R.layout.progress_dialog)
    val tvHint = progressDialog.findViewById<TextView>(R.id.tv_hint)
    if (!hint.isNullOrEmpty()) {
        tvHint.visibility = View.VISIBLE
        tvHint.text = hint
    } else {
        tvHint.visibility = View.GONE
    }

    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    progressDialog.show()

    return progressDialog
}

fun hideLoadingDialog(mProgressDialog: Dialog?, activity: Activity?) {
    if (activity != null && !activity.isFinishing && mProgressDialog != null && mProgressDialog.isShowing) {
        mProgressDialog.dismiss()
    }
}

@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

fun String.isEmailValid(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()