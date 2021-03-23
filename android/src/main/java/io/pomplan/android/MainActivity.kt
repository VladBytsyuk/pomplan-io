package io.pomplan.android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.pomplan.android.ui_kit.PomodoroPanelView
import io.pomplan.android.ui_kit.TimerProgressView
import io.pomplan.android.ui_kit.TimerView
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

        findViewById<TimerView>(R.id.timer).data = TimerView.Data(
            goal = pomodoro.goalTime,
            elapsed = pomodoro.elapsedTime,
            mode = pomodoro.mode,
        )
        findViewById<ImageView>(R.id.btnPlayPause).setImageResource(
            if (pomodoro.mode in listOf(PRE_WORK, PRE_BREAK)) R.drawable.ic_play else R.drawable.ic_pause
        )
        findViewById<PomodoroPanelView>(R.id.pomodoroPanel).count = PomodoroPanelView.Data(
            doneCount = pomodoro.number,
            currentInProgress = pomodoro.mode in listOf(WORK, PRE_WORK)
        )
    }
}
