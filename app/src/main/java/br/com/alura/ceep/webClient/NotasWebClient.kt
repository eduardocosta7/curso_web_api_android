package br.com.alura.ceep.webClient

import br.com.alura.ceep.model.Nota

object NotasWebClient {

    suspend fun buscaTodas(): List<Nota>? {
        return try {
            val listaResposta = RetrofitClient().notaService.buscaTodas()
            listaResposta.map { notaResposta -> notaResposta.paraNota() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}