package id.afdaldev.footballmatchscheduleapp

import android.app.Application
import id.afdaldev.footballmatchscheduleapp.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FootballMatchScheduleApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@FootballMatchScheduleApp)
            modules(listOf(
                leagueModule,
                lookUpLeagueModule,
                eventModule,
                lookUpEventModule,
                favoriteEventModule,
                teamModule,
                shareViewModelModule,
                searchModule
            ))
        }
    }
}