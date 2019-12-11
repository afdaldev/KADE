package id.afdaldev.footballmatchscheduleapp.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.League
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.league_list.view.*

class LeagueAdapter(private val listener: (League) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    private var leaguesList: List<League> = emptyList()

    fun setLeague(league: List<League>) {
        this.leaguesList = league
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.league_list, parent, false)
        return LeagueViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = leaguesList.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leaguesList[position]
        holder.tvStrLeague.text = league.strLeague
        imgPicasso(
            league.strBadge.toString(),
            holder.strBadge
        )

        holder.itemView.setOnClickListener { listener(league) }
    }

    class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val strBadge: AppCompatImageView = itemView.imgStrBadge
        val tvStrLeague: AppCompatTextView = itemView.tvStrLeague
    }
}