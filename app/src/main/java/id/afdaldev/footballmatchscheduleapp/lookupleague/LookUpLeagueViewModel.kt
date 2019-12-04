package id.afdaldev.footballmatchscheduleapp.lookupleague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpLeague

class LookUpLeagueViewModel(idLeague: String) : ViewModel() {

    private val lookUpLeagueRepository = LookUpLeagueRepository()
    private var lookUpLeague = MutableLiveData<LookUpLeague>()

    init {
        lookUpLeague = lookUpLeagueRepository.getLookUpLeague(idLeague)
    }

    fun getLookUpLeague(): LiveData<LookUpLeague> = lookUpLeague
}