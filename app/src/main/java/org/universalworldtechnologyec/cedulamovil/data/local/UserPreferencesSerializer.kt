package org.universalworldtechnologyec.cedulamovil.data.local

import androidx.datastore.core.Serializer
import org.universalworldtechnologyec.cedulamovil.data.security.CryptoManager
import org.universalworldtechnologyec.cedulamovil.datastore.UserPreferences
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserPreferencesSerializer @Inject constructor(
    private val cryptoManager: CryptoManager
): Serializer<UserPreferences> {

    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences {
        return try {
            val decryptedBytes = cryptoManager.decrypt(input)
            UserPreferences.parseFrom(decryptedBytes)
        }catch (e: Exception){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        var bytes = t.toByteArray()
        cryptoManager.encrypt(bytes = bytes, outputStream = output)
    }
}