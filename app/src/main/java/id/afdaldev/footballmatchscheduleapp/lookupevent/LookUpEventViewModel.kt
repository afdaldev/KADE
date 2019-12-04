package id.afdaldev.footballmatchscheduleapp.lookupevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.Event

class LookUpEventViewModel(idEvent: String) : ViewModel() {

    private val lookUpEventRepository = LookUpEventRepository()
    private var lookUpEventList = MutableLiveData<Event>()

    init {
        lookUpEventList = lookUpEventRepository.getLookUpEvent(idEvent)
    }

    fun getLookUpEvent() : LiveData<Event> = lookUpEventList
}