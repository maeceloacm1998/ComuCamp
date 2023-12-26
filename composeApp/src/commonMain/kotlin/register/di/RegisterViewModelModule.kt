package register.di

import org.koin.dsl.module
import register.feature.registerusername.RegisterUserNameScreenModel

object RegisterViewModelModule {
    val modules = module {
        factory { RegisterUserNameScreenModel() }
    }
}