package io.pomplan.common.elm

import io.pomplan.common.domain.Timer


data class State(
    val timer: Timer,
) : Elm.State
