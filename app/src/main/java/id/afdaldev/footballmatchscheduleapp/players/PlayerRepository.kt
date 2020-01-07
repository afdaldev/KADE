package id.afdaldev.footballmatchscheduleapp.players

import id.afdaldev.footballmatchscheduleapp.data.network.APIService

class PlayerRepository {

    suspend fun getSearchPlayers(search: String) = APIService().getSearchPlayers(search)
}