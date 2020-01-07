package id.afdaldev.footballmatchscheduleapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem

@Dao
interface FavoriteEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(event: EventItem)

    @Query("SELECT * FROM ${EventItem.TABLE_NAME}")
    fun getEventFromLocal(): LiveData<List<EventItem>>

    @Query("SELECT ${EventItem.COLUMN_ID} FROM ${EventItem.TABLE_NAME} WHERE ${EventItem.COLUMN_ID} = :id LIMIT 1")
    fun getEventById(id: String): String?

    @Query("DELETE FROM ${EventItem.TABLE_NAME} WHERE ${EventItem.COLUMN_ID} = :id ")
    fun deleteEventById(id: String)
}