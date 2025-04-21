package com.mamadiyorov_lazizbek.game2048

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mamadiyorov_lazizbek.game2048.dialogs.GameOverDialog
import com.mamadiyorov_lazizbek.game2048.dialogs.RestartGameDialog
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants
import com.mamadiyorov_lazizbek.game2048.utils.setBackgroundMatrixElements
@SuppressLint("SetTextI18n")
class GameScreen: AppCompatActivity() {
    private var repository = Repository()

    private lateinit var rootGameScreen: ConstraintLayout
    private lateinit var containerGameView: FrameLayout

    private lateinit var containerLevel3: LinearLayoutCompat
    private lateinit var containerLevel4: LinearLayoutCompat
    private lateinit var containerLevel5: LinearLayoutCompat


    private lateinit var views: ArrayList<AppCompatTextView>
    private lateinit var score: TextView
    private lateinit var record: TextView

    private lateinit var menuBtn: TextView
    private lateinit var restartBtn: TextView
    private lateinit var gameOverDialog: GameOverDialog
    private lateinit var restartDialog: RestartGameDialog


    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game4)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_game)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        gameOverDialog = GameOverDialog(this){
            repository.setCount(0)
            repository.setScore(0)

            showMatrix()
            showScore()
        }
        restartDialog = RestartGameDialog(this)


        loadViews()
        showMatrix()
        showScore()
        showRecord()

        val myTouchListener = MyTouchListener(this)
        myTouchListener.setDetectMotionDirectionListener { sideEnum ->
            repository.setCount(repository.getCount() + 1)
            if (repository.checkGameOver(repository.getLevel())) {
                gameOverDialog.show()
              //  Toast.makeText(this, "Tabriklaymiz siz yutqazdingiz 😉😉😉", Toast.LENGTH_SHORT).show()
                return@setDetectMotionDirectionListener
            }
            when(sideEnum) {
                SideEnum.UP -> { repository.moveTopDirection() }
                SideEnum.DOWN -> { repository.moveBottomDirection() }
                SideEnum.LEFT -> { repository.moveLeftDirection() }
                SideEnum.RIGHT -> { repository.moveRightDirection() }
            }

            // Matritsani yangilash
            showMatrix()
            showScore()
            showRecord()

            // Harakatdan keyin yana yutqazganlik holatini tekshirish

        }
        rootGameScreen.setOnTouchListener(myTouchListener)

        menuBtn.setOnClickListener{
            val intent =  Intent(this, MenuScreen::class.java)
            startActivity(intent)
        }
        restartBtn.setOnClickListener {

            restartDialog.setRestartBtnClicked {
                repository.setCount(0)
                repository.setScore(0)
                showMatrix()
                showScore()
                restartDialog.dismiss()
            }
            restartDialog.setCanselBtnClicked {
                restartDialog.dismiss()
            }
            restartDialog.show()

        }
        record.setOnLongClickListener {


            gameOverDialog.show()


            return@setOnLongClickListener false
        }

    }

    private fun loadViews(){

        rootGameScreen = findViewById(R.id.root_game)
        containerGameView = findViewById(R.id.gameView)

        containerLevel3 = containerGameView.getChildAt(0) as LinearLayoutCompat
        containerLevel4 = containerGameView.getChildAt(1) as LinearLayoutCompat
        containerLevel5 = containerGameView.getChildAt(2) as LinearLayoutCompat

        record = findViewById(R.id.tv_record)
        score = findViewById(R.id.tv_score)

        menuBtn = findViewById(R.id.menu_btn)
        restartBtn = findViewById(R.id.restart_btn)

        when(repository.getLevel()){
            3 -> {
                views = ArrayList(9)
                containerLevel4.visibility = View.GONE
                containerLevel5.visibility = View.GONE
                for(i in 0 until containerLevel3.childCount){
                    val container  = containerLevel3.getChildAt(i) as LinearLayoutCompat
                    for(j in 0 until container.childCount){
                        views.add(container.getChildAt(j) as AppCompatTextView)
                    }
                }

            }
            4 -> {
                views = ArrayList(16)
                containerLevel3.visibility = View.GONE
                containerLevel5.visibility = View.GONE
                for(i in 0 until containerLevel4.childCount){
                    val container  = containerLevel4.getChildAt(i) as LinearLayoutCompat
                    for(j in 0 until container.childCount){
                        views.add(container.getChildAt(j) as AppCompatTextView)
                    }
                }
            }
            5 -> {
                views = ArrayList(25)
                containerLevel4.visibility = View.GONE
                containerLevel3.visibility = View.GONE
                for(i in 0 until containerLevel5.childCount){
                    val container  = containerLevel5.getChildAt(i) as LinearLayoutCompat
                    for(j in 0 until container.childCount){
                        views.add(container.getChildAt(j) as AppCompatTextView)
                    }
                }
            }
            else -> {
                views = ArrayList(repository.getLevel() * repository.getLevel())
                containerLevel4.visibility = View.GONE
                containerLevel5.visibility = View.GONE
                for(i in 0 until containerLevel3.childCount){
                    val container  = containerLevel3.getChildAt(i) as LinearLayoutCompat
                    for(j in 0 until container.childCount){
                        views.add(container.getChildAt(j) as AppCompatTextView)
                    }
                }
            }
        }


    }

    private fun showMatrix(){
        val matrixSize = when(repository.getLevel()){
            3 -> 3
            4 -> 4
            5 -> 5
            else -> 3
        }
        val matrix = repository.getMatrix()
        for(i in 0 until matrixSize){
            for (j in 0 until matrixSize){
                views[matrixSize * i + j].text = if(matrix[i][j] != 0) matrix[i][j].toString() else ""
                views[matrixSize * i + j].setBackgroundResource(setBackgroundMatrixElements(matrix[i][j]))
            }
        }
    }

    private fun showScore(){
        score.text = "${repository.getScore()}"
    }

    private fun showRecord(){
        record.text = "${repository.getRecord()}"
    }


    override fun onPause() {
        super.onPause()
        Log.d("GAMELLL","onPause() buldi: ")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent =  Intent(this, MenuScreen::class.java)
        startActivity(intent)
    }


}