package com.lucascarlos.cineticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lucascarlos.cineticket.R

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2

    private var route: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        route = intent.getStringExtra("route")

        if (route == "newTicket") {
            Snackbar.make(
                findViewById(android.R.id.content),
                getString(R.string.ticket_reserved),
                Snackbar.LENGTH_LONG
            )
                .show()
            setupViews(true)
        }

        setupViews(false)
    }

    private fun setupViews(isNew: Boolean) {
        tabLayout = findViewById(R.id.add_tab)
        viewPager = findViewById(R.id.add_viewpager)

        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter

        if (isNew) viewPager.currentItem = R.string.my_tickets

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }


    class TabViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        val tabs = arrayOf(R.string.movies, R.string.coming_soon, R.string.my_tickets)
        private val fragments = arrayOf(Movies(), MoviesComingSoon(), MyTickets())

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
}