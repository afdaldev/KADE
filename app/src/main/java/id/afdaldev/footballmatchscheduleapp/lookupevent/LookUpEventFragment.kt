package id.afdaldev.footballmatchscheduleapp.lookupevent


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteEventViewModel
import id.afdaldev.footballmatchscheduleapp.favoriteevent.database
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.TeamViewModel
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.fragment_look_up_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM = "param"

class LookUpEventFragment : Fragment() {

    private var param: String? = null

    private var menuItem: Menu? = null
    private lateinit var favoriteMenu: MenuItem

    private var isFavorite: Boolean = false
    private var eventList: MutableList<EventItem> = mutableListOf()

    private val lookUpEventViewModel: LookUpEventViewModel by viewModel()
    private val favoriteEventViewModel: FavoriteEventViewModel by viewModel()
    private val teamViewModel: TeamViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
        setHasOptionsMenu(true)
        favoriteState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_look_up_event, container, false)
    }

    override fun onResume() {
        super.onResume()
        progressBarEvent.visible()
        showLookUpEvent()
        showHomeTeam()
        showAwayTeam()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        favoriteMenu = menu.findItem(R.id.favorite)
        menuItem = menu
        setFavorite()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        favoriteMenu.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite)
                    favoriteEventViewModel.removeFromFavorite(param.toString())
                else
                    favoriteEventViewModel.addToFavorite(eventList[0])
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                requireContext().getDrawable(R.drawable.ic_favorite_black_24dp)
        else
            menuItem?.getItem(0)?.icon =
                requireContext().getDrawable(R.drawable.ic_favorite_border_black_24dp)
    }

    private fun favoriteState() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(${Favorite.EVENT_ID} = {id})",
                    "id" to param.toString()
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun showLookUpEvent() {
        lookUpEventViewModel.getLookUpEvent(param.toString()).observe(this, Observer {
            progressBarEvent.gone()
            initView(it.events[0])
            favoriteMenu.isVisible = true

            eventList.addAll(it.events)
        })
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
                homeTeam.strTeamBadge,
                imgHomeTeam
            )
        })
    }

    private fun showAwayTeam() {
        val idAwayTeam = shareViewModel.idAwayTeam.value.toString()
        teamViewModel.getAwayTeam(idAwayTeam).observe(this, Observer {
            val awayTeam = it.teams[0]
            imgPicasso(
                awayTeam.strTeamBadge,
                imgAwayTeam
            )
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            LookUpEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}
