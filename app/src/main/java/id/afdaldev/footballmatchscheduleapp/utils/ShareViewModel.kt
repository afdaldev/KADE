package id.afdaldev.footballmatchscheduleapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    var idLeague = MutableLiveData<String>()

    var idHomeTeam = MutableLiveData<String>()
    var idAwayTeam = MutableLiveData<String>()

    fun setIdLeague(id: String) {
        idLeague.value = id
    }

    fun setIdHomeTeam(id: String) {
        idHomeTeam.value = id
    }

    fun setIdAwayTeam(id: String) {
        idAwayTeam.value = id
    }
}