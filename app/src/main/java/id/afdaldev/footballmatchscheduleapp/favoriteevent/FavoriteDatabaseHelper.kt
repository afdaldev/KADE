package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import org.jetbrains.anko.db.*

class FavoriteDatabaseHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {

    init {
        instance = this
    }

    companion object {
        private var instance: FavoriteDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteDatabaseHelper {
            if (instance == null) {
                instance = FavoriteDatabaseHelper(ctx.applicationContext)
            }
            return instance as FavoriteDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.EVENT to TEXT,
            Favorite.DATE to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: FavoriteDatabaseHelper
    get() = FavoriteDatabaseHelper.getInstance(this)