package com.mamadiyorov_lazizbek.game2048

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splsh)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val lottieAnimationView1 =  findViewById<LottieAnimationView>(R.id.lottie_splash)


        lottieAnimationView1.repeatCount = LottieDrawable.INFINITE
        lottieAnimationView1.speed = 2.5f
        lottieAnimationView1.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MenuScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}