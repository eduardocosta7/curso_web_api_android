package br.com.alura.ceep.webClient.model

import br.com.alura.ceep.model.Nota
import java.util.UUID

class NotaReposta(
    val id: String?,
    val titulo: String?,
    val descricao: String?,
    val imagem: String?
) {
    fun paraNota() = Nota(
        id = id ?: UUID.randomUUID().toString(),
        titulo = titulo ?: "",
        descricao = descricao ?: "",
        imagem = imagem ?: ""
    )
}