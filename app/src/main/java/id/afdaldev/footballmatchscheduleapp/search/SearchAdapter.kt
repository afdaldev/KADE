package id.afdaldev.footballmatchscheduleapp.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.SearchItem
import kotlinx.android.synthetic.main.match_list.view.*

class SearchAdapter(private val listener: (SearchItem) -> Unit) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private var searchList: List<SearchItem> = emptyList()


    fun setSearch(search: List<SearchItem>) {
        this.searchList = search
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.match_list, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount(): Int = searchList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search = searchList[position]

        val event: Any? = search.strEvent
        val date: Any? = search.strDate
        val homeTeam: Any? = search.strHomeTeam
        val awayTeam: Any? = search.strAwayTeam
        val homeScore: Any? = search.intHomeScore
        val awayScore: Any? = search.intAwayScore

        val nullValue = "-"

        holder.tvStrEvent.text = event?.toString() ?: nullValue
        holder.tvStrDate.text = date?.toString() ?: nullValue
        holder.tvStrHomeTeam.text = homeTeam?.toString() ?: nullValue
        holder.tvStrAwayTeam.text = awayTeam?.toString() ?: nullValue
        holder.tvStrHomeTeamScore.text = homeScore?.toString() ?: nullValue
        holder.tvStrAwayTeamScore.text = awayScore?.toString() ?: nullValue

        holder.itemView.setOnClickListener { listener(search) }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStrEvent: AppCompatTextView = itemView.tvStrEvent
        val tvStrDate: AppCompatTextView = itemView.tvStrDate
        val tvStrHomeTeam: AppCompatTextView = itemView.tvHomeTeam
        val tvStrHomeTeamScore: AppCompatTextView = itemView.tvHomeScore
        val tvStrAwayTeam: AppCompatTextView = itemView.tvAwayTeam
        val tvStrAwayTeamScore: AppCompatTextView = itemView.tvAwayScore
    }
}