package id.afdaldev.footballmatchscheduleapp.lookupallteams

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class LookUpAllTeamsViewModel(private val lookUpAllTeamsRepository: LookUpAllTeamsRepository) : ViewModel() {

    fun lookUpAllTeams(idLeague: String) = liveData(Dispatchers.IO) {
        val data = lookUpAllTeamsRepository.getLookUpAllTeams(idLeague)
        emit(data)
    }
}