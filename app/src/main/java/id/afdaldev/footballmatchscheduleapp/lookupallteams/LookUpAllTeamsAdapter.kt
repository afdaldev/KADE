package id.afdaldev.footballmatchscheduleapp.lookupallteams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.card_item.view.*

class LookUpAllTeamsAdapter(private val listener: (TeamItem) -> Unit) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var lookUpAllTeamsList: List<TeamItem> = emptyList()

    fun setLookUpAllTeams(data: List<TeamItem>) {
        this.lookUpAllTeamsList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return LookUpAllTeamsViewHolder(view)
    }

    override fun getItemCount(): Int = lookUpAllTeamsList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = lookUpAllTeamsList[position]
        when (holder) {
            is LookUpAllTeamsViewHolder -> holder.bind(list)
        }
        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class LookUpAllTeamsViewHolder(itemView: View) : BaseViewHolder<TeamItem>(itemView) {
        override fun bind(item: TeamItem) {
            itemView.tvName.text = item.strTeam
            imgPicasso(item.strTeamBadge.toString(), itemView.imgBadge)
        }
    }
}