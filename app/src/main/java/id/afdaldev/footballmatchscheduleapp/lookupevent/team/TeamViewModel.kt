package id.afdaldev.footballmatchscheduleapp.lookupevent.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Team

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    fun getHomeTeam(idHomeTeam: String): LiveData<Team> = teamRepository.getHomeTeam(idHomeTeam)

    fun getAwayTeam(idAwayTeam: String): LiveData<Team> = teamRepository.getAwayTeam(idAwayTeam)
}