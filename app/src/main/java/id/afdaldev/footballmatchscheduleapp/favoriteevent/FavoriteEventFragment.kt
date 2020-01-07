package id.afdaldev.footballmatchscheduleapp.favoriteevent


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_PARAM = "param"

class FavoriteEventFragment : Fragment() {

    private lateinit var favoriteEventAdapter: FavoriteEventAdapter

    private val favoriteEventViewModel: FavoriteEventViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    private var favoriteEventList: MutableList<EventItem> = mutableListOf()
    private var pastEventList: List<EventItem> = mutableListOf()
    private var nextEventList: List<EventItem> = mutableListOf()

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteEventAdapter = FavoriteEventAdapter {
            shareViewModel.setIdHomeTeam(it.idHomeTeam.toString())
            shareViewModel.setIdAwayTeam(it.idAwayTeam.toString())
            shareViewModel.setIdEvent(it.idEvent)
            replaceFragment(LookUpEventFragment(), R.id.fragment_container)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        progressBar.visible()
    }

    override fun onResume() {
        super.onResume()
        favoriteEventViewModel.getAllFavoriteEvent().observe(this, Observer {
            progressBar.gone()
            favoriteEventList.clear()
            favoriteEventList.addAll(it)
        })
        when (param) {
            pastEventFavorite -> {
                showPastEvent()
            }
            nextEventFavorite -> {
                showNextEvent()
            }
        }
    }

    private fun showPastEvent() {
        pastEventList = favoriteEventList.filterNot {
            it.intHomeScore.isNullOrBlank()
        }
        isValidateList(pastEventList)
        favoriteEventAdapter.setFavoriteList(pastEventList)
        recyclerView.adapter = favoriteEventAdapter
    }

    private fun showNextEvent() {
        nextEventList = favoriteEventList.filter {
            it.intHomeScore.isNullOrBlank()
        }
        isValidateList(nextEventList)
        favoriteEventAdapter.setFavoriteList(nextEventList)
        recyclerView.adapter = favoriteEventAdapter
    }

    private fun isValidateList(eventList: List<EventItem>) {
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
            FavoriteEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}
