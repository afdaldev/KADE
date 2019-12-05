package id.afdaldev.footballmatchscheduleapp.league

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.League

class LeagueViewModel : ViewModel() {

    private var leagueList = MutableLiveData<MutableList<League>>()

    init {
        leagueList = getArrayList()
    }

    fun getLeague(): LiveData<MutableList<League>> = leagueList

    private fun getArrayList(): MutableLiveData<MutableList<League>>{
        val leagueListMutableLiveData = MutableLiveData<MutableList<League>>()
        val leagueList: MutableList<League> = mutableListOf()

        val idLeague = ArrayList<String>()
        idLeague.add("4328")
        idLeague.add("4329")
        idLeague.add("4330")
        idLeague.add("4331")
        idLeague.add("4332")
        idLeague.add("4334")
        idLeague.add("4335")
        idLeague.add("4336")
        idLeague.add("4337")
        idLeague.add("4338")

        val strLeague = ArrayList<String>()
        strLeague.add("English Premier League")
        strLeague.add("English League Championship")
        strLeague.add("Scottish Premier League")
        strLeague.add("German Bundesliga")
        strLeague.add("Italian Serie A")
        strLeague.add("French Ligue 1")
        strLeague.add("Spanish La Liga")
        strLeague.add("Greek Superleague Greece")
        strLeague.add("Dutch Eredivisie")
        strLeague.add("Belgian Jupiler League")

        val strBadge = ArrayList<String>()
        val imgBaseURL = "https://www.thesportsdb.com/images/media/league/badge"
        strBadge.add("$imgBaseURL/i6o0kh1549879062.png")
        strBadge.add("$imgBaseURL/m7urjx1535732496.png")
        strBadge.add("$imgBaseURL/vw72bl1534096708.png")
        strBadge.add("$imgBaseURL/0j55yv1534764799.png")
        strBadge.add("$imgBaseURL/ocy2fe1566216901.png")
        strBadge.add("$imgBaseURL/8f5jmf1516458074.png")
        strBadge.add("$imgBaseURL/7onmyv1534768460.png")
        strBadge.add("$imgBaseURL/g5rxbr1517435561.png")
        strBadge.add("$imgBaseURL/ywoi5k1534590331.png")
        strBadge.add("$imgBaseURL/8y3jti1564838854.png")

        leagueList.clear()
        for (i in idLeague.indices) {
            leagueList.add(
                League(
                idLeague[i],
                strLeague[i],
                strBadge[i]))
        }
        leagueListMutableLiveData.value = leagueList

        return leagueListMutableLiveData
    }
}