package id.afdaldev.footballmatchscheduleapp.lookupplayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class LookUpPlayerViewModel(private val lookUpPlayerRepository: LookUpPlayerRepository) :
    ViewModel() {

    fun getLookUpPlayer(idPlayer: String) = liveData(Dispatchers.IO) {
        val data = lookUpPlayerRepository.getLookUpPlayer(idPlayer)
        emit(data)
    }
}