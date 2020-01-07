package id.afdaldev.footballmatchscheduleapp.lookupplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpPlayerItem
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.top_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LookUpPlayerFragment : Fragment() {

    private val shareViewModel: ShareViewModel by sharedViewModel()
    private val lookUpPlayerViewModel: LookUpPlayerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        val idPlayer = shareViewModel.idPlayer.value.toString()
        progressBar.visible()
        lookUpPlayerViewModel.getLookUpPlayer(idPlayer).observe(this, Observer {
            progressBar.gone()
            initView(it.players[0])
        })
    }

    private fun initView(data: LookUpPlayerItem?) {
        tvName.text = data?.strPlayer
        tvDescription.text = data?.strDescriptionEN
        imgPicasso(data?.strThumb.toString(), imgBadge)
    }
}