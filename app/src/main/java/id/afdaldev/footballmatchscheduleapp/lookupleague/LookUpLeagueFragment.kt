package id.afdaldev.footballmatchscheduleapp.lookupleague


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.afdaldev.footballmatchscheduleapp.*
import id.afdaldev.footballmatchscheduleapp.event.EventFragment
import id.afdaldev.footballmatchscheduleapp.utils.*

import kotlinx.android.synthetic.main.fragment_look_up_league.*

/**
 * A simple [Fragment] subclass.
 */
class LookUpLeagueFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_look_up_league, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter =
            PagerAdapter(
                childFragmentManager
            )
        pagerAdapter.addFragment(EventFragment.newInstance(EventFragment.pastEvent), "Previous")
        pagerAdapter.addFragment(EventFragment.newInstance(EventFragment.nextEvent), "Next")
        viewpagerEvent.adapter = pagerAdapter
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        val viewModelFactory =
            ViewModelFactory(
                getIdLeague()
            )
        val lookUpLeagueViewModel =
            ViewModelProviders.of(this, viewModelFactory)[LookUpLeagueViewModel::class.java]
        lookUpLeagueViewModel.getLookUpLeague().observe(this, Observer {
            progressBar.gone()
            val lookUpLeague = it.leagues[0]
            tvLeagueName.text = lookUpLeague.strLeague
            tvDescription.text = lookUpLeague.strDescriptionEN
            imgPicasso(
                lookUpLeague.strBadge,
                imgLeagueBadge
            )
        })
    }
}
