package id.afdaldev.footballmatchscheduleapp.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import id.afdaldev.footballmatchscheduleapp.R
import id.afdaldev.footballmatchscheduleapp.data.model.EventItem
import kotlinx.android.synthetic.main.match_horizontal_item.view.*

class EventAdapter(private val listener: (EventItem) -> Unit) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var eventList: List<EventItem> = emptyList()

    fun setEvent(event: List<EventItem>) {
        this.eventList = event
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.match_horizontal_item, parent, false)
        return EventViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val events = eventList[position]
        val homeScore: Any? = events.intHomeScore
        val awayScore: Any? = events.intAwayScore

        holder.tvEvent.text = events.strEvent
        holder.tvDate.text = events.strDate
        holder.tvHomeTeam.text = events.strHomeTeam
        holder.tvAwayTeam.text = events.strAwayTeam

        holder.tvHomeScore.text = homeScore?.toString() ?: "-"
        holder.tvAwayScore.text = awayScore?.toString() ?: "-"

        holder.itemView.setOnClickListener { listener(events) }
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEvent: AppCompatTextView = itemView.tvStrEvent
        val tvDate: AppCompatTextView = itemView.tvStrDate
        val tvHomeTeam: AppCompatTextView = itemView.tvHomeTeam
        var tvHomeScore: AppCompatTextView = itemView.tvHomeScore
        val tvAwayTeam: AppCompatTextView = itemView.tvAwayTeam
        val tvAwayScore: AppCompatTextView = itemView.tvAwayScore
    }
}