package id.afdaldev.footballmatchscheduleapp.module

import id.afdaldev.footballmatchscheduleapp.event.EventRepository
import id.afdaldev.footballmatchscheduleapp.event.EventViewModel
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteEventRepository
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteEventViewModel
import id.afdaldev.footballmatchscheduleapp.league.LeagueRepository
import id.afdaldev.footballmatchscheduleapp.league.LeagueViewModel
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventRepository
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventViewModel
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.TeamRepository
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.TeamViewModel
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueRepository
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueViewModel
import id.afdaldev.footballmatchscheduleapp.search.SearchViewModel
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
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

val favoriteEventModule = module {
    single { FavoriteEventRepository(get()) }
    viewModel { FavoriteEventViewModel(get()) }
}

val teamModule = module {
    single { TeamRepository() }
    viewModel { TeamViewModel(get()) }
}

val shareViewModelModule = module {
    viewModel { ShareViewModel() }
}

val searchModule = module {
    viewModel { SearchViewModel() }
}