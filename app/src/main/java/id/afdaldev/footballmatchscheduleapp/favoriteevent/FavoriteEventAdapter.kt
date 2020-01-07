package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import kotlinx.android.synthetic.main.match_vertical_item.view.*

class FavoriteEventAdapter(private val listener: (EventItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var favoriteEventList: List<EventItem> = emptyList()

    fun setFavoriteList(data: List<EventItem>) {
        this.favoriteEventList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.match_vertical_item, parent, false)
        return FavoriteEventViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteEventList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = favoriteEventList[position]
        when (holder) {
            is FavoriteEventViewHolder -> holder.bind(list)
        }
        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class FavoriteEventViewHolder(itemView: View) : BaseViewHolder<EventItem>(itemView) {
        override fun bind(item: EventItem) {
            itemView.tvStrEvent.text = item.strEvent
            itemView.tvStrDate.text = item.strDate
            itemView.tvHomeTeam.text = item.strHomeTeam
            itemView.tvAwayTeam.text = item.strAwayTeam

            val homeScore: String? = item.intHomeScore
            val awayScore: String? = item.intAwayScore

            itemView.tvHomeScore.text = homeScore ?: "-"
            itemView.tvAwayScore.text = awayScore ?: "-"
        }
    }
}