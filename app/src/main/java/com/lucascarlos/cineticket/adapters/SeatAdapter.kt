package com.lucascarlos.cineticket.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R


class SeatAdapter(
    private val context: Context,
    private val seatList: List<String>,
    private val y: String,
) :
    RecyclerView.Adapter<SeatViewHolder>() {

    private lateinit var selectedSeat: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {

        return SeatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.seat_item, parent, false)
        )
    }

    override fun getItemCount(): Int = seatList.size

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val item = seatList[position]

        if (item == "true") {
            holder.rectangleAvailable.visibility = View.VISIBLE
        } else if (item == "false") {
            holder.rectangleNoAvailable.visibility = View.VISIBLE
        }

        holder.rectangleAvailable.setOnClickListener {
            holder.rectangleSelected.visibility = View.VISIBLE
            holder.rectangleAvailable.visibility = View.GONE

            selectedSeat = "${y}${position + 1}"

            updateSelectedSeat()
        }

        holder.rectangleSelected.setOnClickListener{
            holder.rectangleSelected.visibility = View.GONE
            holder.rectangleAvailable.visibility = View.VISIBLE

            selectedSeat = "${y}${position + 1}"
            updateSelectedSeat()
        }
    }

    private fun updateSelectedSeat() {
        val intent = Intent("message_subject_intent")
        intent.putExtra("selectedSeat", selectedSeat)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

}

class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rectangleAvailable: ImageView = itemView.findViewById(R.id.rectangleAvailable)
    val rectangleNoAvailable: ImageView = itemView.findViewById(R.id.rectangleNoAvailable)
    val rectangleSelected: ImageView = itemView.findViewById(R.id.rectangleSelected)
}