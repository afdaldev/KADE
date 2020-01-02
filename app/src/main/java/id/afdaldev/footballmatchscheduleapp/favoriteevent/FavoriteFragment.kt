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
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */

private const val ARG_PARAM = "param"

class FavoriteFragment : Fragment() {

    private lateinit var adapter: FavoriteEventAdapter
    private var favoriteList: MutableList<Favorite> = mutableListOf()
    private var pastList: List<Favorite> = mutableListOf()
    private var nextList: List<Favorite> = mutableListOf()
    private val favoriteEventViewModel: FavoriteEventViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

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
        progressBar.visible()
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = FavoriteEventAdapter {
            shareViewModel.setIdHomeTeam(it.idHomeTeam.toString())
            shareViewModel.setIdAwayTeam(it.idAwayTeam.toString())
            replaceFragment(
                LookUpEventFragment.newInstance(it.idEvent.toString()),
                R.id.fragment_container
            )
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteEventViewModel.getFavorite().observe(this, Observer {
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
