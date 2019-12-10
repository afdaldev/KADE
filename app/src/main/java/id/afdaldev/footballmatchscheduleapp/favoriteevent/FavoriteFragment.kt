package id.afdaldev.footballmatchscheduleapp.favoriteevent


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import kotlinx.android.synthetic.main.recyclerview.*

/**
 * A simple [Fragment] subclass.
 */

private const val ARG_PARAM = "param"

class FavoriteFragment : Fragment() {

    private lateinit var adapter: FavoriteEventAdapter
    private var favoriteList: MutableList<Favorite> = mutableListOf()
    private var pastList: List<Favorite> = mutableListOf()
    private var nextList: List<Favorite> = mutableListOf()
    private lateinit var favoriteViewModel: FavoriteViewModel

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
        favoriteViewModel =
            ViewModelProviders.of(this)[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progressBar.visible()
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = FavoriteEventAdapter {
            setIdHomeTeam(it.idHomeTeam.toString())
            setIdAwayTeam(it.idAwayTeam.toString())
            replaceFragment(
                LookUpEventFragment.newInstance(it.idEvent.toString()),
                R.id.fragment_container
            )
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getFavoriteEvent(requireContext())
        favoriteViewModel.favoriteEventList.observe(viewLifecycleOwner, Observer {
            progressBar.gone()
            favoriteList.clear()
            favoriteList.addAll(it)
        })

        if (param == pastEventFavorite) showPastEvent() else showNextEvent()
    }

    private fun showPastEvent() {
        pastList = favoriteList.filterNot {
            it.intHomeScore.equals("null")
        }
        isValidateList(pastList)
        adapter.setEvent(pastList)
        recyclerView.adapter = adapter
    }

    private fun showNextEvent() {
        nextList = favoriteList.filter {
            it.intHomeScore.equals("null")
        }
        isValidateList(nextList)
        adapter.setEvent(nextList)
        recyclerView.adapter = adapter
    }

    private fun isValidateList(eventList: List<Favorite>) {
        if (eventList.isEmpty())
            Snackbar.make(requireView(), "No data in Favorite", Snackbar.LENGTH_SHORT).show()
        else
            return
    }

    companion object {

        const val pastEventFavorite = "pastEventFavorite"
        const val nextEventFavorite = "nextEventFavorite"

        @JvmStatic
        fun newInstance(param: String) =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}
