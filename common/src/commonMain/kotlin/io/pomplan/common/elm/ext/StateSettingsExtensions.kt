package io.pomplan.common.elm.ext

import io.pomplan.common.domain.Settings
import io.pomplan.common.domain.Time
import io.pomplan.common.elm.State


fun State.updateSettings(
    workTime: Time = settings.workTime,
    shortBreakTime: Time = settings.shortBreakTime,
    longBreakTime: Time = settings.longBreakTime,
    groupSize: Int = settings.groupSize
): State = copy(
    settings = Settings(workTime, shortBreakTime, longBreakTime, groupSize)
)
