package id.afdaldev.footballmatchscheduleapp.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    fun getTeam(idTeam: String) = liveData(Dispatchers.IO) {
        val data = teamRepository.getTeam(idTeam)
        emit(data)
    }

    fun getHomeTeam(idHomeTeam: String) = liveData(Dispatchers.IO) {
        val data = teamRepository.getHomeTeam(idHomeTeam)
        emit(data)
    }

    fun getAwayTeam(idAwayTeam: String) = liveData(Dispatchers.IO) {
        val data = teamRepository.getAwayTeam(idAwayTeam)
        emit(data)
    }
}