package id.afdaldev.footballmatchscheduleapp.event

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.utils.*
import kotlinx.android.synthetic.main.recyclerview.*

private const val ARG_MATCH = "match"

class EventFragment : Fragment() {

    private lateinit var eventAdapter: EventAdapter
    private lateinit var eventViewModel: EventViewModel

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_MATCH)
            d("TAG", "PARAM LOOK : $param")
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
            setIdHomeTeam(it.idHomeTeam.toString())
            setIdAwayTeam(it.idAwayTeam.toString())
            replaceFragment(LookUpEventFragment.newInstance(it.idEvent.toString()), R.id.fragment_container)
        }
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        val viewModelFactory =
            ViewModelFactory(
                getIdLeague()
            )
        eventViewModel  = ViewModelProviders.of(this, viewModelFactory)[EventViewModel::class.java]
        if (param == pastEvent) showPastEvent() else showNextEvent()
        recyclerView.adapter = eventAdapter
        progressBar.gone()
    }

    private fun showPastEvent() {
        eventViewModel.getPastEvent().observe(this, Observer {
            eventAdapter.setEvent(it.events)
        })
    }

    private fun showNextEvent() {
        eventViewModel.getNextEvent().observe(this, Observer {
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
