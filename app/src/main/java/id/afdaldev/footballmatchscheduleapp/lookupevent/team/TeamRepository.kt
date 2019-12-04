package id.afdaldev.footballmatchscheduleapp.lookupevent.team

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import id.afdaldev.footballmatchscheduleapp.data.model.Team
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository {

    fun getHomeTeam(idHomeTeam: String) : MutableLiveData<Team> {
        val homeTeamList = MutableLiveData<Team>()
        APIService().getTeams(idHomeTeam).enqueue(object : Callback<Team>{
            override fun onFailure(call: Call<Team>, t: Throwable) {
                d("TAG", "getHomeTeamOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                homeTeamList.value = response.body()
            }
        })
        return homeTeamList
    }

    fun getAwayTeam(idAwayTeam: String) : MutableLiveData<Team> {
        val awayTeamList = MutableLiveData<Team>()
        APIService().getTeams(idAwayTeam).enqueue(object : Callback<Team> {
            override fun onFailure(call: Call<Team>, t: Throwable) {
                d("TAG", "getAwayTeamOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                awayTeamList.value = response.body()
            }
        })
        return awayTeamList
    }
}