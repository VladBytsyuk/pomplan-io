package io.pomplan.android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import io.pomplan.common.elm.Controller
import io.pomplan.common.elm.Elm
import io.pomplan.common.elm.State
import io.pomplan.common.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private val controller: Controller = inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.subscribeOnState<State>("this") { data ->
            val pomodoro = data.pomodoro
            findViewById<TextView>(R.id.timerText).text = pomodoro.elapsedTime.toString()
            findViewById<Button>(R.id.btnPlayPause).text =
                if (pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) "Play" else "Pause"
        }
        findViewById<Button>(R.id.btnPlayPause).setOnClickListener {
            val action = if (controller.currentState.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK))
                Action.UserClick.Button.Play else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
    }

    override fun onStart() {
        super.onStart()
        controller.attach()
    }

    override fun onStop() {
        controller.detach()
        super.onStop()
    }
}


private inline fun <reified VD : Elm.ViewData> Controller.subscribeOnState(
    subscriber: String,
    noinline callback: suspend (VD) -> Unit
) = subscribeOnState(subscriber, VD::class) { data -> withContext(Dispatchers.Main) { callback(data) } }
