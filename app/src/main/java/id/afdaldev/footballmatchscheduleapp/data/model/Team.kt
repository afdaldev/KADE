package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("teams")
    val teams: List<TeamItem>
)