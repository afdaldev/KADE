package id.afdaldev.footballmatchscheduleapp.league


import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueFragment
import id.afdaldev.footballmatchscheduleapp.search.SearchAdapter
import id.afdaldev.footballmatchscheduleapp.search.SearchViewModel

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var leagueLayoutManager: LinearLayoutManager
    private lateinit var searchLayoutManager: LinearLayoutManager
    private var searchList: MutableList<SearchItem> = mutableListOf()
    private var soccerList: List<SearchItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        progressBar.visible()
        leagueLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = leagueLayoutManager

        leagueAdapter = LeagueAdapter {
            setIdLeague(it.idLeague.toString())
            addFragment(LookUpLeagueFragment(), R.id.fragment_container)
        }

        val leagueViewModel = ViewModelProviders.of(this)[LeagueViewModel::class.java]
        leagueViewModel.getLeague().observe(this, Observer {
            progressBar.gone()
            leagueAdapter.setLeague(it)
        })
        recyclerView.adapter = leagueAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchSomething(query.toString())
                Toast.makeText(context, "Search : $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun searchSomething(e: String) {
        progressBar.visible()
        searchLayoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = searchLayoutManager
        searchAdapter = SearchAdapter {
            setIdHomeTeam(it.idHomeTeam)
            setIdAwayTeam(it.idAwayTeam)
            replaceFragment(LookUpEventFragment.newInstance(it.idEvent), R.id.fragment_container)
        }

        val searchViewModel = ViewModelProviders.of(this)[SearchViewModel::class.java]
        searchViewModel.loadSearch(e).observe(this, Observer {
            searchList.clear()
            searchList.addAll(it.event)
        })
        filterSoccer()
    }

    private fun filterSoccer(){
        soccerList = searchList.filter {
            it.strSport.contains("Soccer")
        }
        progressBar.gone()
        searchAdapter.setSearch(soccerList)
        recyclerView.adapter = searchAdapter
    }
}
