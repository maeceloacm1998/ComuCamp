package register.di

import org.koin.dsl.module
import register.feature.registerphoto.ui.RegisterPhotoScreenModel
import register.feature.registerusername.RegisterUserNameScreenModel

object RegisterViewModelModule {
    val modules = module {
        factory { RegisterUserNameScreenModel() }
        factory { RegisterPhotoScreenModel() }
    }
}