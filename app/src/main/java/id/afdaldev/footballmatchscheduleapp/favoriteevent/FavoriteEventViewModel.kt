package id.afdaldev.footballmatchscheduleapp.favoriteevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.local.FavoriteDatabase
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem

class FavoriteEventViewModel(private val database: FavoriteDatabase) : ViewModel() {

    fun getAllFavoriteEvent(): LiveData<List<EventItem>> =
        database.eventDao().getEventFromLocal()

    fun insertEventToFavorite(event: EventItem) =
        database.eventDao().insertEvent(event)

    fun getEventById(idEvent: String) =
        database.eventDao().getEventById(idEvent)


    fun deleteEventById(idEvent: String) {
        database.eventDao().deleteEventById(idEvent)
    }
}