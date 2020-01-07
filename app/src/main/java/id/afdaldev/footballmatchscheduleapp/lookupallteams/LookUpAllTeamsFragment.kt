package id.afdaldev.footballmatchscheduleapp.lookupallteams


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.team.LookUpTeamFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LookUpAllTeamsFragment : Fragment() {

    private lateinit var lookUpAllTeamsAdapter: LookUpAllTeamsAdapter
    private val lookUpAllTeamsViewModel: LookUpAllTeamsViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        lookUpAllTeamsAdapter = LookUpAllTeamsAdapter {
            shareViewModel.setIdTeam(it.idTeam)
            shareViewModel.setTeamName(it.strTeam.toString())
            replaceFragment(LookUpTeamFragment(), R.id.fragment_container)
        }
    }

    override fun onResume() {
        super.onResume()
        val idLeague = shareViewModel.idLeague.value.toString()
        progressBar.visible()
        lookUpAllTeamsViewModel.lookUpAllTeams(idLeague).observe(this, Observer {
            progressBar.gone()
            lookUpAllTeamsAdapter.setLookUpAllTeams(it.teams)
        })
        recyclerView.adapter = lookUpAllTeamsAdapter
    }
}
