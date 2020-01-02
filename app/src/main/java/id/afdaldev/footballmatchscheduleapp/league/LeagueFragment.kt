package id.afdaldev.footballmatchscheduleapp.league


import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import id.afdaldev.footballmatchscheduleapp.favoriteevent.FavoritePagerFragment
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueFragment
import id.afdaldev.footballmatchscheduleapp.search.SearchAdapter
import id.afdaldev.footballmatchscheduleapp.search.SearchViewModel
import id.afdaldev.footballmatchscheduleapp.utils.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutManager: LinearLayoutManager
    private val searchViewModel: SearchViewModel by viewModel()
    private val leagueViewModel: LeagueViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private var searchList: MutableList<SearchItem> = mutableListOf()
    private var soccerList: List<SearchItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = requireActivity().findViewById(R.id.recyclerView)
        progressBar = requireActivity().findViewById(R.id.progressBar)

        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        leagueAdapter = LeagueAdapter {
            shareViewModel.setIdLeague(it.idLeague.toString())
            addFragment(LookUpLeagueFragment(), R.id.fragment_container)
        }

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
        val searchMenu = menu.findItem(R.id.search)
        val searchView = searchMenu.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMatch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchMatch(newText.toString())
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                replaceFragment(FavoritePagerFragment(), R.id.fragment_container)
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun searchMatch(search: String) {
        searchAdapter = SearchAdapter {
            shareViewModel.setIdHomeTeam(it.idHomeTeam)
            shareViewModel.setIdAwayTeam(it.idAwayTeam)
            replaceFragment(LookUpEventFragment.newInstance(it.idEvent), R.id.fragment_container)
        }
        EspressoIdlingResource.increment()
        searchViewModel.getSearchFromAPI(search).observe(this, Observer {
            if (it.event.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "No Data for $search", Toast.LENGTH_SHORT).show()
                EspressoIdlingResource.decrement()
            } else {
                searchList.clear()
                searchList.addAll(it.event)
                EspressoIdlingResource.decrement()
            }
        })

        filterSearch()
    }

    private fun filterSearch() {
        soccerList = searchList.filter {
            it.strSport.contains("Soccer")
        }
        searchAdapter.setSearch(soccerList)
        recyclerView.adapter = searchAdapter
    }
}
