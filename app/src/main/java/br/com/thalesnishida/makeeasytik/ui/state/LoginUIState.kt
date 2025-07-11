package br.com.thalesnishida.makeeasytik.ui.state

import br.com.thalesnishida.makeeasytik.utils.StringUtils

data class LoginUIState(
    val username: String = StringUtils.EMPTY,
    val password: String = StringUtils.EMPTY,
    val hasLogin: Boolean = false
)
