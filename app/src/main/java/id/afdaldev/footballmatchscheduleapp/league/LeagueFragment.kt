package id.afdaldev.footballmatchscheduleapp.league


import android.os.Bundle
import android.util.Log.d
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.data.model.League
import id.afdaldev.footballmatchscheduleapp.data.model.Search
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import id.afdaldev.footballmatchscheduleapp.data.network.APIService
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.lookupleague.LookUpLeagueFragment
import id.afdaldev.footballmatchscheduleapp.search.SearchAdapter
import kotlinx.android.synthetic.main.recyclerview.progressBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment() {

    private lateinit var leagueAdapter: LeagueAdapter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var leagueLayoutManager: LinearLayoutManager
    private lateinit var searchLayoutManager: LinearLayoutManager
    private var league: MutableList<League> = mutableListOf()
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
        progressBar.visible()
        recyclerView = view!!.findViewById(R.id.recyclerView)

        initData()
        leagueLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = leagueLayoutManager

        leagueAdapter = LeagueAdapter {
            setIdLeague(it.idLeague.toString())
            addFragment(LookUpLeagueFragment(), R.id.fragment_container)
        }
        progressBar.gone()
        leagueAdapter.setLeague(league)
        recyclerView.adapter = leagueAdapter
    }

    private fun initData() {
        val idLeague = resources.getStringArray(R.array.idLeague)
        val strLeague = resources.getStringArray(R.array.strLeague)
        val strBadge = resources.getStringArray(R.array.strBadge)
        league.clear()
        for (i in idLeague.indices) {
            league.add(
                League(
                    idLeague[i],
                    strLeague[i],
                    strBadge[i]
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchSomething(query.toString())
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
        APIService().getSearch(e).enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                d("TAG", "searchOnFailure : ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val responses = response.body()?.event
                searchList.clear()
                if (responses != null) searchList.addAll(responses) else Toast.makeText(context, "No Data For : $e", Toast.LENGTH_SHORT).show()
            }
        })
        filterSoccer()
        recyclerView.adapter = searchAdapter
    }

    private fun filterSoccer(){
        soccerList = searchList.filter {
            it.strSport.contains("Soccer")
        }
        progressBar.gone()
        searchAdapter.setSearch(soccerList)
    }
}
