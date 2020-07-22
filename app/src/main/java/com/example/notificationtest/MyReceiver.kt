package com.example.notificationtest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val intent=Intent(context, Alarm::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
