package id.afdaldev.footballmatchscheduleapp.team

import id.afdaldev.footballmatchscheduleapp.data.network.APIService

class TeamRepository {

    suspend fun getTeam(idTeam: String) = APIService().getLookUpTeam(idTeam)

    suspend fun getHomeTeam(idHomeTeam: String) = APIService().getLookUpTeam(idHomeTeam)

    suspend fun getAwayTeam(idAwayTeam: String) = APIService().getLookUpTeam(idAwayTeam)
}