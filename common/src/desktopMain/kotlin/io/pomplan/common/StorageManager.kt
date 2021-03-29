package io.pomplan.common


interface StorageManager {
    fun readFile(): String
    fun rewriteFile(text: String)
}
