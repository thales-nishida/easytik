package br.com.thalesnishida.makeeasytik.services

import android.content.Context
import br.com.thalesnishida.makeeasytik.model.Keys
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class CacheServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) : CacheService {
    override fun saveAudioBytes(audio: ByteArray, theme: String) {
        val editTheme = theme.lowercase().trim()
        val fileName = "AUDIO_$editTheme.mp3"
        val audioFile = File(context.cacheDir, fileName)
        try {
            FileOutputStream(audioFile).use {
                it.write(audio)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getAudio(theme: String): File? {
        val audioFile = File(context.cacheDir, theme)

        return if (audioFile.exists()) {
            audioFile
        } else {
            null
        }
    }

    override fun getListAudio(): List<File> {
        val cacheDir = context.cacheDir
        val audiosFiles = cacheDir.listFiles { file ->
            file.isFile && file.name.startsWith("AUDIO_") && file.name.endsWith(".mp3")
        }

        return audiosFiles?.toList() ?: emptyList()
    }

    override fun deleteAudio(theme: String): Boolean {
        val audioFile = File(context.cacheDir, theme)
        return if (audioFile.exists()) {
            audioFile.delete()
        } else {
            false
        }
    }

    override fun setApiKeys(key: Keys) {
        val contentGson = gson.toJson(key)
        val sharedPref = context.getSharedPreferences(CACHE, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(KEYS, contentGson)
            apply()
        }
    }

    override fun getApiKeys(): Keys? {
        val sharedPref = context.getSharedPreferences(CACHE, Context.MODE_PRIVATE)
        val keysLabelJson = sharedPref.getString(KEYS, null)
        if (keysLabelJson != null) {
            return gson.fromJson(keysLabelJson, Keys::class.java)
        }
        return null
    }

    companion object {
        const val CACHE = "cache"
        const val KEYS = "keys"
    }
}