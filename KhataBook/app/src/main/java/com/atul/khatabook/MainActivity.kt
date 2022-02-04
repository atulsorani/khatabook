package com.atul.khatabook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.atul.khatabook.fragments.HomeFragment
import com.atul.khatabook.fragments.MoneyFragment
import com.atul.khatabook.fragments.MoreFragment

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    public val homeFragment = HomeFragment()
    private val moneyFragment = MoneyFragment()
    private val moreFragment = MoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaseFragment(homeFragment)

        val buttomNavigation =
            findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        buttomNavigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.home -> replaseFragment(homeFragment)
                R.id.money -> replaseFragment(moneyFragment)
                R.id.more -> replaseFragment(moreFragment)
            }
            true
        }

    }

    private fun replaseFragment(fragment: Fragment) {

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fregmentContainer, fragment)
            transaction.commit()
        }

    }
}