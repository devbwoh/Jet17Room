package kr.ac.kumoh.ce.prof01.jet17room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "song")
data class Song(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val singer: String,
)

@Dao
interface SongDao {
    @Query("SELECT * FROM song")
    fun select(): Flow<List<Song>>

    @Insert
    suspend fun insert(song: Song)
}

@Database(
    entities = [Song::class],
    version = 1,
)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
}