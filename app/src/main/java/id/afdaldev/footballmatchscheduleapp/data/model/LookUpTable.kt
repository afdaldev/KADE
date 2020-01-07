package id.afdaldev.footballmatchscheduleapp.data.model


import com.google.gson.annotations.SerializedName

data class LookUpTable(
    @SerializedName("table")
    val table: List<LookUpTableItem?>
)