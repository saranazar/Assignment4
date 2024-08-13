package com.example.assignment3

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class VangoghFrag : Fragment(R.layout.fragment_vangogh) {
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private val notificationID1: Int = 123
    private val channelID = "App_Channel.testNotification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create Notification Channel
        createNotificationChannel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vangogh, container, false)

        // Find the button within the inflated view
        val downloadvangogh: Button = view.findViewById(R.id.btnvangogh)

        // Set the click listener on the button
        downloadvangogh.setOnClickListener {
            basicNotification(requireContext())
        }

        return view
    }

    private fun basicNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Downloaded Paintings by Vincent Vangogh")
            .setContentText("'Paintings by Vincent Vangogh' has been downloaded successfully")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Request permission if not granted
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
                return
            }
            notify(notificationID1, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Download Notification"
            val descriptionText = "Paintings By Claude Monet has been downloaded"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, name, importance).apply {
                description = descriptionText
            }
            val notifManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(channel)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VangoghFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
