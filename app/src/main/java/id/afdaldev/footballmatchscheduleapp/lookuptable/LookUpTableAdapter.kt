package id.afdaldev.footballmatchscheduleapp.lookuptable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.utils.BaseViewHolder
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.LookUpTableItem
import kotlinx.android.synthetic.main.look_up_table_item.view.*

class LookUpTableAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var lookUpTableList: List<LookUpTableItem> = emptyList()

    fun setLookUpTable(data: List<LookUpTableItem>) {
        this.lookUpTableList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.look_up_table_item, parent, false)
        return LookUpTableViewHolder(view)
    }

    override fun getItemCount(): Int = lookUpTableList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val list = lookUpTableList[position]
        when (holder) {
            is LookUpTableViewHolder -> holder.bind(list)
        }
    }

    inner class LookUpTableViewHolder(itemView: View) : BaseViewHolder<LookUpTableItem>(itemView) {
        override fun bind(item: LookUpTableItem) {
            itemView.chipName.text = item.name
            itemView.chipPlayed.text = item.played.toString()
            itemView.chipGoalsFor.text = item.goalsfor.toString()
            itemView.chipGoalsAgainst.text = item.goalsagainst.toString()
            itemView.chipGoalsDifference.text = item.goalsdifference.toString()
            itemView.chipWin.text = item.win.toString()
            itemView.chipDraw.text = item.draw.toString()
            itemView.chipLoss.text = item.loss.toString()
            itemView.chipTotal.text = item.total.toString()
        }
    }
}