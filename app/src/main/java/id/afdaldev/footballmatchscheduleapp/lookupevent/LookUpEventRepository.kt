package id.afdaldev.footballmatchscheduleapp.lookupevent

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import id.afdaldev.footballmatchscheduleapp.data.model.Event
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LookUpEventRepository {

    fun getLookUpEvent(idEvent: String): MutableLiveData<Event> {
        val lookUpEventList = MutableLiveData<Event>()
        APIService().getLookUpEvent(idEvent).enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                d("TAG", "getLookUpEventOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                lookUpEventList.value = response.body()
            }
        })
        return lookUpEventList
    }
}