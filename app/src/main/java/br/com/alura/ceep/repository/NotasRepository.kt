package br.com.alura.ceep.repository

import br.com.alura.ceep.database.AppDatabase
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webClient.NotasWebClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class NotasRepository(db: AppDatabase, private val webClient: NotasWebClient) {

    private val dao = db.notaDao()

    fun buscaTodas(): Flow<List<Nota>> = dao.buscaTodas()

    private suspend fun atualizaTodas() {
        val notasRepostas = webClient.buscaTodas()
        notasRepostas?.let { notas ->
            val notasSincronizadas = notas.map { it.copy(flSincronizado = true) }
            dao.salva(notasSincronizadas)
        }
    }

    fun buscaPorId(id: String): Flow<Nota> {
        return dao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        dao.desativa(id)
        if (webClient.remove(id)) {
            dao.remove(id)
        }
    }

    suspend fun salva(nota: Nota) {
        dao.salva(nota)
        if (webClient.salva(nota)) {
            val notaSincronizada = nota.copy(flSincronizado = true)
            dao.salva(notaSincronizada)
        }
    }

    suspend fun sincroniza() {
        val notasDesativadas = dao.buscaDesativadas().first()
        notasDesativadas.forEach {
            remove(it.id)
        }
        val notasNaoSincronizadas = dao.buscaNaoSincronadas().first()
        notasNaoSincronizadas.forEach {
            salva(it)
        }
        atualizaTodas()
    }
}