package com.example.myruns2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.myruns2.fragments.HistoryFragment
import com.example.myruns2.fragments.SettingsFragment
import com.example.myruns2.fragments.StartFragment
import com.example.myruns2.fragments.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
//import kotlinx.android.synthetic.main.fragment_settings.*
//import com.example.fhghf.R;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabSet()
    }

    private fun tabSet(){
        val adapter =  ViewPagerAdapter(supportFragmentManager)
        adapter.addfargment(StartFragment(), "Start")
        adapter.addfargment(HistoryFragment(), "History")
        adapter.addfargment(SettingsFragment(), "Settings")
         val viewPager = findViewById<ViewPager>(R.id.ViewPagerXML)
        viewPager.adapter= adapter
        val tabLay = findViewById<TabLayout>(R.id.tabLayout)
        tabLay.setupWithViewPager(viewPager)
    }




}