package br.com.thalesnishida.makeeasytik.services

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ElevenLabsServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    private val cacheService: CacheService
) : ElevenLabsService {

    data class TextToSpeechRequest(
        val text: String,
        val modelId: String = "eleven_multilingual_v2"
    )

    override suspend fun sendTextToSpeech(text: String, theme: String) =
        withContext(Dispatchers.IO) {
            val key = cacheService.getApiKeys()?.keyGemini

            if (key.isNullOrEmpty()) {
                Toast.makeText(context, "Key do elevenLabs vazia", Toast.LENGTH_LONG).show()
                return@withContext
            }

            val specialCharsRegex = Regex("[*#:@%&^]")
            val cleanedText1 = text.replace(specialCharsRegex, "")
            val cleanedText = cleanedText1.replace("\"", "")
            val requestData = TextToSpeechRequest(text = cleanedText.trim())
            val json = Gson().toJson(requestData)

            val request = Request.Builder()
                .url("https://api.elevenlabs.io/v1/text-to-speech/JBFqnCBsd6RMkjVDRZzb?output_format=mp3_44100_128")
                .post(json.toRequestBody("application/json".toMediaType()))
                .addHeader("xi-api-key", key)
                .addHeader("Content-Type", "application/json")
                .build()

            val response = okHttpClient.newCall(request).execute()

            response.use {
                if (it.isSuccessful) {
                    val audioByteArray = it.body.bytes()
                    cacheService.saveAudioBytes(
                        audio = audioByteArray,
                        theme = theme
                    )
                }
            }
        }
}