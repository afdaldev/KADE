package id.afdaldev.footballmatchscheduleapp.lookupevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Event

class LookUpEventViewModel(private val lookUpEventRepository: LookUpEventRepository) : ViewModel() {

    fun getLookUpEvent(idEvent: String): LiveData<Event> = lookUpEventRepository.getLookUpEvent(idEvent)
}