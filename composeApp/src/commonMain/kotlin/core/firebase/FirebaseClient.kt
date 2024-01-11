package core.firebase

import dev.gitlive.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.KSerializer

interface FirebaseClient {
    suspend fun getDocumentValue(collectionPath: String): Result<List<DocumentSnapshot>>

    suspend fun getSpecificDocument(
        collectionPath: String,
        documentPath: String
    ): Result<DocumentSnapshot>

    suspend fun <T> setSpecificDocument(
        collectionPath: String,
        documentPath: String,
        data: T,
    ): Result<Boolean>

    suspend fun deleteDocument(collectionPath: String, documentPath: String): Result<Boolean>
}