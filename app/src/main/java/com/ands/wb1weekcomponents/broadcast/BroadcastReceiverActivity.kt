package com.ands.wb1weekcomponents.broadcast

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb1weekcomponents.databinding.ActivityBroadcastReceiverBinding

class BroadcastReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastReceiverBinding
    private val powerReceiver = ChargingBroadcastReceiver()
    // в Extend Controls выставить зарядку AC,none, AC ик эмулятор лагает
    // при включении можно услышать звук подключения зарядки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastReceiverBinding.inflate(layoutInflater)
            .also { setContentView(it.root) }

        val powerIntentFilter = IntentFilter()
        powerIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        powerIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(powerReceiver, powerIntentFilter)

        binding.checkPowerState.setOnClickListener(){
            checkPowerState() //биндинг клик на кнопку проверки подключения провода зарядки
        }


    }

    @SuppressLint("SetTextI18n")
    fun checkPowerState() {

        val connectivity = powerReceiver.getPowerState()

        when (connectivity) {
            true -> binding.textPowerState.text = "Connected"
            false -> binding.textPowerState.text = "Disconnected"
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(powerReceiver)
    }

}