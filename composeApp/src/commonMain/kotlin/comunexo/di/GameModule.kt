package comunexo.di

import comunexo.feature.game.GameScreenModel
import org.koin.dsl.module

object GameModule {
    val modules = module {
        factory { GameScreenModel(client = get()) }
    }
}