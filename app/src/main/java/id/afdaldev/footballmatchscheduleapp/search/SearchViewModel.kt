package id.afdaldev.footballmatchscheduleapp.search

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Search
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    fun getSearchFromAPI(text: String): MutableLiveData<Search> {
        val search = MutableLiveData<Search>()
        APIService().getSearch(text).enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                d("TAG", "loadSearchOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                search.value = response.body()
            }
        })
        return search
    }
}