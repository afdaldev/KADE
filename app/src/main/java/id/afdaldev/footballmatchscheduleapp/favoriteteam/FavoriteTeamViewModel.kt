package id.afdaldev.footballmatchscheduleapp.favoriteteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.local.FavoriteDatabase
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem

class FavoriteTeamViewModel(private val database: FavoriteDatabase) : ViewModel() {

    fun getAllFavoriteTeam(): LiveData<List<TeamItem>> =
        database.teamDao().getTeamFromLocal()

    fun insertTeamToFavorite(team: TeamItem) =
        database.teamDao().insertTeam(team)

    fun getTeamById(idTeam: String) =
        database.teamDao().getTeamById(idTeam)

    fun deleteTeamById(idTeam: String) =
        database.teamDao().deleteTeamById(idTeam)
}