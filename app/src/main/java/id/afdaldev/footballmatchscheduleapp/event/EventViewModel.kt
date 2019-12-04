package id.afdaldev.footballmatchscheduleapp.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Event

class EventViewModel(idLeague: String) : ViewModel() {

    private val eventRepository = EventRepository()
    private var pastEventList = MutableLiveData<Event>()
    private var nextEventList = MutableLiveData<Event>()

    init {
        pastEventList = eventRepository.getPastEvent(idLeague)
        nextEventList = eventRepository.getNextEvent(idLeague)
    }

    fun getPastEvent(): LiveData<Event> = pastEventList

    fun getNextEvent(): LiveData<Event> = nextEventList
}