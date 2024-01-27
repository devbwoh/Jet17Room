package kr.ac.kumoh.ce.prof01.jet17room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: SongDao =
        Room.databaseBuilder(
            application,
            SongDatabase::class.java,
            "song.db"
        ).build().songDao()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>>
        get() = _songs

    init {
        add("노래 테스트 1", "가수 1")
        add("노래 테스트 2", "가수 2")

        select()
    }

    fun select() {
        viewModelScope.launch {
            dao.select().collect {
                _songs.value = it
            }
        }
    }

    fun add(title: String, singer: String) {
        viewModelScope.launch {
            dao.insert(Song(0, title, singer))
        }
    }
}