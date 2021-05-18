package io.pomplan.android

import androidx.lifecycle.ViewModel
import io.pomplan.common.elm.Controller
import io.pomplan.common.inject


class AppViewModel : ViewModel() {
    private val stateMachine: Controller by inject()

    
}
