package id.afdaldev.footballmatchscheduleapp.lookuptable

import id.afdaldev.footballmatchscheduleapp.data.network.APIService

class LookUpTableRepository {

    suspend fun getLookUpTable(idLeague: String) = APIService().getLookUpTable(idLeague)
}