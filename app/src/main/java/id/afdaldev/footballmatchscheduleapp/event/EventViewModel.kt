package id.afdaldev.footballmatchscheduleapp.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Event

class EventViewModel(private val eventRepository: EventRepository) : ViewModel() {

    fun getPastEvent(idLeague: String): LiveData<Event> = eventRepository.getPastEvent(idLeague)

    fun getNextEvent(idLeague: String): LiveData<Event> = eventRepository.getNextEvent(idLeague)
}