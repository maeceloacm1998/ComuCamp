package core.firebase

import comunexo.feature.game.model.Game
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.Exception

class FirebaseClientImpl(private val da: FirebaseFirestore) : FirebaseClient {
    val database = Firebase.firestore
    override suspend fun getDocumentValue(collectionPath: String): Result<List<DocumentSnapshot>> {
        return try {
            val res = withContext(Dispatchers.IO) {
                database.clearPersistence()
                database.collection(collectionPath).get()
            }
            val documents = res.documents

            if (documents.isEmpty()) {
                throw Exception()
            }

            Result.success(documents)
        } catch (e: Exception) {
            Result.failure(Throwable(e.message, e.cause))
        }
    }

    override suspend fun getSpecificDocument(
        collectionPath: String,
        documentPath: String
    ): Result<DocumentSnapshot> {
        return try {
            val res = withContext(Dispatchers.IO) {
                database.clearPersistence()
                database.collection(collectionPath).document(documentPath).get()
            }

            Result.success(res)
        } catch (e: Exception) {
            Result.failure(Throwable(e.message, e.cause))
        }
    }

    override suspend fun <T> setSpecificDocument(
        collectionPath: String,
        documentPath: String,
        data: T,
    ): Result<Boolean> {
        return try {
            database.collection(collectionPath).document(documentPath)
                    .set(Game.serializer(),data as Game, encodeDefaults = true)

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Throwable(e.message, e.cause))
        }
    }

    override suspend fun deleteDocument(
        collectionPath: String,
        documentPath: String
    ): Result<Boolean> {
        return try {
            withContext(Dispatchers.IO) {
                database.clearPersistence()
                database.collection(collectionPath).document(documentPath).delete()
            }
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(Throwable(e.message, e.cause))
        }
    }
}