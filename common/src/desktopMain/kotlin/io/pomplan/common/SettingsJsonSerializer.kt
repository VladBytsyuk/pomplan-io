package io.pomplan.common

import com.beust.klaxon.Klaxon
import io.pomplan.common.domain.Settings
import io.pomplan.common.domain.Time


class SettingsJsonSerializer : Serializer<Settings, String> {
    override fun serialize(value: Settings): String = klaxon.toJsonString(value)
    override fun deserialize(serialized: String): Settings = klaxon.parse<Settings>(serialized)
        ?: Settings(
            workTime = Time(minute = 25),
            shortBreakTime = Time(minute = 5),
            longBreakTime = Time(minute = 15),
            groupSize = 4,
        )

    private val klaxon: Klaxon get() = Klaxon()
}
