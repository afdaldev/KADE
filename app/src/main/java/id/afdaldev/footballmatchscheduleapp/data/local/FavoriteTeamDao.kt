package id.afdaldev.footballmatchscheduleapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem

@Dao
interface FavoriteTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: TeamItem)

    @Query("SELECT * FROM ${TeamItem.TABLE_NAME}")
    fun getTeamFromLocal(): LiveData<List<TeamItem>>

    @Query("SELECT ${TeamItem.COLUMN_ID} FROM ${TeamItem.TABLE_NAME} WHERE ${TeamItem.COLUMN_ID} = :id LIMIT 1")
    fun getTeamById(id: String): String?

    @Query("DELETE FROM ${TeamItem.TABLE_NAME} WHERE ${TeamItem.COLUMN_ID} = :id ")
    fun deleteTeamById(id: String)
}