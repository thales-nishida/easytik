package br.com.thalesnishida.makeeasytik.ui.state

import br.com.thalesnishida.makeeasytik.utils.StringUtils

data class SettingsUIState(
    val apiKeyGemini: String = StringUtils.EMPTY,
    val apiKeyElevenLabs: String = StringUtils.EMPTY
)
