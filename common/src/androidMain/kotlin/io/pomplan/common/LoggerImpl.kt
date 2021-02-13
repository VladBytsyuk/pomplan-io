package io.pomplan.common

import android.util.Log


actual class LoggerImpl : Logger {
    override fun info(tag: String, message: String) {
        Log.i("PomPlan-$tag", message)
    }

    override fun debug(tag: String, message: String) {
        Log.d("PomPlan-$tag", message)
    }

    override fun warning(tag: String, message: String) {
        Log.w("PomPlan-$tag", message)
    }

    override fun error(tag: String, message: String) {
        Log.e("PomPlan-$tag", message)
    }

    override fun fatal(tag: String, message: String) {
        Log.e("PomPlan-$tag", "FATAL: $message")
    }
}
