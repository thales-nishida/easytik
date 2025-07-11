package br.com.thalesnishida.makeeasytik.services

import br.com.thalesnishida.makeeasytik.model.Keys
import java.io.File

interface CacheService {
    fun saveAudioBytes(audio: ByteArray, theme: String)
    fun getAudio(theme: String) : File?
    fun getListAudio() : List<File>
    fun deleteAudio(theme: String): Boolean
    fun setApiKeys(key: Keys)
    fun getApiKeys() : Keys?
}