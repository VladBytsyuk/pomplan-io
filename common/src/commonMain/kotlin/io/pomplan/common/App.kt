package io.pomplan.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import io.pomplan.common.domain.Pomodoro
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import io.pomplan.common.elm.Controller
import io.pomplan.common.elm.State
import io.pomplan.common.ui.Theme
import io.pomplan.common.ui.TimerView
import org.kodein.di.instance


@Composable
fun App() {
    val controller = kodein.instance<Controller>()
    var data by remember { mutableStateOf(controller.currentState) }
    controller.subscribeOnState("tag", State::class) { data = it }

    MaterialTheme {
        Column {
            TimerView(theme = Theme.Light, text = data.pomodoro.elapsedTime.toString())
            Button(
                onClick = {
                    val action = when (data.pomodoro.mode) {
                        PRE_WORK, PRE_BREAK -> Action.UserClick.Button.Play
                        WORK, BREAK -> Action.UserClick.Button.Pause
                    }
                    controller.setAction(action)
                }
            ) {
                val text = when (data.pomodoro.mode) {
                    PRE_WORK, PRE_BREAK -> "Play"
                    WORK, BREAK -> "Pause"
                }
                Text(text)
            }
        }
    }
}
