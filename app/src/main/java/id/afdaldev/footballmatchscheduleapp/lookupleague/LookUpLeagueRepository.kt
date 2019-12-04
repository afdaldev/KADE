package id.afdaldev.footballmatchscheduleapp.lookupleague

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpLeague
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LookUpLeagueRepository {

    fun getLookUpLeague(idLeague: String): MutableLiveData<LookUpLeague> {
        val lookUpLeague = MutableLiveData<LookUpLeague>()
        APIService().getLookUpLeague(idLeague).enqueue(object : Callback<LookUpLeague> {
            override fun onFailure(call: Call<LookUpLeague>, t: Throwable) {
                d("TAG", "lookUpLeagueOnFailure ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<LookUpLeague>, response: Response<LookUpLeague>) {
                lookUpLeague.value = response.body()
            }
        })
        return lookUpLeague
    }
}