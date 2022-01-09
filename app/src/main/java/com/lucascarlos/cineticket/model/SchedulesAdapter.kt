package com.lucascarlos.cineticket.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R

class SchedulesAdapter(private val context: Context, private val schedulesList: List<String>) :
    RecyclerView.Adapter<SchedulesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder =
        SchedulesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        )

    override fun getItemCount(): Int = schedulesList.size

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        val item = schedulesList[position]
        holder.schedule.text = item
    }

}

class SchedulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val schedule: TextView = itemView.findViewById(R.id.schedule)
}