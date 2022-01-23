package com.lucascarlos.cineticket.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.view.ChooseSeat

class SchedulesAdapter(
    private val context: Context,
    private val schedulesList: List<String>,
    private val date: String,
    private val roomNumber: String,
    private val movieTitle: String,
    private val posterUrl: String
) :
    RecyclerView.Adapter<SchedulesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchedulesViewHolder =
        SchedulesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        )

    override fun getItemCount(): Int = schedulesList.size

    override fun onBindViewHolder(holder: SchedulesViewHolder, position: Int) {
        val item = schedulesList[position]
        holder.schedule.text = item

        holder.scheduleRectangle.setOnClickListener {
            val intent = Intent(context, ChooseSeat::class.java)
            intent.putExtra("movieTitle", movieTitle)
            intent.putExtra("posterUrl", posterUrl)
            intent.putExtra("roomNumber", roomNumber)
            intent.putExtra("date", date)
            intent.putExtra("time", item)
            context.startActivity(intent)
        }
    }
}

class SchedulesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val schedule: TextView = itemView.findViewById(R.id.schedule)
    val scheduleRectangle: ImageView = itemView.findViewById(R.id.rectangle)
}