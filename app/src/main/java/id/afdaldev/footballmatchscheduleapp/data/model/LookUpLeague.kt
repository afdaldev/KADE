package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class LookUpLeague(
    @SerializedName("leagues")
    val leagues: List<LookUpLeagueItem>
)