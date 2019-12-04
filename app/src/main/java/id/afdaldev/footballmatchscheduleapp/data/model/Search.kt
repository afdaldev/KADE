package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("event")
    val event: List<SearchItem>
)