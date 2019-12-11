package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import id.afdaldev.footballmatchscheduleapp.utils.showToast
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FavoriteViewModel : ViewModel() {

    var favoriteEventList = MutableLiveData<List<Favorite>>()

    fun getFavoriteEvent(context: Context) {
        context.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favoriteEventList.value = favorite
        }
    }

    fun addToFavorite(context: Context, event: EventItem) {
        try {
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
            showToast(
                context,
                "Added to favorite"
            )
        } catch (e: SQLiteConstraintException) {
            showToast(
                context,
                "error : ${e.localizedMessage}"
            )
        }
    }

    fun removeFromFavorite(context: Context, param: String) {
        try {
            context.database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(${Favorite.EVENT_ID} = {id})",
                    "id" to param
                )
            }
            showToast(
                context,
                "Removed from favorite"
            )
        } catch (e: SQLiteConstraintException) {
            showToast(
                context,
                "Error : ${e.localizedMessage}"
            )
        }
    }
}