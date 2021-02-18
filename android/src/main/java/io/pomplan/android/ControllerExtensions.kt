package io.pomplan.android

import io.pomplan.common.elm.Controller
import io.pomplan.common.elm.Elm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


inline fun <reified VD : Elm.ViewData> Controller.subscribeOnState(
    subscriber: String,
    noinline callback: suspend (VD) -> Unit
) = subscribeOnState(subscriber, VD::class) { data -> withContext(Dispatchers.Main) { callback(data) } }

