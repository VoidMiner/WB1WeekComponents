package com.ands.wb1weekcomponents.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb1weekcomponents.broadcast.BroadcastReceiverActivity
import com.ands.wb1weekcomponents.contentprovider.ContentProviderActivity
import com.ands.wb1weekcomponents.databinding.ActivityMainBinding
import com.ands.wb1weekcomponents.service.ServiceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding //поздняя инициализации binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.apply {
            openBroadcast.setOnClickListener() {
                makeIntent(BroadcastReceiverActivity::class.java)
            }
            openContentProvider.setOnClickListener() {
                makeIntent(ContentProviderActivity::class.java)
            }
            openPlayerService.setOnClickListener(){
                makeIntent(ServiceActivity::class.java)
            }
        }

    }

    private fun makeIntent(anyClass: Class<*>) {
        val i = Intent(this, anyClass)
        startActivity(i)
    }

}