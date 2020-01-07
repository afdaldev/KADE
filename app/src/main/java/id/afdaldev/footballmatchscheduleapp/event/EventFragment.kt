package id.afdaldev.footballmatchscheduleapp.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.lookupevent.LookUpEventFragment
import id.afdaldev.footballmatchscheduleapp.utils.*
import kotlinx.android.synthetic.main.match_recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private lateinit var lastMatchAdapter: EventAdapter
    private lateinit var nextMatchAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(true)
        lastMatchAdapter = EventAdapter {
            setShareViewModel(
                it.idHomeTeam.toString(),
                it.idAwayTeam.toString(),
                it.idEvent)
            replaceFragment(
                LookUpEventFragment(),
                R.id.fragment_container
            )
        }

        nextMatchAdapter = EventAdapter {
            setShareViewModel(
                it.idHomeTeam.toString(),
                it.idAwayTeam.toString(),
                it.idEvent)
            replaceFragment(
                LookUpEventFragment(),
                R.id.fragment_container
            )
        }

        rvNextMatch.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvLastMatch.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    override fun onResume() {
        super.onResume()
        val idLeague = shareViewModel.idLeague.value.toString()
        showNextEvent(idLeague)
        showPastEvent(idLeague)
    }

    private fun showNextEvent(idLeague: String) {
        eventViewModel.getNextEvent(idLeague).observe(this, Observer {
            nextMatchAdapter.setEvent(it.events)
            rvNextMatch.adapter = nextMatchAdapter
        })
    }

    private fun showPastEvent(idLeague: String) {
        eventViewModel.getPastEvent(idLeague).observe(this, Observer {
            lastMatchAdapter.setEvent(it.events)
            rvLastMatch.adapter = lastMatchAdapter
            initView(false)
        })
    }

    private fun setShareViewModel(idHomeTeam: String, idAwayTeam: String, idEvent: String) {
        shareViewModel.setIdHomeTeam(idHomeTeam)
        shareViewModel.setIdAwayTeam(idAwayTeam)
        shareViewModel.setIdEvent(idEvent)
    }

    private fun initView(state: Boolean) {
        if (state) {
            pbMatch.visible()
            tvNextMatchLabel.invisible()
            tvLastMatchLabel.invisible()
        } else {
            pbMatch.gone()
            tvNextMatchLabel.visible()
            tvLastMatchLabel.visible()
        }
    }
}
