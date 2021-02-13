package io.pomplan.common

import io.kotest.core.spec.style.ShouldSpec
import io.pomplan.common.elm.Action
import io.pomplan.common.elm.Effect
import io.pomplan.common.elm.Elm
import io.pomplan.common.elm.State


class StateModificationTest : ShouldSpec({
    val controller: Elm.Controller<State, Action, Effect> = inject()

    context(name = "") {

    }
})
