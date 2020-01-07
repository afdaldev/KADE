package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class LookUpPlayer(
    @SerializedName("players")
    val players: List<LookUpPlayerItem?>
)