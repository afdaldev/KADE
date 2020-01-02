package id.afdaldev.footballmatchscheduleapp.lookupleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpLeague

class LookUpLeagueViewModel(private val lookUpLeagueRepository: LookUpLeagueRepository) :
    ViewModel() {

    fun getLookUpLeagueList(idLeague: String): LiveData<LookUpLeague> = lookUpLeagueRepository.getLookUpLeague(idLeague)
}