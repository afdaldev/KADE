package id.afdaldev.footballmatchscheduleapp.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.League

class LeagueViewModel(private val leagueRepository: LeagueRepository) : ViewModel() {

    fun getLeague(): LiveData<MutableList<League>> = leagueRepository.getArrayList()
}