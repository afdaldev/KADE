package id.afdaldev.footballmatchscheduleapp.search

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Search
import id.afdaldev.footballmatchscheduleapp.data.model.Team
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    fun getSearchMatch(search: String): MutableLiveData<Search> {
        val searchList = MutableLiveData<Search>()
        APIService().getSearch(search).enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                d("TAG", "loadSearchOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                searchList.value = response.body()
            }
        })
        return searchList
    }

    fun getSearchTeams(search: String): MutableLiveData<Team> {
        val data = MutableLiveData<Team>()
        APIService().getSearchTeams(search).enqueue(object : Callback<Team> {
            override fun onFailure(call: Call<Team>, t: Throwable) {
                d("TAG", "searchTeamsOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                data.value = response.body()
            }
        })
        return data
    }
}