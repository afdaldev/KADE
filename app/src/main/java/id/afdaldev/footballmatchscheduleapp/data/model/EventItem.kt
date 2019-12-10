package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class EventItem(
    @SerializedName("dateEvent")
    val dateEvent: String? = null,
    @SerializedName("dateEventLocal")
    val dateEventLocal: Any? = null,
    @SerializedName("idAwayTeam")
    val idAwayTeam: String? = null,
    @SerializedName("idEvent")
    val idEvent: String? = null,
    @SerializedName("idHomeTeam")
    val idHomeTeam: String? = null,
    @SerializedName("idLeague")
    val idLeague: String? = null,
    @SerializedName("idSoccerXML")
    val idSoccerXML: String? = null,
    @SerializedName("intAwayScore")
    val intAwayScore: Any? = null,
    @SerializedName("intAwayShots")
    val intAwayShots: Any? = null,
    @SerializedName("intHomeScore")
    val intHomeScore: Any? = null,
    @SerializedName("intHomeShots")
    val intHomeShots: Any? = null,
    @SerializedName("intRound")
    val intRound: String? = null,
    @SerializedName("intSpectators")
    val intSpectators: Any? = null,
    @SerializedName("strAwayFormation")
    val strAwayFormation: Any? = null,
    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String? = null,
    @SerializedName("strAwayLineupDefense")
    val strAwayLineupDefense: String? = null,
    @SerializedName("strAwayLineupForward")
    val strAwayLineupForward: String? = null,
    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String? = null,
    @SerializedName("strAwayLineupMidfield")
    val strAwayLineupMidfield: String? = null,
    @SerializedName("strAwayLineupSubstitutes")
    val strAwayLineupSubstitutes: String? = null,
    @SerializedName("strAwayRedCards")
    val strAwayRedCards: String? = null,
    @SerializedName("strAwayTeam")
    val strAwayTeam: String? = null,
    @SerializedName("strAwayYellowCards")
    val strAwayYellowCards: String? = null,
    @SerializedName("strBanner")
    val strBanner: Any? = null,
    @SerializedName("strCircuit")
    val strCircuit: Any? = null,
    @SerializedName("strCity")
    val strCity: Any? = null,
    @SerializedName("strCountry")
    val strCountry: Any? = null,
    @SerializedName("strDate")
    val strDate: String? = null,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: Any? = null,
    @SerializedName("strEvent")
    val strEvent: String? = null,
    @SerializedName("strEventAlternate")
    val strEventAlternate: String? = null,
    @SerializedName("strFanart")
    val strFanart: Any? = null,
    @SerializedName("strFilename")
    val strFilename: String? = null,
    @SerializedName("strHomeFormation")
    val strHomeFormation: Any? = null,
    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String? = null,
    @SerializedName("strHomeLineupDefense")
    val strHomeLineupDefense: String? = null,
    @SerializedName("strHomeLineupForward")
    val strHomeLineupForward: String? = null,
    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String? = null,
    @SerializedName("strHomeLineupMidfield")
    val strHomeLineupMidfield: String? = null,
    @SerializedName("strHomeLineupSubstitutes")
    val strHomeLineupSubstitutes: String? = null,
    @SerializedName("strHomeRedCards")
    val strHomeRedCards: String? = null,
    @SerializedName("strHomeTeam")
    val strHomeTeam: String? = null,
    @SerializedName("strHomeYellowCards")
    val strHomeYellowCards: String? = null,
    @SerializedName("strLeague")
    val strLeague: String? = null,
    @SerializedName("strLocked")
    val strLocked: String? = null,
    @SerializedName("strMap")
    val strMap: Any? = null,
    @SerializedName("strPoster")
    val strPoster: Any? = null,
    @SerializedName("strResult")
    val strResult: Any? = null,
    @SerializedName("strSeason")
    val strSeason: String? = null,
    @SerializedName("strSport")
    val strSport: String? = null,
    @SerializedName("strTVStation")
    val strTVStation: Any? = null,
    @SerializedName("strThumb")
    val strThumb: Any? = null,
    @SerializedName("strTime")
    val strTime: String? = null,
    @SerializedName("strTimeLocal")
    val strTimeLocal: String? = null,
    @SerializedName("strTweet1")
    val strTweet1: Any? = null,
    @SerializedName("strTweet2")
    val strTweet2: Any? = null,
    @SerializedName("strTweet3")
    val strTweet3: Any? = null,
    @SerializedName("strVideo")
    val strVideo: Any? = null
)