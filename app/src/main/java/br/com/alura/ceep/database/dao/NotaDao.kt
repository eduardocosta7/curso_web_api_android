package br.com.alura.ceep.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alura.ceep.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    @Insert(onConflict = REPLACE)
    suspend fun salva(nota: Nota)

    @Insert(onConflict = REPLACE)
    suspend fun salva(notas: List<Nota>)

    @Query("SELECT * FROM Nota WHERE flDesativada = 0")
    fun buscaTodas() : Flow<List<Nota>>

    @Query("SELECT * FROM Nota WHERE id = :id AND flDesativada = 0")
    fun buscaPorId(id: String): Flow<Nota>

    @Query("DELETE FROM Nota WHERE id = :id")
    suspend fun remove(id: String)

    @Query("SELECT * FROM Nota WHERE flSincronizado = 0 AND flDesativada = 0")
    fun buscaNaoSincronadas() : Flow<List<Nota>>

    @Query("UPDATE Nota SET flDesativada = 1 WHERE id = :id")
    suspend fun desativa(id: String)

    @Query("SELECT * FROM Nota WHERE flDesativada = 1")
    fun buscaDesativadas() : Flow<List<Nota>>

}