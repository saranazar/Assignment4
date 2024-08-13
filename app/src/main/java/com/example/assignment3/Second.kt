package com.example.assignment3

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Second : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        //fragments

            //declaring fragments
        val Monetfrag = MonetFrag()
        val GoghFrag = VangoghFrag()

            //declaring buttons
        val btnmonet: Button = findViewById(R.id.button)
        val btngogh: Button = findViewById(R.id.button2)

            //On click listener
        btnmonet.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfrag, Monetfrag, "FIRST_FRAGMENT")
                addToBackStack(null)
                commit()
            }
        }

        btngogh.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FLfrag, GoghFrag, "SECOND_FRAGMENT")
                addToBackStack(null)
                commit()
            }
        }











        //receiving & displaying data

        val tv: TextView = findViewById(R.id.texttv)

        val msg = intent.getStringExtra("key")
        tv.text = msg
    }

    //menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.opt_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                true
            }
            R.id.exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}