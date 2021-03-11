package io.pomplan.desktop

import io.kotest.core.spec.style.scopes.ShouldSpecContextScope
import io.kotest.matchers.shouldBe


inline fun <T> T.multiOperation(times: Int, block: (T) -> T): T {
    require(times >= 0)
    var value = this
    repeat(times) { value = block(value) }
    return value
}

suspend inline fun <T> ShouldSpecContextScope.should(name: String, actual: T, expected: T) {
    should(name) { actual shouldBe expected }
}
