package com.example.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun tap_btnNotifi(view : View?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val name = "通知タイトル"
        val id = "Notification_001"
        val notifyDescription = "通知の詳細情報"

        if (notificationManager.getNotificationChannel(id) == null) {
            val mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            mChannel.apply {
                description = notifyDescription
            }
            notificationManager.createNotificationChannel(mChannel)
        }

        val notification = NotificationCompat.Builder(this, id).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentTitle("通知のタイトル")
            setContentText("通知の本文")
        }.build()
        notificationManager.notify(1, notification)
    }
}

//サイト　-> https://qiita.com/naoi/items/367fc23e55292c50d459