package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("events")
    val events: List<EventItem>
)