package io.pomplan.common

import io.pomplan.common.domain.second
import kotlinx.coroutines.*


open class Timer(
    var tickAction: () -> Unit = { /* do nothing */ }
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private var job: Job? = null

    fun run() {
        job = scope.launch {
            while (isActive) {
                delay(1.second)
                tickAction()
            }
        }
    }

    fun stop() {
        job?.cancel(message = "Stop timer")
        job = null
    }
}
