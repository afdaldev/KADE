package id.afdaldev.footballmatchscheduleapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {

    var idLeague = MutableLiveData<String>()
    fun setIdLeague(id: String) {
        idLeague.value = id
    }

    var idTeam = MutableLiveData<String>()
    fun setIdTeam(id: String) {
        idTeam.value = id
    }

    var idHomeTeam = MutableLiveData<String>()
    fun setIdHomeTeam(id: String) {
        idHomeTeam.value = id
    }

    var idAwayTeam = MutableLiveData<String>()
    fun setIdAwayTeam(id: String) {
        idAwayTeam.value = id
    }

    var teamName = MutableLiveData<String>()
    fun setTeamName(name: String) {
        teamName.value = name
    }

    var idPlayer = MutableLiveData<String>()
    fun setIdPlayer(id: String) {
        idPlayer.value = id
    }

    var idEvent = MutableLiveData<String>()
    fun setIdEvent(eventId: String) {
        idEvent.value = eventId
    }
}