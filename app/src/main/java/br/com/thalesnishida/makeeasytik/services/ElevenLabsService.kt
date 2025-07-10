package br.com.thalesnishida.makeeasytik.services

interface ElevenLabsService {
    suspend fun sendTextToSpeech(text: String, theme: String)
}