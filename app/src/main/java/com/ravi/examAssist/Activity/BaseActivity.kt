package com.ravi.examAssist.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.ravi.examAssist.MainActivity
import com.ravi.examAssist.R
import com.ravi.examAssist.ui.dashboard.DashboardFragment
import com.ravi.examAssist.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.base_layout.*


open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        init()
    }

    private fun init() {
        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        val header = nav_view.getHeaderView(0)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.isChecked) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return true
        }
        when (item.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            else -> drawer_layout.closeDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}