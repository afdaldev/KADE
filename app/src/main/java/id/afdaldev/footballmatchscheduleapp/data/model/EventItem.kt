package id.afdaldev.footballmatchscheduleapp.data.model

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = EventItem.TABLE_NAME)
data class EventItem(
    @SerializedName("dateEvent")
    val dateEvent: String?,
    @SerializedName("dateEventLocal")
    val dateEventLocal: String?,
    @SerializedName("idAPIfootball")
    val idAPIfootball: Any?,
    @SerializedName("idAwayTeam")
    val idAwayTeam: String?,
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("idHomeTeam")
    val idHomeTeam: String?,
    @SerializedName("idLeague")
    val idLeague: String?,
    @SerializedName("idSoccerXML")
    val idSoccerXML: String?,
    @SerializedName("intAwayScore")
    val intAwayScore: String?,
    @SerializedName("intAwayShots")
    val intAwayShots: Any?,
    @SerializedName("intHomeScore")
    val intHomeScore: String?,
    @SerializedName("intHomeShots")
    val intHomeShots: Any?,
    @SerializedName("intRound")
    val intRound: String?,
    @SerializedName("intSpectators")
    val intSpectators: Any?,
    @SerializedName("strAwayFormation")
    val strAwayFormation: Any?,
    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String?,
    @SerializedName("strAwayLineupDefense")
    val strAwayLineupDefense: String?,
    @SerializedName("strAwayLineupForward")
    val strAwayLineupForward: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String?,
    @SerializedName("strAwayLineupMidfield")
    val strAwayLineupMidfield: String?,
    @SerializedName("strAwayLineupSubstitutes")
    val strAwayLineupSubstitutes: String?,
    @SerializedName("strAwayRedCards")
    val strAwayRedCards: String?,
    @SerializedName("strAwayTeam")
    val strAwayTeam: String?,
    @SerializedName("strAwayYellowCards")
    val strAwayYellowCards: String?,
    @SerializedName("strBanner")
    val strBanner: Any?,
    @SerializedName("strCircuit")
    val strCircuit: Any?,
    @SerializedName("strCity")
    val strCity: Any?,
    @SerializedName("strCountry")
    val strCountry: Any?,
    @SerializedName("strDate")
    val strDate: String?,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: String?,
    @SerializedName("strEvent")
    val strEvent: String?,
    @SerializedName("strEventAlternate")
    val strEventAlternate: String?,
    @SerializedName("strFanart")
    val strFanart: Any?,
    @SerializedName("strFilename")
    val strFilename: String?,
    @SerializedName("strHomeFormation")
    val strHomeFormation: Any?,
    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String?,
    @SerializedName("strHomeLineupDefense")
    val strHomeLineupDefense: String?,
    @SerializedName("strHomeLineupForward")
    val strHomeLineupForward: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String?,
    @SerializedName("strHomeLineupMidfield")
    val strHomeLineupMidfield: String?,
    @SerializedName("strHomeLineupSubstitutes")
    val strHomeLineupSubstitutes: String?,
    @SerializedName("strHomeRedCards")
    val strHomeRedCards: String?,
    @SerializedName("strHomeTeam")
    val strHomeTeam: String?,
    @SerializedName("strHomeYellowCards")
    val strHomeYellowCards: String?,
    @SerializedName("strLeague")
    val strLeague: String?,
    @SerializedName("strLocked")
    val strLocked: String?,
    @SerializedName("strMap")
    val strMap: Any?,
    @SerializedName("strPoster")
    val strPoster: Any?,
    @SerializedName("strResult")
    val strResult: String?,
    @SerializedName("strSeason")
    val strSeason: String?,
    @SerializedName("strSport")
    val strSport: String?,
    @SerializedName("strTVStation")
    val strTVStation: Any?,
    @SerializedName("strThumb")
    val strThumb: Any?,
    @SerializedName("strTime")
    val strTime: String?,
    @SerializedName("strTimeLocal")
    val strTimeLocal: String?,
    @SerializedName("strTweet1")
    val strTweet1: String?,
    @SerializedName("strTweet2")
    val strTweet2: String?,
    @SerializedName("strTweet3")
    val strTweet3: String?,
    @SerializedName("strVideo")
    val strVideo: String?
){
    companion object {
        const val TABLE_NAME = "event_table"
        const val COLUMN_ID = BaseColumns._ID
    }
}