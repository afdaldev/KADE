package id.afdaldev.footballmatchscheduleapp.league


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoritePagerFragment
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueFragment
import id.afdaldev.footballmatchscheduleapp.search.SearchEventAdapter
import id.afdaldev.footballmatchscheduleapp.search.SearchTeamAdapter
import id.afdaldev.footballmatchscheduleapp.search.SearchViewModel
import id.afdaldev.footballmatchscheduleapp.team.LookUpTeamFragment
import id.afdaldev.footballmatchscheduleapp.utils.*
import kotlinx.android.synthetic.main.league_recycler_view.*
import kotlinx.android.synthetic.main.recyclerview.progressBar
import kotlinx.android.synthetic.main.recyclerview.recyclerView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeagueFragment : Fragment() {

    private lateinit var searchMenu: MenuItem
    private lateinit var searchEventView: SearchView

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var searchEventAdapter: SearchEventAdapter
    private lateinit var searchTeamAdapter: SearchTeamAdapter

    private val searchViewModel: SearchViewModel by viewModel()
    private val leagueViewModel: LeagueViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private var searchList: MutableList<SearchItem> = mutableListOf()
    private var soccerList: List<SearchItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar
        return inflater.inflate(R.layout.league_recycler_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        leagueAdapter = LeagueAdapter {
            shareViewModel.setIdLeague(it.idLeague.toString())
            addFragment(LookUpLeagueFragment(), R.id.fragment_container)
        }
        showLeague()
        searchTeams()
    }

    private fun showLeague() {
        progressBar.visible()
        leagueViewModel.getLeague().observe(this, Observer {
            if (it.isNotEmpty()) {
                progressBar.gone()
                leagueAdapter.setLeague(it)
            }
        })
        recyclerView.adapter = leagueAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        searchMenu = menu.findItem(R.id.searchEvent)
        searchEventView = searchMenu.actionView as SearchView
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                replaceFragment(
                    FavoritePagerFragment(),
                    R.id.fragment_container
                )
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        searchMatches()
    }

    private fun searchMatches() {
        searchEventView.queryHint = " Search Event"
        searchEventView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMatch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchMatch(newText.toString())
                return true
            }
        })
    }

    private fun searchMatch(searchEvent: String) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        searchEventAdapter = SearchEventAdapter {
            shareViewModel.setIdHomeTeam(it.idHomeTeam.toString())
            shareViewModel.setIdAwayTeam(it.idAwayTeam.toString())
            shareViewModel.setIdEvent(it.idEvent.toString())
            replaceFragment(LookUpEventFragment(), R.id.fragment_container)
        }
        EspressoIdlingResource.increment()
        searchViewModel.getSearchMatch(searchEvent).observe(this, Observer {
            if (it.event.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "No Data for $searchEvent", Toast.LENGTH_SHORT)
                    .show()
                EspressoIdlingResource.decrement()
            } else {
                searchList.clear()
                searchList.addAll(it.event)
                EspressoIdlingResource.decrement()
            }
        })
        soccerList = searchList.filter {
            it.strSport.contains("Soccer")
        }
        searchEventAdapter.setSearch(soccerList)
        recyclerView.adapter = searchEventAdapter
    }

    private fun searchTeams() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        searchTeam.queryHint = "Search Team"
        searchTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchTeam(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchTeam(newText.toString())
                return true
            }
        })
    }

    private fun searchTeam(searchTeam: String) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        searchTeamAdapter = SearchTeamAdapter {
            shareViewModel.setIdTeam(it.idTeam)
            shareViewModel.setTeamName(it.strTeam.toString())
            replaceFragment(LookUpTeamFragment(), R.id.fragment_container)
        }
        EspressoIdlingResource.increment()
        searchViewModel.getSearchTeams(searchTeam).observe(this, Observer {
            if (it.teams.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "No Data for $searchTeam", Toast.LENGTH_SHORT)
                    .show()
                EspressoIdlingResource.decrement()
            } else {
                searchTeamAdapter.setTeamList(it.teams)
                EspressoIdlingResource.decrement()
            }
        })
        recyclerView.adapter = searchTeamAdapter
    }
}
