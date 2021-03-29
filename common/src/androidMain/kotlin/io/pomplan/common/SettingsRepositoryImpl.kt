package io.pomplan.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import io.pomplan.common.domain.Settings
import io.pomplan.common.domain.Time


actual class SettingsRepositoryImpl(private val context: Context) : Repository<Settings> {
    override var value: Settings
        get() {
            val workTime = Time(
                minute = storage.getInt(WORK_TIME_MINUTES_KEY, 0),
                second = storage.getInt(WORK_TIME_SECONDS_KEY, 0)
            )
            val shortBreakTime = Time(
                minute = storage.getInt(SHORT_BREAK_TIME_MINUTES_KEY, 0),
                second = storage.getInt(SHORT_BREAK_TIME_SECONDS_KEY, 0)
            )
            val longBreakTime = Time(
                minute = storage.getInt(LONG_BREAK_TIME_MINUTES_KEY, 0),
                second = storage.getInt(LONG_BREAK_TIME_SECONDS_KEY, 0)
            )
            val groupSize = storage.getInt(GROUP_SIZE_KEY, 0)
            return Settings(workTime, shortBreakTime, longBreakTime, groupSize)
        }
        set(value) {
            storage.edit {
                putInt(WORK_TIME_MINUTES_KEY, value.workTime.minute)
                putInt(WORK_TIME_SECONDS_KEY, value.workTime.seconds)
                putInt(SHORT_BREAK_TIME_MINUTES_KEY, value.shortBreakTime.minute)
                putInt(SHORT_BREAK_TIME_SECONDS_KEY, value.shortBreakTime.seconds)
                putInt(LONG_BREAK_TIME_MINUTES_KEY, value.longBreakTime.minute)
                putInt(LONG_BREAK_TIME_SECONDS_KEY, value.longBreakTime.seconds)
                putInt(GROUP_SIZE_KEY, value.groupSize)
            }
        }

    private val storage: SharedPreferences
        get() = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PACKAGE = "io.pomplan.common.repository"
        private const val SHARED_PREFERENCES_NAME = "$PACKAGE.settings"

        private const val WORK_TIME_MINUTES_KEY = "$PACKAGE.work.minutes"
        private const val WORK_TIME_SECONDS_KEY = "$PACKAGE.work.seconds"
        private const val SHORT_BREAK_TIME_MINUTES_KEY = "$PACKAGE.break.short.minutes"
        private const val SHORT_BREAK_TIME_SECONDS_KEY = "$PACKAGE.break.short.seconds"
        private const val LONG_BREAK_TIME_MINUTES_KEY = "$PACKAGE.break.long.minutes"
        private const val LONG_BREAK_TIME_SECONDS_KEY = "$PACKAGE.break.long.seconds"
        private const val GROUP_SIZE_KEY = "$PACKAGE.group.size"
    }
}
