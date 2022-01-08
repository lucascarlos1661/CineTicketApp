package com.lucascarlos.cineticket.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R

class DaysAdapter(private val context: Context, private val datesList: List<Days>) :
    RecyclerView.Adapter<DaysViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder =
        DaysViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.dates_item, parent, false)
        )

    override fun getItemCount(): Int = datesList.size

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        val item = datesList[position]
        holder.datesWeekday.text = item.weekday
        holder.datesDate.text = item.date
    }
}

class DaysViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val datesWeekday: TextView = itemView.findViewById(R.id.weekday)
    val datesDate: TextView = itemView.findViewById(R.id.date)
}