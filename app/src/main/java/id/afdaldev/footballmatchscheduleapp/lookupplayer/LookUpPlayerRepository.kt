package id.afdaldev.footballmatchscheduleapp.lookupplayer

import id.afdaldev.footballmatchscheduleapp.data.network.APIService

class LookUpPlayerRepository {

    suspend fun getLookUpPlayer(idPlayer: String) = APIService().getLookUpPlayer(idPlayer)
}