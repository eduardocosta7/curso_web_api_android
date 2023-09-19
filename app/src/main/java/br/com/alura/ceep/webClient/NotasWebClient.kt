package br.com.alura.ceep.webClient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webClient.model.NotaRequisicao

object NotasWebClient {

    private val TAG = "WebClient"
    private val notasService = RetrofitClient().notaService

    suspend fun buscaTodas(): List<Nota>? {
        return try {
            val listaResposta = notasService.buscaTodas()
            listaResposta.map { notaResposta -> notaResposta.paraNota() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun salva(nota: Nota) : Boolean {
        try {
            val response = notasService.salva(
                nota.id,
                NotaRequisicao(nota.titulo, nota.descricao, nota.imagem)
            )

            return response.isSuccessful

        } catch (e: Exception) {
            Log.e(TAG, "Erro: ", e)
        }
        return false
    }

    suspend fun remove(id: String) : Boolean {
        try {
            notasService.remove(id)
            return true
        } catch (e: Exception) {
            Log.e(TAG, "remove: ", e)
        }
        return false
    }

}