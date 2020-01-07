package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.favoriteteam.FavoriteTeamFragment
import id.afdaldev.footballmatchscheduleapp.utils.PagerAdapter
import kotlinx.android.synthetic.main.fragment_pager.*

class FavoritePagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter =
            PagerAdapter(
                childFragmentManager
            )
        pagerAdapter.addFragment(
            FavoriteEventFragment.newInstance(FavoriteEventFragment.pastEventFavorite),
            "Previous"
        )
        pagerAdapter.addFragment(
            FavoriteEventFragment.newInstance(FavoriteEventFragment.nextEventFavorite),
            "Next"
        )
        pagerAdapter.addFragment(
            FavoriteTeamFragment(),
            "Team"
        )

        viewpager.adapter = pagerAdapter
    }
}