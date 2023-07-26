package br.com.alura.ceep.repository

import br.com.alura.ceep.database.AppDatabase
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webClient.NotasWebClient
import kotlinx.coroutines.flow.Flow

class NotasRepository(db: AppDatabase, private val webClient: NotasWebClient) {

    private val notasDao = db.notaDao()

    fun buscaTodas(): Flow<List<Nota>> = notasDao.buscaTodas()

    suspend fun atualizaTodas() {
        val notasRepostas = webClient.buscaTodas()
        notasRepostas?.let { notasDao.salva(notasRepostas) }
    }

    fun buscaPorId(id: String): Flow<Nota> {
        return notasDao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        return notasDao.remove(id)
    }

    suspend fun salva(nota: Nota) {
        notasDao.salva(nota)
        webClient.salva(nota)
    }

}