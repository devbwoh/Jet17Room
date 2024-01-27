package kr.ac.kumoh.ce.prof01.jet17room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kr.ac.kumoh.ce.prof01.jet17room.ui.theme.Jet17RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jet17RoomTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            floatingActionButton = {
                SongAddButton()
            }
        ) {
            SongList(it)
        }
    }
}

@Composable
fun SongList(paddingValues: PaddingValues) {
    val viewModel: SongViewModel = viewModel()
    val songs by viewModel.songs.collectAsState()

    LazyVerticalStaggeredGrid(
        StaggeredGridCells.Fixed(2),
        contentPadding = paddingValues,
    ) {
        items(
            items = songs,
            key = {
                it.id
            }
        ) {
            SongItem(it)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Column(
            Modifier.padding(8.dp)
        ) {
            SongTitle(song.title)
            SingerName(song.singer)
        }
    }
}

@Composable
fun SongTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 20.sp,
        lineHeight = 25.sp,
    )
}

@Composable
fun SingerName(name: String) {
    Text(name)
}

@Composable
fun SongAddButton() {
    val viewModel: SongViewModel = viewModel()
    val songsToAdd = listOf(
        listOf("사랑에 연습이 있었다면", "임재현"),
        listOf("소주 한 잔", "임창정"),
        listOf("비의 랩소디", "임재현"),
        listOf("ETA", "NewJeans"),
        listOf("어떻게 이별까지 사랑하겠어, 널 사랑하는 거지", "AKMU(악뮤)"),
        listOf("헤어지자 말해요", "박재정"),
        listOf("Perfect Night", "LE SSERAFIM (르세라핌)"),
    )

    FloatingActionButton(
        onClick = {
            val song = songsToAdd.random()
            viewModel.add(song[0], song[1])
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(
            Icons.Filled.Add,
            "추가 버튼"
        )
    }
}