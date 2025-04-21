package com.mamadiyorov_lazizbek.game2048

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants

class MenuScreen: AppCompatActivity() {
    private lateinit var continueBtn: CardView
    private lateinit var playBtn: CardView
    private lateinit var about: CardView

    private lateinit var level3: CardView
    private lateinit var level4: CardView
    private lateinit var level5: CardView

    private lateinit var records: CardView


    private lateinit var imgLevelGame: ImageView
    private lateinit var levelGame: AppCompatButton

    private val repository = Repository()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.axtivity_menu)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_menu_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        loadViews()

        if(repository.getCount() == 0){
            continueBtn.visibility = View.GONE
        }
        else{
            continueBtn.visibility = View.VISIBLE
        }
        when(repository.getLevel()){
            3 -> {
                imgLevelGame.setImageResource(R.drawable.ic_img_level3)
                levelGame.text = "3 x 3"
                level3.visibility = View.GONE
                level4.visibility = View.VISIBLE
                level5.visibility = View.VISIBLE
            }
            4 -> {
                imgLevelGame.setImageResource(R.drawable.ic_2k48_4_4)
                levelGame.text = "4 x 4"
                level4.visibility = View.GONE
                level3.visibility = View.VISIBLE
                level5.visibility = View.VISIBLE
            }
            5 -> {
                imgLevelGame.setImageResource(R.drawable.ic_img_level5)
                levelGame.text = "5 x 5"
                level5.visibility = View.GONE
                level4.visibility = View.VISIBLE
                level3.visibility = View.VISIBLE
            }
        }

        playBtn.setOnClickListener {
            repository.setCount(0)
            repository.setScore(0)
            val intent =  Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
        about.setOnClickListener {
            val intent =  Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        continueBtn.setOnClickListener {
            val intent =  Intent(this, GameScreen::class.java)
            startActivity(intent)
        }
        level3.setOnClickListener {
            repository.setLevel(3)
            nextScreenGame(LevelEnum.LEVEL3)
        }
        level4.setOnClickListener {
            repository.setLevel(4)
            nextScreenGame(LevelEnum.LEVEL4)
        }
        level5.setOnClickListener {
            repository.setLevel(5)
            nextScreenGame(LevelEnum.LEVEL5)
        }

        records.setOnClickListener {
            startActivity(Intent(this,RecordActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    private fun loadViews(){
       continueBtn = findViewById(R.id.continueBtn)
       playBtn = findViewById(R.id.playBtn)
       about = findViewById(R.id.aboutBtn)
       level3 = findViewById(R.id.level3)
       level4 = findViewById(R.id.level4)
       level5 = findViewById(R.id.level5)
       records = findViewById(R.id.recordsBtn)

       imgLevelGame = findViewById(R.id.img_level_game)
       levelGame = findViewById(R.id.level_game)
    }
    private fun nextScreenGame(levelEnum: LevelEnum){
        val intent =  Intent(this, GameScreen::class.java)
        intent.putExtra(MyConstants.LEVEL,levelEnum)
        startActivity(intent)
    }
}