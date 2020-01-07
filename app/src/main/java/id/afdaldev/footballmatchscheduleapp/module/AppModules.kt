package id.afdaldev.footballmatchscheduleapp.module

import id.afdaldev.footballmatchscheduleapp.data.local.FavoriteDatabase
import id.afdaldev.footballmatchscheduleapp.event.EventRepository
import id.afdaldev.footballmatchscheduleapp.event.EventViewModel
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteEventViewModel
import id.afdaldev.footballmatchscheduleapp.favoriteteam.FavoriteTeamViewModel
import id.afdaldev.footballmatchscheduleapp.league.LeagueRepository
import id.afdaldev.footballmatchscheduleapp.league.LeagueViewModel
import id.afdaldev.footballmatchscheduleapp.lookupallteams.LookUpAllTeamsRepository
import id.afdaldev.footballmatchscheduleapp.lookupallteams.LookUpAllTeamsViewModel
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventRepository
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventViewModel
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueRepository
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueViewModel
import id.afdaldev.footballmatchscheduleapp.lookupplayer.LookUpPlayerRepository
import id.afdaldev.footballmatchscheduleapp.lookupplayer.LookUpPlayerViewModel
import id.afdaldev.footballmatchscheduleapp.lookuptable.LookUpTableRepository
import id.afdaldev.footballmatchscheduleapp.lookuptable.LookUpTableViewModel
import id.afdaldev.footballmatchscheduleapp.players.PlayerRepository
import id.afdaldev.footballmatchscheduleapp.players.PlayerViewModel
import id.afdaldev.footballmatchscheduleapp.search.SearchViewModel
import id.afdaldev.footballmatchscheduleapp.team.TeamRepository
import id.afdaldev.footballmatchscheduleapp.team.TeamViewModel
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val leagueModule = module {
    single { LeagueRepository() }
    viewModel { LeagueViewModel(get()) }
}

val lookUpLeagueModule = module {
    single { LookUpLeagueRepository() }
    viewModel { LookUpLeagueViewModel(get()) }
}

val eventModule = module {
    single { EventRepository() }
    viewModel { EventViewModel(get()) }
}

val lookUpEventModule = module {
    single { LookUpEventRepository() }
    viewModel { LookUpEventViewModel(get()) }
}

val teamModule = module {
    single { TeamRepository() }
    viewModel { TeamViewModel(get()) }
}

val lookUpTableModule = module {
    single { LookUpTableRepository() }
    viewModel { LookUpTableViewModel(get()) }
}

val lookUpAllTeams = module {
    single { LookUpAllTeamsRepository() }
    viewModel { LookUpAllTeamsViewModel(get()) }
}

val shareViewModelModule = module {
    viewModel { ShareViewModel() }
}

val searchModule = module {
    viewModel { SearchViewModel() }
}

val searchPlayerModule = module {
    single { PlayerRepository() }
    viewModel { PlayerViewModel(get()) }
}

val lookUpPlayerModule = module {
    single { LookUpPlayerRepository() }
    viewModel { LookUpPlayerViewModel(get()) }
}

val favoriteTeamModule = module {
    single { FavoriteDatabase.getDatabase(androidContext()) }
    viewModel { FavoriteTeamViewModel(get()) }
    viewModel { FavoriteEventViewModel(get()) }
}