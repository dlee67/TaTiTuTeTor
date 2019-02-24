package com.less.TaTiTuTeTor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity

class TaskReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context!!.startActivity(Intent(context!!, Finisher::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}