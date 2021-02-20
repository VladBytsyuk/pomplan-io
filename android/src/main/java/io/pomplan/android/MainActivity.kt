package io.pomplan.android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.pomplan.android.ui_kit.TimerProgressView
import io.pomplan.common.domain.Pomodoro.Mode.*
import io.pomplan.common.elm.Action
import io.pomplan.common.elm.Controller
import io.pomplan.common.elm.State
import io.pomplan.common.inject


class MainActivity : AppCompatActivity() {
    private val controller: Controller by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListeners()
        controller.subscribeOnState<State>("this") { render(it) }
    }

    override fun onStart() {
        super.onStart()
        controller.attach()
    }

    override fun onStop() {
        controller.detach()
        super.onStop()
    }

    private fun setOnClickListeners() {
        findViewById<Button>(R.id.btnPlayPause).setOnClickListener {
            val action = if (controller.currentState.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK))
                Action.UserClick.Button.Play else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
        findViewById<Button>(R.id.btnStop).setOnClickListener {
            controller.setAction(Action.UserClick.Button.Stop())
        }
        findViewById<Button>(R.id.btnSkip).setOnClickListener {
            controller.setAction(Action.UserClick.Button.Skip())
        }
    }

    private fun render(data: State) {
        val pomodoro = data.pomodoro
        findViewById<TimerProgressView>(R.id.timerProgress).data = TimerProgressView.Data(
            goal = pomodoro.goalTime.milliseconds,
            elapsed = pomodoro.elapsedTime.milliseconds,
            isWork = pomodoro.mode in listOf(WORK, PRE_WORK)
        )
        findViewById<TextView>(R.id.timerMinutes).text = pomodoro.elapsedTime.minute
            .let { if (it.toString().length == 1) "0$it" else it.toString() }
        findViewById<TextView>(R.id.timerSeconds).text = pomodoro.elapsedTime.seconds
            .let { if (it.toString().length == 1) "0$it" else it.toString() }
        findViewById<Button>(R.id.btnPlayPause).text =
            if (pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) "Play" else "Pause"
    }
}
