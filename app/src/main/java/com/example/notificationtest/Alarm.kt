package com.example.maindisp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationtest.MyReceiver
import com.example.notificationtest.R
import java.util.*

class Alarm : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Notifi()
        //setAlarmManager()
        finish()
    }

    fun setAlarmManager(){
        val calendar= Calendar.getInstance()
        calendar.timeInMillis=System.currentTimeMillis()
        calendar.add(Calendar.SECOND,5)
        val am=getSystemService(Context.ALARM_SERVICE)as AlarmManager
        val intent= Intent(this, MyReceiver::class.java)
        val pending= PendingIntent.getBroadcast(this,0,intent,0)
        when{
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP->{
                val info= AlarmManager.AlarmClockInfo(calendar.timeInMillis,null)
                am.setAlarmClock(info,pending)
            }
            Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT->{
                am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
            else->{
                am.set(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pending)
            }
        }
    }



    fun Notifi(){
        val CHANNEL_ID = "channel_id"
        val channel_name = "channel_name"
        val channel_description = "channel_description "

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = channel_name
            val descriptionText = channel_description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            /// チャネルを登録
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        /// 通知の中身
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)    /// 表示されるアイコン
            .setContentTitle("ハローkotlin!!")                  /// 通知タイトル
            .setContentText("今日も1日がんばるぞい!")           /// 通知コンテンツ
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)   /// 通知の優先度



        var notificationId = 0   /// notificationID
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }


}