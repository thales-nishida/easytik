package br.com.thalesnishida.makeeasytik.services

import br.com.thalesnishida.makeeasytik.BuildConfig
import br.com.thalesnishida.makeeasytik.model.GeminiResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import javax.inject.Inject

class GeminiServiceImpl @Inject constructor(
    private val gson: Gson,
    private val okHttpClient: OkHttpClient
) : GeminiService {
    override suspend fun sendTheme(
        theme: String,
        niche: String,
        tone: String
    ): String = withContext(Dispatchers.IO) {
        val masterPrompt = """
                Atue como: Um roteirista especialista em conteúdo viral para o TikTok, com foco em criar narrativas de falas que maximizem a retenção e o engajamento da audiência.
                Seu objetivo: Criar um roteiro de falas para um vídeo de 70 segundos sobre o tema "$theme".
                Estrutura e Tom:
                    1.  Gancho Irresistível (Primeiros 3 segundos): Comece com uma pergunta provocadora, uma estatística chocante ou uma declaração contraintuitiva diretamente relacionada ao tema. O objetivo é fazer o espectador parar de rolar a tela imediatamente.
                    2.  Identificação e Promessa (Até 15 segundos): Crie uma conexão com o espectador. Use frases como "Você já se sentiu..." ou "E se eu te dissesse que...". Apresente o problema ou a curiosidade que será abordada e prometa uma solução ou uma revelação até o final do vídeo. O tom deve ser $tone.
                    3.  Desenvolvimento Dinâmico (Até 50 segundos): Apresente o conteúdo principal em 3 a 5 pontos curtos e de fácil digestão. Utilize uma linguagem simples e direta. Se o nicho for "$niche", os pontos devem ser práticos e acionáveis. A narrativa deve seguir um ritmo rápido e crescente.
                    4.  A Grande Revelação (Até 60 segundos): Entregue a informação mais valiosa ou surpreendente do roteiro. Este é o clímax. Deve gerar um sentimento de "aha!" no espectador.
                    5.  Chamada para Ação (CTA) Engajadora e Encerramento (Até 70 segundos): Finalize com uma CTA clara e que incentive a interação. Peça para os espectadores comentarem suas opiniões ou compartilharem com alguém que precisa saber disso. Encerre com uma frase de efeito que reforce a mensagem principal.
                Regras Adicionais:
                    - Linguagem: Utilize uma linguagem coloquial e próxima do público brasileiro.
                    - Tempo: O roteiro deve ser lido em um ritmo natural em aproximadamente 70 segundos.
                    - Formato: Apresente o roteiro com indicações de tempo (ex: 0-3s, 4-15s, etc.) e sugestões de entonação (ex: *em tom de segredo*, *com entusiasmo*).
                ---
                    **TAREFA FINAL IMPORTANTE:** Após gerar o roteiro completo com todas as formatações acima, reescreva ABAIXO dele uma versão final contendo APENAS as falas a serem narradas. Remova todas as indicações de tempo, sugestões de entonação e títulos de seção. Este texto final deve estar perfeitamente limpo e pronto para ser enviado a uma API de Text-to-Speech (TTS).
        """.trimIndent()
        val key = BuildConfig.API_KEY

        val url =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=$key"

        val payload = mapOf(
            "contents" to listOf(
                mapOf(
                    "parts" to listOf(
                        mapOf("text" to masterPrompt)
                    )
                )
            )
        )
        val validJsonBody = gson.toJson(payload)
        val requestBody = validJsonBody.toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        val response = okHttpClient.newCall(request).execute()

        response.use {
            if (it.isSuccessful) {
                val geminiResponse = gson.fromJson(it.body.string(), GeminiResponse::class.java)
                val completeString = geminiResponse.candidates[0].content.parts[0].text
                val justTalkString = completeString.substringAfterLast("**").trim()
                return@withContext justTalkString
            } else {
                throw IOException("Erro: ${response.code} - ${response.body.string()}")
            }
        }
    }
}