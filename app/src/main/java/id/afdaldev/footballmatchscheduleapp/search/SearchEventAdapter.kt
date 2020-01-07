package id.afdaldev.footballmatchscheduleapp.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import kotlinx.android.synthetic.main.match_vertical_item.view.*


class SearchEventAdapter(private val listener: (SearchItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var searchList: List<SearchItem> = emptyList()

    fun setSearch(search: List<SearchItem>) {
        this.searchList = search
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.match_vertical_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int = searchList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = searchList[position]
        when (holder) {
            is SearchViewHolder -> holder.bind(list)
        }

        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class SearchViewHolder(itemView: View) : BaseViewHolder<SearchItem>(itemView) {
        override fun bind(item: SearchItem) {
            itemView.tvStrEvent.text = item.strEvent
            itemView.tvStrDate.text = item.strDate
            itemView.tvHomeTeam.text = item.strHomeTeam
            itemView.tvAwayTeam.text = item.strAwayTeam

            val homeScore = item.intHomeScore ?: "-"
            val awayScore = item.intAwayScore ?: "-"

            itemView.tvHomeScore.text = homeScore
            itemView.tvAwayScore.text = awayScore

        }
    }
}