package id.afdaldev.footballmatchscheduleapp.utils

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import id.afdaldev.footballmatchscheduleapp.R

open class FavoriteMenuBaseFragment : Fragment() {

    private var menuItem: Menu? = null

    protected lateinit var favoriteMenu: MenuItem
    protected var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        favoriteMenu = menu.findItem(R.id.favorite)
        menuItem = menu
        setFavorite()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        favoriteMenu.isVisible = false
    }

    protected fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                requireContext().getDrawable(R.drawable.ic_favorite_black_24dp)
        else
            menuItem?.getItem(0)?.icon =
                requireContext().getDrawable(R.drawable.ic_favorite_border_black_24dp)
    }
}