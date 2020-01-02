package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.content.Context
import androidx.lifecycle.MutableLiveData
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteEventRepository(private val context: Context) {

    fun getFavoriteEvent(): MutableLiveData<List<Favorite>> {
        val favoriteEventList = MutableLiveData<List<Favorite>>()
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favoriteEventList.value = favorite
        }
        return favoriteEventList
    }

    fun addToFavorite(event: EventItem) {
        context.database.use {
            insert(
                Favorite.TABLE_FAVORITE,
                Favorite.EVENT_ID to event.idEvent,
                Favorite.EVENT to event.strEvent,
                Favorite.DATE to event.strDate,
                Favorite.HOME_TEAM_ID to event.idHomeTeam,
                Favorite.HOME_TEAM to event.strHomeTeam,
                Favorite.HOME_SCORE to event.intHomeScore.toString(),
                Favorite.AWAY_TEAM_ID to event.idAwayTeam,
                Favorite.AWAY_TEAM to event.strAwayTeam,
                Favorite.AWAY_SCORE to event.intAwayScore.toString()
            )
        }
    }

    fun removeFromFavorite(id: String) {
        context.database.use {
            delete(
                Favorite.TABLE_FAVORITE, "(${Favorite.EVENT_ID} = {id})",
                "id" to id
            )
        }
    }
}