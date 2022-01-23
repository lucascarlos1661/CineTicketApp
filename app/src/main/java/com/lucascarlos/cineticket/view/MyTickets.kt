package com.lucascarlos.cineticket.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.Ticket
import com.lucascarlos.cineticket.adapters.TicketsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTickets : Fragment(R.layout.activity_my_tickets) {

    lateinit var recyclerTickets: RecyclerView
    private var data: List<Ticket> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerTickets = requireView().findViewById(R.id.recyclerTickets)
        recyclerTickets.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        getData()
    }

    private fun getData() {
        val call: Call<List<Ticket>> =
            MyRetrofit.instance?.getTicketApi()?.getTicketsApi() as Call<List<Ticket>>
        call.enqueue(object : Callback<List<Ticket>> {
            override fun onResponse(call: Call<List<Ticket>>, response: Response<List<Ticket>>) {
                data = response.body()?.toList()!!
                val adapter =
                    TicketsAdapter(requireContext(), data)
                recyclerTickets.adapter = adapter
            }

            override fun onFailure(call: Call<List<Ticket>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        data = emptyList()
        getData()
    }
}