package com.mamadiyorov_lazizbek.game2048

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mamadiyorov_lazizbek.game2048.databinding.ActivityRecordsBinding

class RecordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRecordsBinding
    private val repository =  Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_record_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        binding.record33.text = repository.getRecordLevel3().toString()
        binding.record44.text = repository.getRecordLevel4().toString()
        binding.record55.text = repository.getRecordLevel5().toString()
        binding.backBtn.setOnClickListener {
            finish()
        }

    }

}