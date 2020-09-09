package com.egemeninceler.kutuphanem.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.egemeninceler.kutuphanem.R
import com.egemeninceler.kutuphanem.util.extStartActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                (this@SplashActivity).extStartActivity(MainActivity::class.java)
                finish()
            }

            override fun onTick(p0: Long) {
                Log.e("error", "ticking")
            }

        }.start()

    }
}