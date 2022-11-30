package com.simform.todo.presentation.ui.alert

import android.app.Activity
import androidx.annotation.ColorRes
import com.simform.todo.R
import com.tapadoo.alerter.Alerter as AlerterImpl

class Alerter {

    fun showWarning(
        title: String,
        message: String,
        activity: Activity
    ) = showAlert(
        title = title,
        message = message,
        activity = activity,
        backgroundColor = R.color.warning_color
    )

    fun showError(
        title: String,
        message: String,
        activity: Activity
    ) = showAlert(
        title = title,
        message = message,
        activity = activity,
        backgroundColor = R.color.error_color
    )

    fun showSuccess(
        title: String,
        message: String,
        activity: Activity
    ) = showAlert(
        title = title,
        message = message,
        activity = activity,
        backgroundColor = R.color.success_color
    )

    fun show(
        title: String,
        message: String,
        activity: Activity
    ) = showAlert(
        title = title,
        message = message,
        activity = activity,
        backgroundColor = R.color.gray
    )

    private fun showAlert(
        title: String,
        message: String,
        activity: Activity,
        @ColorRes backgroundColor: Int
    ) = AlerterImpl.create(activity)
        .setTitle(title)
        .setText(message)
        .setBackgroundColorRes(backgroundColor)
        .show()
}