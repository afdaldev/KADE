package id.afdaldev.footballmatchscheduleapp.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import kotlinx.android.synthetic.main.fragment_team_info.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TeamInfoFragment : Fragment() {

    private val shareViewModel: ShareViewModel by sharedViewModel()
    private val teamViewModel: TeamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_info, container, false)
    }

    override fun onResume() {
        super.onResume()
        val idTeam = shareViewModel.idTeam.value.toString()
        teamViewModel.getTeam(idTeam).observe(this, Observer {
            initView(it.teams[0])
        })
    }

    private fun initView(data: TeamItem) {
        chipTeamName.text = data.strTeam
        chipLeague.text = data.strLeague
        chipStadium.text = data.strStadium
        tvDescription.text = data.strDescriptionEN
    }
}
