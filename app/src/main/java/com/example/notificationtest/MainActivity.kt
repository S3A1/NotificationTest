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
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun tap_btnNotifi(view : View?) {
        StartNotification("通知タイトル","通知本文")
    }



    fun RepeatAlarm(){

    }











    @RequiresApi(Build.VERSION_CODES.O)
    fun StartNotification(strTitle:String,strText:String){
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
            setContentTitle(strTitle)
            setContentText(strText)
        }.build()
        notificationManager.notify(1, notification)
    }

    fun onNotification(strTitle:String,strText:String){
        val channelId = "CHANNEL_ID"
        val builder = NotificationCompat.Builder(this, channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle(strTitle)
            setContentText(strText)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }

        // API 26 以上の場合は NotificationChannel に登録する
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CHANNEL_NAME"
            val description = "SAMPLE"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                this.description = description
            }

            // システムにチャンネルを登録する
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            notify(1234567, builder.build())
        }
    }

}

//サイト　-> https://qiita.com/naoi/items/367fc23e55292c50d459
//https://oldbigbuddha.dev/post/android-notification-tutorial/
//https://developer.android.com/training/scheduling/alarms?hl=ja
//https://tanalab.com/2017/10/28/%E3%82%BF%E3%82%A4%E3%83%9E%E3%83%BC%E3%82%A2%E3%83%97%E3%83%AA%E3%82%92kotlin%E3%81%A7/