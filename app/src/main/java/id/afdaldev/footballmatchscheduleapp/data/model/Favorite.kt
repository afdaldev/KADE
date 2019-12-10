package id.afdaldev.footballmatchscheduleapp.data.model


class Favorite(
    val id: Long?,
    val idEvent: String?,
    val strEvent: String?,
    val strDate: String?,
    val idHomeTeam: String?,
    val strHomeTeam: String?,
    val intHomeScore: String?,
    val idAwayTeam: String?,
    val strAwayTeam: String?,
    val intAwayScore: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"

        const val EVENT_ID = "EVENT_ID"
        const val EVENT: String = "EVENT"
        const val DATE: String = "DATE"

        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"

        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}