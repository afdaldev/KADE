package id.afdaldev.footballmatchscheduleapp.lookupleague


import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpLeagueItem
import id.afdaldev.footballmatchscheduleapp.event.EventFragment
import id.afdaldev.footballmatchscheduleapp.lookupallteams.LookUpAllTeamsFragment
import id.afdaldev.footballmatchscheduleapp.lookuptable.LookUpTableFragment
import id.afdaldev.footballmatchscheduleapp.utils.*
import kotlinx.android.synthetic.main.look_up_layout.*
import kotlinx.android.synthetic.main.top_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LookUpLeagueFragment : Fragment() {

    private val lookUpLeagueViewModel: LookUpLeagueViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar
        return inflater.inflate(R.layout.look_up_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(EventFragment(), "Match")
        pagerAdapter.addFragment(LookUpTableFragment(), "Standings")
        pagerAdapter.addFragment(LookUpAllTeamsFragment(), "Team")
        viewPager.adapter = pagerAdapter
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        val idLeague = shareViewModel.idLeague.value.toString()
        lookUpLeagueViewModel.getLookUpLeagueList(idLeague).observe(this, Observer {
            if (it.leagues.isNotEmpty()) {
                progressBar.gone()
                initView(it.leagues[0])
            } else {
                d("TAG", " KOSONG COY")
            }
        })
    }

    private fun initView(data: LookUpLeagueItem) {
        tvName.text = data.strLeague
        tvDescription.text = data.strDescriptionEN
        imgPicasso(data.strBadge.toString(), imgBadge)
    }
}
