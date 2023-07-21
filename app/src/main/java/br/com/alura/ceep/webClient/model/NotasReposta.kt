package br.com.alura.ceep.webClient.model

import br.com.alura.ceep.model.Nota

class NotasReposta(
    val id: String?,
    val titulo: String?,
    val descricao: String?,
    val imagem: String?
) {
    fun paraNota() = Nota(
        id = 0,
        titulo = titulo ?: "",
        descricao = descricao ?: "",
        imagem = imagem ?: ""
    )
}