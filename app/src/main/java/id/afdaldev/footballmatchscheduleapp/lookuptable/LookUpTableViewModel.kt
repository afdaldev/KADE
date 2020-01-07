package id.afdaldev.footballmatchscheduleapp.lookuptable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class LookUpTableViewModel(private val lookUpTableRepository: LookUpTableRepository) : ViewModel() {

    fun lookUpTable(idLeague: String) = liveData(Dispatchers.IO) {
        val data = lookUpTableRepository.getLookUpTable(idLeague)
        emit(data)
    }
}