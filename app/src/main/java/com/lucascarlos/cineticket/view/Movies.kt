package com.lucascarlos.cineticket.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lucascarlos.cineticket.R
import com.lucascarlos.cineticket.api.MyRetrofit
import com.lucascarlos.cineticket.model.Movies
import com.lucascarlos.cineticket.adapters.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies : Fragment(R.layout.activity_movies) {

    lateinit var recycleMovies: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = requireView().findViewById(R.id.progress_bar)

        recycleMovies = requireView().findViewById(R.id.recycleMovies)
        recycleMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        getData()
    }


    private fun getData() {
        val call: Call<List<Movies>> =
            MyRetrofit.instance?.moviesApi()?.getMoviesApi() as Call<List<Movies>>
        call.enqueue(object : Callback<List<Movies>> {
            override fun onResponse(call: Call<List<Movies>>, response: Response<List<Movies>>) {
                val adapter =
                    MoviesAdapter(requireContext(), response.body()?.toList() as List<Movies>, null)
                recycleMovies.adapter = adapter
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Movies>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                t.message?.let { Log.e("Err", it) }
                progressBar.visibility = View.GONE
            }

        })
    }
}