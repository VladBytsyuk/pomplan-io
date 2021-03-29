package io.pomplan.common

import java.io.File


class FileManagerImpl(private val fileName: String) : StorageManager {
    override fun readFile(): String {
        val file = File(fileName)
        return file.readText()
    }

    override fun rewriteFile(text: String) {
        val file = File(fileName)
        if (!file.exists()) file.createNewFile()
        file.writeText(text)
    }
}
