package br.com.thalesnishida.makeeasytik.services

import android.content.Context
import java.io.File

interface AudioPlayService {
    fun play(fileName: String)
    fun pause()
    fun stop()
    fun resume()
    fun downloadAudioToPublicFolder(context: Context, audioFile: File) : Boolean
}