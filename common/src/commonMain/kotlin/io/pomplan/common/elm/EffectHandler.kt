package io.pomplan.common.elm

import io.pomplan.common.Logger
import io.pomplan.common.Repository
import io.pomplan.common.Timer
import io.pomplan.common.domain.Settings


class EffectHandler (
    private val logger: Logger,
    private val timer: Timer,
    private val settingsRepository: Repository<Settings>
): Elm.EffectHandler<Effect, Action> {
    override suspend fun handle(effect: Effect): Action? = when (effect) {
        is Effect.IllegalState -> {
            logger.error(
                tag = "StateMachine",
                message = "Applying ${effect.action} to ${effect.state} is forbidden by reducer."
            )
            null
        }

        is Effect.Timer -> handleTimer(effect)

        is Effect.SaveSettings -> {
            settingsRepository.value = effect.settings
            null
        }
    }

    private fun handleTimer(effect: Effect.Timer): Action? = when (effect) {
        Effect.Timer.Run -> {
            timer.run()
            null
        }
        Effect.Timer.Stop -> {
            timer.stop()
            null
        }
    }
}
