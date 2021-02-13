package io.pomplan.common.elm

import io.pomplan.common.domain.Pomodoro


data class State(
    val pomodoro: Pomodoro,
) : Elm.State, Elm.ViewData
