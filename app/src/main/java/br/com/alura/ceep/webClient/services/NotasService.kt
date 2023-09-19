package br.com.alura.ceep.webClient.services

import br.com.alura.ceep.webClient.model.NotaRequisicao
import br.com.alura.ceep.webClient.model.NotaReposta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotasService {

    @GET("notas")
    suspend fun buscaTodas(): List<NotaReposta>

    @PUT("notas/{id}")
    suspend fun salva(@Path("id") id: String, @Body nota: NotaRequisicao): Response<NotaRequisicao>
    @DELETE("notas/{id}")
    suspend fun remove(@Path("id") id: String) : Response<Void>

}