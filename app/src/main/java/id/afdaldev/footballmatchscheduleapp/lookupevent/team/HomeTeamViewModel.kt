package id.afdaldev.footballmatchscheduleapp.lookupevent.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Team

class HomeTeamViewModel(idHomeTeam: String) : ViewModel() {

    private val teamRepository = TeamRepository()
    private var homeTeamList = MutableLiveData<Team>()

    init {
        homeTeamList = teamRepository.getHomeTeam(idHomeTeam)
    }

    fun getHomeTeam() : LiveData<Team> = homeTeamList

}