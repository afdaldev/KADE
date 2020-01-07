package id.afdaldev.footballmatchscheduleapp.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.PlayerItem
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import id.afdaldev.footballmatchscheduleapp.utils.imgPicasso
import kotlinx.android.synthetic.main.player_item.view.*

class PlayerAdapter(private val listener: (PlayerItem) -> Unit) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var playerList: List<PlayerItem> = emptyList()

    fun setPlayers(data: List<PlayerItem>) {
        this.playerList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = playerList[position]
        when (holder) {
            is PlayerViewHolder -> holder.bind(list)
        }

        holder.itemView.setOnClickListener { listener(list) }
    }

    inner class PlayerViewHolder(itemView: View) : BaseViewHolder<PlayerItem>(itemView) {
        override fun bind(item: PlayerItem) {
            itemView.tvPlayerName.text = item.strPlayer
            itemView.tvPlayerDescription.text = item.strDescriptionEN
            imgPicasso(item.strThumb.toString(), itemView.imgBadge)
        }
    }
}