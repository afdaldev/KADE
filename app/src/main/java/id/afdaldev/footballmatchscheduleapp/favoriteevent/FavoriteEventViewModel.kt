package id.afdaldev.footballmatchscheduleapp.favoriteevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite

class FavoriteEventViewModel(private val favoriteEventRepository: FavoriteEventRepository) : ViewModel() {

    fun getFavorite(): LiveData<List<Favorite>> = favoriteEventRepository.getFavoriteEvent()

    fun addToFavorite(event: EventItem) {
        favoriteEventRepository.addToFavorite(event)
    }

    fun removeFromFavorite(id: String) {
        favoriteEventRepository.removeFromFavorite(id)
    }
}