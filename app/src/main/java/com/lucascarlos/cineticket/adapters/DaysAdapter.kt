package com.lucascarlos.cineticket.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.model.Days

class DaysAdapter(private val context: Context, private val datesList: List<Days>) :
    RecyclerView.Adapter<DaysViewHolder>() {

    private var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {

        if (selectedItemPosition == 0) updateSelectDate(datesList[0].date, datesList[0].fullDate)

        return DaysViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.dates_item, parent, false)
        )
    }


    override fun getItemCount(): Int = datesList.size

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val item = datesList[position]
        holder.datesWeekday.text = item.weekday
        holder.datesDate.text = item.date

        holder.datesCard.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
            updateSelectDate(item.date, item.fullDate)
        }

        if (selectedItemPosition == position) {
            holder.datesWeekday.setTextColor(ContextCompat.getColor(context, R.color.red_500))
            holder.datesDate.setTextColor(ContextCompat.getColor(context, R.color.red_500))
        } else {
            holder.datesWeekday.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.datesDate.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    private fun updateSelectDate(date: String, fullDate: String?) {
        val intent = Intent("message_subject_intent")
        intent.putExtra("selectedDate", date)
        intent.putExtra("fullDate", fullDate)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}

class DaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val datesWeekday: TextView = itemView.findViewById(R.id.weekday)
    val datesDate: TextView = itemView.findViewById(R.id.date)
    val datesCard: CardView = itemView.findViewById(R.id.card)
}