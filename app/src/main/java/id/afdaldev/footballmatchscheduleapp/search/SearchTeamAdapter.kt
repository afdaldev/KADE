package id.afdaldev.footballmatchscheduleapp.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.card_item.view.*

class SearchTeamAdapter(private val listener: (TeamItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var teamList: List<TeamItem> = emptyList()

    fun setTeamList(data: List<TeamItem>) {
        this.teamList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return SearchTeamViewHolder(view)
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = teamList[position]
        when (holder) {
            is SearchTeamViewHolder -> holder.bind(list)
        }

        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class SearchTeamViewHolder(itemView: View) : BaseViewHolder<TeamItem>(itemView) {
        override fun bind(item: TeamItem) {
            itemView.tvName.text = item.strTeam
            imgPicasso(item.strTeamBadge.toString(), itemView.imgBadge)
        }
    }
}