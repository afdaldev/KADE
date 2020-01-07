package id.afdaldev.footballmatchscheduleapp.lookuptable


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpTableItem
import id.afdaldev.footballmatchscheduleapp.utils.ShareViewModel
import id.afdaldev.footballmatchscheduleapp.utils.gone
import id.afdaldev.footballmatchscheduleapp.utils.visible
import kotlinx.android.synthetic.main.recyclerview.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
@Suppress("UNCHECKED_CAST")
class LookUpTableFragment : Fragment() {

    private lateinit var lookUpTableAdapter: LookUpTableAdapter
    private val lookUpTableViewModel: LookUpTableViewModel by viewModel()
    private val shareViewModel: ShareViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recyclerview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lookUpTableAdapter = LookUpTableAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        progressBar.visible()
        val idLeague = shareViewModel.idLeague.value.toString()
        lookUpTableViewModel.lookUpTable(idLeague).observe(this, Observer {
            progressBar.gone()
            lookUpTableAdapter.setLookUpTable(it.table as List<LookUpTableItem>)
        })
        recyclerView.adapter = lookUpTableAdapter
    }

}
