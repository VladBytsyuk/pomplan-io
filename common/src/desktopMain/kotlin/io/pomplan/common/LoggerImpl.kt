package io.pomplan.common


actual class LoggerImpl : Logger {
    override fun info(tag: String, message: String) = log("I/PomPlan-$tag: $message")
    override fun debug(tag: String, message: String) = log("D/PomPlan-$tag: $message")
    override fun warning(tag: String, message: String) = log("W/PomPlan-$tag: $message")
    override fun error(tag: String, message: String) = log("E/PomPlan-$tag: $message")
    override fun fatal(tag: String, message: String) = log("F/PomPlan-$tag: $message")

    private fun log(message: String) = println(message)
}
