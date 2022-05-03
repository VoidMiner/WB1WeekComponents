package com.ands.wb1weekcomponents.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi


class ChargingBroadcastReceiver() : BroadcastReceiver() {

    private var powerState: Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) { //переопределение метода в рисивер

//        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)) {
//            val musicService = Intent(context, PlayerService::class.java)
//            context?.startForegroundService(musicService)
//         }

        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                powerState = true
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                powerState = false
            }
        }

    }

    fun getPowerState(): Boolean = powerState


}