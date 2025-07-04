package br.com.thalesnishida.makeeasytik.services

interface GeminiService {
    suspend fun sendTheme(theme: String, niche: String, tone: String): String
}