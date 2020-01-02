package id.afdaldev.footballmatchscheduleapp.event

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import id.afdaldev.footballmatchscheduleapp.data.model.Event
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventRepository {

    fun getPastEvent(idLeague: String): MutableLiveData<Event> {
        val pastEventList = MutableLiveData<Event>()
        APIService().getPastEvent(idLeague).enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                d("TAG", "pastEventOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                pastEventList.value = response.body()
            }
        })
        return pastEventList
    }

    fun getNextEvent(idLeague: String): MutableLiveData<Event> {
        val nextEvent = MutableLiveData<Event>()
        APIService().getNextEvent(idLeague).enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                d("TAG", "nextEventOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                nextEvent.value = response.body()
            }
        })
        return nextEvent
    }
}