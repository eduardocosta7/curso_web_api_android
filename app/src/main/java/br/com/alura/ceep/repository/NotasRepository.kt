package br.com.alura.ceep.repository

import android.util.Log
import br.com.alura.ceep.database.AppDatabase
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webClient.NotasWebClient
import kotlinx.coroutines.flow.Flow

class NotasRepository(private val db: AppDatabase, private val webClient: NotasWebClient) {

    private val notasDao = db.notaDao()

    fun buscaTodas(): Flow<List<Nota>> = notasDao.buscaTodas()

    suspend fun atualizaTodas() {
        val notasRepostas = webClient.buscaTodas()
        notasRepostas?.let { notasDao.salva(notasRepostas) }
    }

}