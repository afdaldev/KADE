package id.afdaldev.footballmatchscheduleapp.favoriteevent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.Favorite
import kotlinx.android.synthetic.main.match_list.view.*

class FavoriteEventAdapter(private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteEventAdapter.FavoriteViewHolder>() {

    private var favoriteEventList: List<Favorite> = emptyList()

    fun setEvent(favorite: List<Favorite>) {
        this.favoriteEventList = favorite
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.match_list, parent, false)
        return FavoriteViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = favoriteEventList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteEvent = favoriteEventList[position]
        val homeScore: Any? = favoriteEvent.intHomeScore
        val awayScore: Any? = favoriteEvent.intAwayScore

        holder.tvEvent.text = favoriteEvent.strEvent
        holder.tvDate.text = favoriteEvent.strDate
        holder.tvHomeTeam.text = favoriteEvent.strHomeTeam
        holder.tvAwayTeam.text = favoriteEvent.strAwayTeam

        if (homeScore != "null")
            holder.tvHomeScore.text = homeScore.toString()
        else holder.tvHomeScore.text = "-"

        if (awayScore != "null")
            holder.tvAwayScore.text = awayScore.toString()
        else holder.tvAwayScore.text = "-"

        holder.itemView.setOnClickListener { listener(favoriteEvent) }
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEvent: AppCompatTextView = itemView.tvStrEvent
        val tvDate: AppCompatTextView = itemView.tvStrDate
        val tvHomeTeam: AppCompatTextView = itemView.tvHomeTeam
        var tvHomeScore: AppCompatTextView = itemView.tvHomeScore
        val tvAwayTeam: AppCompatTextView = itemView.tvAwayTeam
        val tvAwayScore: AppCompatTextView = itemView.tvAwayScore
    }
}