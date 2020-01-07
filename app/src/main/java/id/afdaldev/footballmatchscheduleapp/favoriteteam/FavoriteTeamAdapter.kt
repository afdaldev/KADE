package id.afdaldev.footballmatchscheduleapp.favoriteteam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.TeamItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.card_item.view.*

class FavoriteTeamAdapter(private val listener: (TeamItem) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var favoriteTeamList: List<TeamItem> = emptyList()

    fun setFavoriteTeamList(data: List<TeamItem>) {
        this.favoriteTeamList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return FavoriteTeamViewHolder(view)
    }

    override fun getItemCount(): Int = favoriteTeamList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = favoriteTeamList[position]
        when (holder) {
            is FavoriteTeamViewHolder -> holder.bind(list)
        }

        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class FavoriteTeamViewHolder(itemView: View) : BaseViewHolder<TeamItem>(itemView) {
        override fun bind(item: TeamItem) {
            itemView.tvName.text = item.strTeam
            imgPicasso(item.strTeamBadge.toString(), itemView.imgBadge)
        }
    }
}