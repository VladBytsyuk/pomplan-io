package io.pomplan.android

import android.os.Bundle
import android.widget.ImageView
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
    }

    override fun onStart() {
        super.onStart()
        controller.subscribeOnState<State>("this") { render(it) }
        controller.attach()
    }

    override fun onStop() {
        controller.detach()
        controller.unsubscribeFromState("this")
        super.onStop()
    }

    private fun setOnClickListeners() {
        findViewById<ImageView>(R.id.btnPlayPause).setOnClickListener {
            val action = if (controller.currentState.pomodoro.mode in listOf(PRE_WORK, PRE_BREAK))
                Action.UserClick.Button.Play else Action.UserClick.Button.Pause
            controller.setAction(action)
        }
        findViewById<ImageView>(R.id.btnStop).setOnClickListener {
            controller.setAction(Action.UserClick.Button.Stop)
        }
        findViewById<ImageView>(R.id.btnSkip).setOnClickListener {
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
        findViewById<ImageView>(R.id.btnPlayPause).setImageResource(
            if (pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) R.drawable.ic_play else R.drawable.ic_pause
        )
    }
}
