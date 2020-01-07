package id.afdaldev.footballmatchscheduleapp.players


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.PlayerItem
import id.afdaldev.footballmatchscheduleapp.lookupplayer.LookUpPlayerFragment
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.replaceFragment
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNCHECKED_CAST")
class PlayerFragment : Fragment() {

    private lateinit var playerAdapter: PlayerAdapter
    private val shareViewModel: ShareViewModel by sharedViewModel()
    private val playerViewModel: PlayerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        playerAdapter = PlayerAdapter {
            shareViewModel.setIdPlayer(it.idPlayer.toString())
            replaceFragment(LookUpPlayerFragment(), R.id.fragment_container)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        val teamName = shareViewModel.teamName.value.toString()
        playerViewModel.getSearchPLayers(teamName).observe(this, Observer {
            progressBar.gone()
            if (it.player.isNullOrEmpty())
                Toast.makeText(context, "No Players", Toast.LENGTH_SHORT).show()
            else
                playerAdapter.setPlayers(it.player as List<PlayerItem>)
        })
        recyclerView.adapter = playerAdapter
    }
}
