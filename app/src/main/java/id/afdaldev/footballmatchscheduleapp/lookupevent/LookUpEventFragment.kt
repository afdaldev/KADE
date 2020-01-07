package id.afdaldev.footballmatchscheduleapp.lookupevent


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.utils.FavoriteMenuBaseFragment
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteEventViewModel
import id.afdaldev.footballmatchscheduleapp.team.TeamViewModel
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.fragment_look_up_event.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LookUpEventFragment : FavoriteMenuBaseFragment() {

    private val lookUpEventViewModel: LookUpEventViewModel by viewModel()
    private val favoriteEventViewModel: FavoriteEventViewModel by viewModel()
    private val teamViewModel: TeamViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    private var eventList: MutableList<EventItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_look_up_event, container, false)
    }

    override fun onResume() {
        super.onResume()
        progressBarEvent.visible()
        showLookUpEvent()
        showHomeTeam()
        showAwayTeam()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite)
                    favoriteEventViewModel.deleteEventById(eventList[0].idEvent)
                else
                    favoriteEventViewModel.insertEventToFavorite(eventList[0])
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showLookUpEvent() {
        val idEvent = shareViewModel.idEvent.value.toString()
        eventFavoriteState(idEvent)
        lookUpEventViewModel.getLookUpEvent(idEvent).observe(this, Observer {
            progressBarEvent.gone()
            val event = it.events
            eventList.addAll(event)

            initView(event[0])
            favoriteMenu.isVisible = true
        })
    }

    private fun eventFavoriteState(idEvent: String) {
        val id = favoriteEventViewModel.getEventById(idEvent)
        isFavorite = id != null
    }

    private fun initView(data: EventItem) {
        tvStrEvent.text = data.strEvent
        tvStrDate.text = data.strDate
        tvHomeTeam.text = data.strHomeTeam
        tvAwayTeam.text = data.strAwayTeam

        val homeTeamScore: Any? = data.intHomeScore
        val awayTeamScore: Any? = data.intAwayScore
        tvHomeScore.text = homeTeamScore?.toString() ?: "-"
        tvAwayScore.text = awayTeamScore?.toString() ?: "-"

        val homeTeamGoals: Any? = data.strHomeGoalDetails
        val awayTeamGoals: Any? = data.strAwayGoalDetails
        tvHomeTeamGoals.text = homeTeamGoals?.toString()
        tvAwayTeamGoals.text = awayTeamGoals?.toString()

        val homeTeamYellowCard: Any? = data.strHomeYellowCards
        val awayTeamYellowCard: Any? = data.strAwayYellowCards
        tvHomeTeamYellowCard.text = homeTeamYellowCard?.toString()
        tvAwayTeamYellowCard.text = awayTeamYellowCard?.toString()

        val homeTeamRedCard: Any? = data.strHomeRedCards
        val awayTeamRedCard: Any? = data.strAwayRedCards
        tvHomeTeamRedCard.text = homeTeamRedCard?.toString()
        tvAwayTeamRedCard.text = awayTeamRedCard?.toString()
    }

    private fun showHomeTeam() {
        val idHomeTeam = shareViewModel.idHomeTeam.value.toString()
        teamViewModel.getHomeTeam(idHomeTeam).observe(this, Observer {
            val homeTeam = it.teams[0]
            imgPicasso(
                homeTeam.strTeamBadge.toString(),
                imgHomeTeam
            )
        })
    }

    private fun showAwayTeam() {
        val idAwayTeam = shareViewModel.idAwayTeam.value.toString()
        teamViewModel.getAwayTeam(idAwayTeam).observe(this, Observer {
            val awayTeam = it.teams[0]
            imgPicasso(
                awayTeam.strTeamBadge.toString(),
                imgAwayTeam
            )
        })
    }
}
