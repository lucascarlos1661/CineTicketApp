package com.lucascarlos.cineticket.model

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R

class RoomAdapter(private val context: Context, private val roomsList: List<Rooms>) :
    RecyclerView.Adapter<RoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder =
        RoomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        )

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val item = roomsList[position]

        holder.roomNumber.text = item.number
        holder.roomAudio.text = item.audio

        Log.e("teste", item.schedules.toString())

        setSchedulesItemRecycler(holder.recyclerSchedules, item.schedules.toList())
    }

    override fun getItemCount(): Int = roomsList.size

    private fun setSchedulesItemRecycler(
        recyclerView: RecyclerView,
        schedulesList: List<String>,
    ) {
        val itemRecyclerAdapter = SchedulesAdapter(context, schedulesList)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerAdapter
    }

}

class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val roomNumber: TextView = itemView.findViewById(R.id.room_number)
    val roomAudio: TextView = itemView.findViewById(R.id.room_audio)

    val recyclerSchedules: RecyclerView = itemView.findViewById(R.id.recyclerSchedules)
}