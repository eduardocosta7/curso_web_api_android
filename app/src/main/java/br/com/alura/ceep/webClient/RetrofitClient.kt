package br.com.alura.ceep.webClient

import br.com.alura.ceep.webClient.services.NotasService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(HttpClient.getClient())
        .baseUrl("http://192.168.15.7:8080/")

    val notaService: NotasService by lazy {
        retrofit.build().create(NotasService::class.java)
    }

}