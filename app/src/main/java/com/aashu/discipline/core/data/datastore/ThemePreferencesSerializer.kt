package com.aashu.discipline.core.data.datastore

import androidx.datastore.core.Serializer
import com.aashu.discipline.core.domain.model.ThemeMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class ThemePreferences(
    val mode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true
)

object ThemePreferencesSerializer : Serializer<ThemePreferences> {
    override val defaultValue: ThemePreferences = ThemePreferences()

    override suspend fun readFrom(input: InputStream): ThemePreferences =
        withContext(Dispatchers.IO) {
            val bytes = input.readBytes()
            if (bytes.isEmpty()) return@withContext defaultValue
            Json.decodeFromString(
                ThemePreferences.serializer(),
                bytes.decodeToString()
            )
        }

    override suspend fun writeTo(t: ThemePreferences, output: OutputStream) =
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(ThemePreferences.serializer(), t).encodeToByteArray()
            )
        }
}

class ThemePreferencesRepository {
    // In a real app this would be injected with Android Context; for now, provide
    // a no-op in-memory flow so the project compiles. Wire DataStore later.
    private val state = kotlinx.coroutines.flow.MutableStateFlow(ThemePreferences())

    val themeSettings: kotlinx.coroutines.flow.StateFlow<ThemePreferences> = state

    suspend fun updateTheme(mode: com.aashu.discipline.core.domain.model.ThemeMode, dynamic: Boolean) {
        state.value = ThemePreferences(mode, dynamic)
    }
}
