package id.afdaldev.footballmatchscheduleapp.team


import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.utils.FavoriteMenuBaseFragment
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.favoriteteam.FavoriteTeamViewModel
import id.afdaldev.footballmatchscheduleapp.players.PlayerFragment
import id.afdaldev.footballmatchscheduleapp.utils.PagerAdapter
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.look_up_layout.*
import kotlinx.android.synthetic.main.top_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LookUpTeamFragment : FavoriteMenuBaseFragment() {

    private val teamViewModel: TeamViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private val favoriteTeamViewModel: FavoriteTeamViewModel by viewModel()

    private var teamList: MutableList<TeamItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.look_up_layout, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite)
                    favoriteTeamViewModel.deleteTeamById(teamList[0].idTeam)
                else
                    favoriteTeamViewModel.insertTeamToFavorite(teamList[0])
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val pagerAdapter = PagerAdapter(childFragmentManager)
        pagerAdapter.addFragment(TeamInfoFragment(), "Team Info")
        pagerAdapter.addFragment(PlayerFragment(), "Player")
        viewPager.adapter = pagerAdapter
    }

    override fun onResume() {
        super.onResume()
        val idTeam = shareViewModel.idTeam.value.toString()
        teamFavoriteState(idTeam)
        teamViewModel.getTeam(idTeam).observe(this, Observer {
            val team = it.teams
            teamList.addAll(team)

            initView(team[0])
            favoriteMenu.isVisible = true
        })
    }

    private fun teamFavoriteState(idTeam: String) {
        val id = favoriteTeamViewModel.getTeamById(idTeam)
        isFavorite = id != null
    }

    private fun initView(data: TeamItem) {
        imgPicasso(data.strTeamBadge.toString(), imgBadge)
        tvName.text = data.strTeam
        tvDescription.text = data.strDescriptionEN
    }
}
