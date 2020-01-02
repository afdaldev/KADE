package id.afdaldev.footballmatchscheduleapp.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARG_MATCH = "match"

class EventFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private lateinit var eventAdapter: EventAdapter

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_MATCH)
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
        recyclerView.layoutManager = LinearLayoutManager(context)

        eventAdapter = EventAdapter {
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
        progressBar.visible()
        val idLeague = shareViewModel.idLeague.value.toString()
        if (param == pastEvent)
            showPastEvent(idLeague)
        else
            showNextEvent(idLeague)

        recyclerView.adapter = eventAdapter
        progressBar.gone()
    }

    private fun showPastEvent(idLeague: String) {
        eventViewModel.getPastEvent(idLeague).observe(this, Observer {
            eventAdapter.setEvent(it.events)
        })
    }

    private fun showNextEvent(idLeague: String) {
        eventViewModel.getNextEvent(idLeague).observe(this, Observer {
            eventAdapter.setEvent(it.events)
        })
    }

    companion object {

        const val pastEvent = "pastEvent"
        const val nextEvent = "nextEvent"

        @JvmStatic
        fun newInstance(param: String) =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MATCH, param)
                }
            }
    }
}
