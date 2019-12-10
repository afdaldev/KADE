package id.afdaldev.footballmatchscheduleapp.search

import android.content.Context
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Search
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private var searchList = MutableLiveData<Search>()

    private fun getSearchFromAPI(text: String, context: Context): MutableLiveData<Search> {
        val search = MutableLiveData<Search>()
        APIService().getSearch(text).enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                d("TAG", "loadSearchOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val responses = response.body()?.event
                if (responses != null)
                    search.value = response.body()
                else
                    Toast.makeText(context, "No Data For : $text", Toast.LENGTH_SHORT).show()
            }
        })
        return search
    }

    fun loadSearch(text: String, context: Context) : LiveData<Search>{
        searchList = getSearchFromAPI(text, context)
        return searchList
    }
}