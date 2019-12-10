package id.afdaldev.footballmatchscheduleapp.lookupevent


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoriteViewModel
import id.afdaldev.footballmatchscheduleapp.favoriteevent.database
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.AwayTeamViewModel
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.HomeTeamViewModel
import kotlinx.android.synthetic.main.fragment_look_up_event.*
import kotlinx.android.synthetic.main.recyclerview.progressBar
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

private const val ARG_PARAM = "param"

class LookUpEventFragment : Fragment() {

    private var param: String? = null
    private var menuItem: Menu? = null
    private lateinit var favoriteMenu: MenuItem
    private var isFavorite: Boolean = false
    private var eventList: MutableList<EventItem> = mutableListOf()
    private lateinit var favoriteViewModel: FavoriteViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteViewModel = ViewModelProviders.of(this)[FavoriteViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
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
                favoriteViewModel.removeFromFavorite(requireContext(), param.toString())
                else
                    favoriteViewModel.addToFavorite(requireContext(), eventList[0])
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
        val viewModelFactory = ViewModelFactory(param.toString())
        val lookUpEventViewModel =
            ViewModelProviders.of(this, viewModelFactory)[LookUpEventViewModel::class.java]
        lookUpEventViewModel.getLookUpEvent().observe(this, Observer {
            progressBar.gone()
            favoriteMenu.isVisible = true
            
            eventList.addAll(it.events)

            val lookUpEvent = it.events[0]

            tvStrEvent.text = lookUpEvent.strEvent
            tvStrDate.text = lookUpEvent.strDate
            tvHomeTeam.text = lookUpEvent.strHomeTeam
            tvAwayTeam.text = lookUpEvent.strAwayTeam

            val homeTeamScore: Any? = lookUpEvent.intHomeScore
            val awayTeamScore: Any? = lookUpEvent.intAwayScore
            tvHomeScore.text = homeTeamScore?.toString() ?: "-"
            tvAwayScore.text = awayTeamScore?.toString() ?: "-"

            val homeTeamGoals: Any? = lookUpEvent.strHomeGoalDetails
            val awayTeamGoals: Any? = lookUpEvent.strAwayGoalDetails
            tvHomeTeamGoals.text = homeTeamGoals?.toString()
            tvAwayTeamGoals.text = awayTeamGoals?.toString()

            val homeTeamYellowCard: Any? = lookUpEvent.strHomeYellowCards
            val awayTeamYellowCard: Any? = lookUpEvent.strAwayYellowCards
            tvHomeTeamYellowCard.text = homeTeamYellowCard?.toString()
            tvAwayTeamYellowCard.text = awayTeamYellowCard?.toString()

            val homeTeamRedCard: Any? = lookUpEvent.strHomeRedCards
            val awayTeamRedCard: Any? = lookUpEvent.strAwayRedCards
            tvHomeTeamRedCard.text = homeTeamRedCard?.toString()
            tvAwayTeamRedCard.text = awayTeamRedCard?.toString()
        })
    }

    private fun showHomeTeam() {
        val viewModelFactory = ViewModelFactory(getIdHomeTeam())
        val teamViewModel =
            ViewModelProviders.of(this, viewModelFactory)[HomeTeamViewModel::class.java]
        teamViewModel.getHomeTeam().observe(this, Observer {
            val homeTeam = it.teams[0]
            imgPicasso(homeTeam.strTeamBadge, imgHomeTeam)
        })
    }

    private fun showAwayTeam() {
        val viewModelFactory = ViewModelFactory(getIdAwayTeam())
        val teamViewModel =
            ViewModelProviders.of(this, viewModelFactory)[AwayTeamViewModel::class.java]
        teamViewModel.getAwayTeam().observe(this, Observer {
            val awayTeam = it.teams[0]
            imgPicasso(awayTeam.strTeamBadge, imgAwayTeam)
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
