package com.ands.wb1weekcomponents.service

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb1weekcomponents.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding // поздняя инициализация

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.launchPlayer.setOnClickListener() {
            startStopService() // запуск плеера на клик
        }

    }

    private fun startStopService() {
        if (isPlayerRunning(PlayerService::class.java)) {
            stopService(Intent(this, PlayerService::class.java))
            Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()
        } else {
            startService(Intent(this, PlayerService::class.java))
            Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()
        }
    }

    fun isPlayerRunning(mClass: Class<PlayerService>): Boolean {//сервис обработчик

        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service: ActivityManager.RunningServiceInfo in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (mClass.name.equals(service.service.className)) {
                return true
            }
        }

        return false
    }


}