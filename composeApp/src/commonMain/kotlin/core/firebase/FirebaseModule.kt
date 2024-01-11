package core.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

object FirebaseModule {
    val modules = module {
        single { provideFirebase() }
        single<FirebaseClient> { FirebaseClientImpl(get()) }
    }

    private fun provideFirebase(): FirebaseFirestore {
        return Firebase.firestore
    }
}