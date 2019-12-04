package id.afdaldev.footballmatchscheduleapp.lookupevent.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Team

class AwayTeamViewModel(idAwayTeam: String) : ViewModel() {

    private val teamRepository = TeamRepository()
    private var awayTeamList = MutableLiveData<Team>()

    init {
        awayTeamList = teamRepository.getAwayTeam(idAwayTeam)
    }

    fun getAwayTeam() : LiveData<Team> = awayTeamList
}