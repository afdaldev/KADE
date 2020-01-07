package id.afdaldev.footballmatchscheduleapp.favoriteteam


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.team.LookUpTeamFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTeamFragment : Fragment() {

    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter
    private val favoriteTeamViewModel: FavoriteTeamViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        favoriteTeamAdapter = FavoriteTeamAdapter {
            shareViewModel.setIdTeam(it.idTeam)
            replaceFragment(LookUpTeamFragment(), R.id.fragment_container)
        }
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        progressBar.visible()
        favoriteTeamViewModel.getAllFavoriteTeam().observe(this, Observer {
            progressBar.gone()
            if (it.isEmpty())
                Snackbar.make(requireView(), "No data in Favorite", Snackbar.LENGTH_SHORT).show()
            else
                favoriteTeamAdapter.setFavoriteTeamList(it)
        })
        recyclerView.adapter = favoriteTeamAdapter
    }
}
