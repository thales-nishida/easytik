package br.com.thalesnishida.makeeasytik.services

import br.com.thalesnishida.makeeasytik.BuildConfig
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ElevenLabsServiceImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
    private val cacheService: CacheService
) : ElevenLabsService {
    data class TextToSpeechRequest(
        val text: String,
        val modelId: String = "eleven_multilingual_v2" // You can set a default value
    )

    override suspend fun sendTextToSpeech(text: String, theme: String) =
        withContext(Dispatchers.IO) {
            val key = BuildConfig.XI_API_KEY

            val specialCharsRegex = Regex("[*#:@%&^]") // Add any characters you want to remove here
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