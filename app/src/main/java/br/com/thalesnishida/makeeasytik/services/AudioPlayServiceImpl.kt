package br.com.thalesnishida.makeeasytik.services

import android.content.ContentValues
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.provider.MediaStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class AudioPlayServiceImpl(
    private val cacheService: CacheService
) : AudioPlayService {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val audioFile = cacheService.getAudio(theme = fileName)

                if (audioFile != null) {
                    releasePlayer()

                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(audioFile.absolutePath)
                        setOnPreparedListener { it.start() }
                        setOnCompletionListener { releasePlayer() }
                        prepareAsync()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun pause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    override fun stop() {
        releasePlayer()
    }

    override fun resume() {
        if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            mediaPlayer?.start()
        }
    }

    private fun releasePlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun downloadAudioToPublicFolder(context: Context, audioFile: File) : Boolean{
        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, audioFile.name)
            put(MediaStore.MediaColumns.MIME_TYPE, "audio/mpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/")
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uri =
                contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            uri?.let {
                try {
                    contentResolver.openOutputStream(it).use { outputStream ->
                        audioFile.inputStream().use { inputStream ->
                            inputStream.copyTo(outputStream!!)
                            return true
                        }
                    }
                } catch (e: Exception) {
                    return false
                    e.printStackTrace()
                }
            }
        }
        return false
    }
}