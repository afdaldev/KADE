package id.afdaldev.footballmatchscheduleapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.utils.Converter

@Database(entities = [TeamItem::class, EventItem::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun eventDao(): FavoriteEventDao
    abstract fun teamDao(): FavoriteTeamDao

    companion object {

        private var INSTANCE: FavoriteDatabase? = null
        private val lock = Any()
        private const val favorite_db = "favorite_db"

        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        favorite_db
                    ).allowMainThreadQueries()
                        .build()
                }
                return INSTANCE as FavoriteDatabase
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}