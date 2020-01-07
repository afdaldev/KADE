package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("player")
    val player: List<PlayerItem?>
)