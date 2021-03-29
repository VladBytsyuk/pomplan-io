package io.pomplan.common

import io.pomplan.common.domain.Settings


actual class SettingsRepositoryImpl : Repository<Settings> {
    override var value: Settings
        get() = serializer.deserialize(storageManager.readFile())
        set(value) = storageManager.rewriteFile(serializer.serialize(value))

    private val storageManager: StorageManager = FileManagerImpl(fileName = "settings.stg")
    private val serializer: Serializer<Settings, String> = SettingsJsonSerializer()
}
