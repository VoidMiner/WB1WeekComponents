package com.ands.wb1weekcomponents.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.ands.wb1weekcomponents.Constants
import com.ands.wb1weekcomponents.R


//инициализация сервиса фонового проигрывателя музыки,пример любой медиа плеер,
class PlayerService : Service() {

    private lateinit var musicPlayer: MediaPlayer

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        initMusicPlayer()
        createNotificationChannel()
    }

    private fun initMusicPlayer() {
        musicPlayer = MediaPlayer.create(this, R.raw.music1)
        musicPlayer.setVolume(100F, 100F)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        musicPlayer.start()
        showNotification()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.stop()
    }

    private fun showNotification() {
        val notificationIntent = Intent(this, ServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)


        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification
                .Builder(this, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Музыкальный плеер")
                .setContentIntent(pendingIntent)
                .setStyle(Notification.MediaStyle())
                .build()
        } else {
            Notification
                .Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("Музыкальный плеер")
                .setContentIntent(pendingIntent)
                .build()
        }

        startForeground(Constants.NOTIFICATION_ID, notification)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            Constants.CHANNEL_ID,"My service channel", NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

}