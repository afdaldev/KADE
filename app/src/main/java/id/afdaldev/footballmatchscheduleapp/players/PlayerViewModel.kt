package id.afdaldev.footballmatchscheduleapp.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    fun getSearchPLayers(search: String) = liveData(Dispatchers.IO) {
        val data = playerRepository.getSearchPlayers(search)
        emit(data)
    }
}