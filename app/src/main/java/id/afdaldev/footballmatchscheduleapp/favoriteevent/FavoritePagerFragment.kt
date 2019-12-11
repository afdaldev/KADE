package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.afdaldev.footballmatchscheduleapp.utils.PagerAdapter

import id.afdaldev.footballmatchscheduleapp.R
import kotlinx.android.synthetic.main.fragment_pager.*

class FavoritePagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagerAdapter =
            PagerAdapter(
                childFragmentManager
            )
        pagerAdapter.addFragment(
            FavoriteFragment.newInstance(FavoriteFragment.pastEventFavorite),
            "Previous"
        )
        pagerAdapter.addFragment(
            FavoriteFragment.newInstance(FavoriteFragment.nextEventFavorite),
            "Next"
        )

        viewpager.adapter = pagerAdapter
    }
}