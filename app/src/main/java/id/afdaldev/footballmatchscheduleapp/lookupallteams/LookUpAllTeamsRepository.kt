package id.afdaldev.footballmatchscheduleapp.lookupallteams

import id.afdaldev.footballmatchscheduleapp.data.network.APIService

class LookUpAllTeamsRepository {

    suspend fun getLookUpAllTeams(idLeague: String) = APIService().getLookUpAllTeams(idLeague)
}