package id.afdaldev.footballmatchscheduleapp.lookupevent


import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.AwayTeamViewModel
import id.afdaldev.footballmatchscheduleapp.lookupevent.team.HomeTeamViewModel

import kotlinx.android.synthetic.main.fragment_look_up_event.*
import kotlinx.android.synthetic.main.recyclerview.progressBar

private const val ARG_PARAM = "param1"

class LookUpEventFragment : Fragment() {

    private var param: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_PARAM)
            d("TAG", "Param: $param")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_look_up_event, container, false)
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        showLookUpEvent()
        showHomeTeam()
        showAwayTeam()
    }

    private fun showLookUpEvent() {
        val viewModelFactory = ViewModelFactory(param.toString())
        val lookUpEventViewModel =
            ViewModelProviders.of(this, viewModelFactory)[LookUpEventViewModel::class.java]
        lookUpEventViewModel.getLookUpEvent().observe(this, Observer {
            progressBar.gone()
            val lookUpEvent = it.events[0]

            tvStrEvent.text = lookUpEvent.strEvent
            tvStrDate.text = lookUpEvent.strDate
            tvHomeTeam.text = lookUpEvent.strHomeTeam
            tvAwayTeam.text = lookUpEvent.strAwayTeam

            val homeTeamScore: Any? = lookUpEvent.intHomeScore
            val awayTeamScore: Any? = lookUpEvent.intAwayScore
            tvHomeScore.text = homeTeamScore?.toString() ?: "-"
            tvAwayScore.text = awayTeamScore?.toString() ?: "-"

            val homeTeamGoals: Any? = lookUpEvent.strHomeGoalDetails
            val awayTeamGoals: Any? = lookUpEvent.strAwayGoalDetails
            tvHomeTeamGoals.text = homeTeamGoals?.toString()
            tvAwayTeamGoals.text = awayTeamGoals?.toString()

            val homeTeamYellowCard: Any? = lookUpEvent.strHomeYellowCards
            val awayTeamYellowCard: Any? = lookUpEvent.strAwayYellowCards
            tvHomeTeamYellowCard.text = homeTeamYellowCard?.toString()
            tvAwayTeamYellowCard.text = awayTeamYellowCard?.toString()

            val homeTeamRedCard: Any? = lookUpEvent.strHomeRedCards
            val awayTeamRedCard: Any? = lookUpEvent.strAwayRedCards
            tvHomeTeamRedCard.text = homeTeamRedCard?.toString()
            tvAwayTeamRedCard.text = awayTeamRedCard?.toString()
        })
    }

    private fun showHomeTeam() {
        val viewModelFactory = ViewModelFactory(getIdHomeTeam())
        val teamViewModel = ViewModelProviders.of(this, viewModelFactory)[HomeTeamViewModel::class.java]
        teamViewModel.getHomeTeam().observe(this, Observer {
            val homeTeam = it.teams[0]
            imgPicasso(homeTeam.strTeamBadge, imgHomeTeam)
        })
    }

    private fun showAwayTeam() {
        val viewModelFactory = ViewModelFactory(getIdAwayTeam())
        val teamViewModel = ViewModelProviders.of(this, viewModelFactory)[AwayTeamViewModel::class.java]
        teamViewModel.getAwayTeam().observe(this, Observer {
            val awayTeam = it.teams[0]
            imgPicasso(awayTeam.strTeamBadge, imgAwayTeam)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param: String) =
            LookUpEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, param)
                }
            }
    }
}
