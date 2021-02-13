package io.pomplan.common

interface Logger {
    fun info(tag: String, message: String)
    fun debug(tag: String, message: String)
    fun warning(tag: String, message: String)
    fun error(tag: String, message: String)
    fun fatal(tag: String, message: String)
}