package com.mamadiyorov_lazizbek.game2048

import android.content.SharedPreferences
import android.util.Log
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.COUNT_GAME
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.COUNT_GAME_3
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.COUNT_GAME_4
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.COUNT_GAME_5
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.GAME_ELEMENTS
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.LEVEL_SIZE
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.RECORD_GAME
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.RECORD_GAME_3
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.RECORD_GAME_4
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.RECORD_GAME_5
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.SCORE_GAME
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.SCORE_GAME_3
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.SCORE_GAME_4
import com.mamadiyorov_lazizbek.game2048.utils.MyConstants.SCORE_GAME_5
import kotlin.random.Random

class Repository {
    private val indexSize = getLevel()
    private var  addElement = 2
    private var score = getScore()
    private var record = getRecord()
    private var matrix: Array<Array<Int>>

    private var  matrix4 = arrayOf(
        arrayOf(0, 1024, 0, 0),
        arrayOf(0, 512, 0, 0),
        arrayOf(0, 0, 1024, 0),
        arrayOf(0, 64, 0, 0)
    )
    private var  matrix3 = arrayOf(
        arrayOf(0, 1024, 0),
        arrayOf(512, 0, 64),
        arrayOf(8, 0, 1024)
    )
    private var  matrix5 = arrayOf(
        arrayOf(1024, 0, 0, 0, 0),
        arrayOf(0, 0, 0, 512, 0),
        arrayOf(0, 0, 1024, 0, 0),
        arrayOf(0, 64, 0, 0, 0),
        arrayOf(0, 0, 0, 4, 0)
    )
    init {
        matrix = when(indexSize){
            3 -> matrix3
            4 -> matrix4
            5 -> matrix5
            else -> {matrix3}
        }

        if(getCount() == 0) {
            addNewElement(indexSize)
            addNewElement(indexSize)
        }else {
            getMatrix()
        }

    }




    fun getMatrix(): Array<Array<Int>>  = getGameElements()

    private fun addNewElement(index: Int){
    val emptyList = ArrayList<Int>()
        for(i in 0 until index){
            for(j in 0 until index){
                if(matrix[i][j] == 0) emptyList.add(i * index + j)
            }
        }

        if(emptyList.isEmpty()) return

        val randomIndex = Random.nextInt(0, emptyList.size)
        matrix[emptyList[randomIndex] / index][emptyList[randomIndex] % index] = addElement
        setGameElements(matrix, indexSize)
    }













    fun moveRightDirection() {
        val newMatrix = createEmptyMatrix(indexSize)
        val ls = ArrayList<Int>()
        var isAdded = false
        for (i in 0 until indexSize) {
            isAdded = false
            ls.clear()
            for (j in indexSize - 1  downTo  0) {
                if (matrix[i][j] == 0) continue
                if (ls.isEmpty()) {
                    ls.add(matrix[i][j])
                    continue
                }
                if (ls.last() == matrix[i][j] && !isAdded) {
                    score += 2* ls.last()
                    if(score > record) record = score
                    setScore(score)
                    setRecord(record)
                    ls[ls.size-1] = 2* ls.last()

                    isAdded = true
                } else {
                    ls.add(matrix[i][j])
                    isAdded = false
                }
            }
            for (k in ls.size - 1 downTo  0)
                newMatrix[i][indexSize - 1 - k] = ls[k]
        }
        matrix = newMatrix
        addNewElement(indexSize)
    }

    fun moveLeftDirection() {
        val newMatrix = createEmptyMatrix(indexSize)
        val ls = ArrayList<Int>()
        var isAdded = false
        for (i in 0 until indexSize) {
            isAdded = false
            ls.clear()
            for (j in 0 until indexSize) {
                if (matrix[i][j] == 0) continue
                if (ls.isEmpty()) {
                    ls.add(matrix[i][j])
                    continue
                }
                if (ls.last() == matrix[i][j] && !isAdded) {
                    score += 2* ls.last()
                    if(score > record) record = score
                    setScore(score)
                    setRecord(record)
                    ls[ls.size-1] = 2* ls.last()
                    isAdded = true

                } else {
                    ls.add(matrix[i][j])
                    isAdded = false
                }
            }
            for (k in 0 until ls.size)
                newMatrix[i][k] = ls[k]
        }
        matrix = newMatrix
        addNewElement(indexSize)
    }

    fun moveTopDirection() {
        val newMatrix = createEmptyMatrix(indexSize)
        val ls = ArrayList<Int>()
        var isAdded = false

        for (j in 0 until indexSize) {
            isAdded = false
            ls.clear()
            for (i in 0 until indexSize) {
                if (matrix[i][j] == 0) continue
                if (ls.isEmpty()) {
                    ls.add(matrix[i][j])
                    continue
                }
                if (ls.last() == matrix[i][j] && !isAdded) {
                    score += 2* ls.last()
                    if(score > record) record = score
                    setScore(score)
                    setRecord(record)
                    ls[ls.size - 1] = 2 * ls.last()
                    isAdded = true
                } else {
                    ls.add(matrix[i][j])
                    isAdded = false
                }
            }
            for (k in ls.indices) {
                newMatrix[k][j] = ls[k]
            }
        }
        matrix = newMatrix
        addNewElement(indexSize)
    }

    fun moveBottomDirection() {
        val newMatrix = createEmptyMatrix(indexSize)
        val ls = ArrayList<Int>()
        var isAdded = false
        for (j in 0 until indexSize) {
            isAdded = false
            ls.clear()
            for (i in indexSize - 1 downTo 0) {
                if (matrix[i][j] == 0) continue
                if (ls.isEmpty()) {
                    ls.add(matrix[i][j])
                    continue
                }
                if (ls.last() == matrix[i][j] && !isAdded) {
                    score += 2* ls.last()
                    if(score > record) record = score
                    setScore(score)
                    setRecord(record)
                    ls[ls.size - 1] = 2 * ls.last()
                    isAdded = true
                } else {
                    ls.add(matrix[i][j])
                    isAdded = false
                }
            }
            for (k in ls.indices) {
                newMatrix[indexSize - 1 - k][j] = ls[k]
            }
        }
        matrix = newMatrix
        addNewElement(indexSize)
    }









    fun createEmptyMatrix(index: Int) : Array<Array<Int>> = when(index){
            3 -> {
                arrayOf(
                    arrayOf(0, 0, 0),
                    arrayOf(0, 0, 0),
                    arrayOf(0, 0, 0),
                )
            }
            4 -> {
                 arrayOf(
                     arrayOf(0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0)
                 )
            }
            5 -> {
                 arrayOf(
                     arrayOf(0, 0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0, 0),
                     arrayOf(0, 0, 0, 0, 0)
                 )
            }
            else -> {
                arrayOf(
                    arrayOf(0, 0, 0),
                    arrayOf(0, 0, 0),
                    arrayOf(0, 0, 0),
                )
            }
        }
    fun checkGameOver(index: Int): Boolean {
        // 1. Bo'sh kataklar borligini tekshirish
        for (i in 0 until index) {
            for (j in 0 until index) {
                if (matrix[i][j] == 0) return false
            }
        }

        // 2. Qo‘shilishi mumkin bo'lgan elementlarni tekshirish
        // Gorizontal tekshirish
        for (i in 0 until index) {
            for (j in 0 until index - 1) {
                if (matrix[i][j] == matrix[i][j + 1]) return false
            }
        }

        // Vertikal tekshirish
        for (j in 0 until index) {
            for (i in 0 until index - 1) {
                if (matrix[i][j] == matrix[i + 1][j]) return false
            }
        }



        return true
    }

    companion object{
        private lateinit var sharedPref: SharedPreferences
        fun  setShared(sharedPreferences: SharedPreferences){
            this.sharedPref = sharedPreferences
        }
    }
    private fun getShared() = sharedPref

    private fun getGameElements(): Array<Array<Int>> {
        if(getCount() == 0){
            this.matrix = createEmptyMatrix(indexSize)
            addNewElement(indexSize)
            addNewElement(indexSize)
        }

        val stringBuilder = getShared().getString("GAME_ELEMENTS_$indexSize", "")


        if (stringBuilder.isNullOrEmpty()) return matrix // Bo'sh yoki null bo'lsa, matrixni qaytaradi
        Log.d("GAMELLL","getGameElements(): ${stringBuilder}")



        val strArr = stringBuilder.split("#").filter { it.isNotEmpty() } // Faqat bo'sh bo'lmagan elementlar
        val maxElements = matrix.size * matrix[0].size // matrix o'lchami

        for (i in 0 until minOf(strArr.size, maxElements)) { // maxElements bilan cheklash
            val chars = strArr[i].toCharArray()
            var sum = 0
            for (element in chars){
                sum = sum * 10 +  (element - '0')
            }
            matrix[i / indexSize][i % indexSize] = sum // Birinchi belgini raqamga o'tkazish
        }

        return matrix // Oxirida matrixni qaytarish
    }

    fun setGameElements(matrix: Array<Array<Int>>, size: Int){
        val stringBuilder = StringBuilder()
        for(i in 0 until size){
            for(j in 0 until size){
                stringBuilder.append(matrix[i][j].toString()).append("#")
            }
        }
        Log.d("GAMELLL", "setGameElements_$size: {${stringBuilder.toString()}}")

        getShared().edit().putString("GAME_ELEMENTS_$size",stringBuilder.toString()).apply()
    }


    // ----------------------------------------------------------------------


    fun getCount() = sharedPref.getInt("COUNT_GAME_$indexSize", 0)
    fun setCount(count: Int){
        getShared().edit().putInt("COUNT_GAME_$indexSize",count).apply()
    }

    fun getScore() : Int{
        Log.d("ASDASD","getScore() =:  ${indexSize}")
        if(getCount() == 0) {
            score = 0
            setScore(0)
            return 0
        }
       return sharedPref.getInt("SCORE_GAME_$indexSize",0)
    }
    fun setScore(score: Int){
        sharedPref.edit().putInt("SCORE_GAME_$indexSize",score).apply()
        Log.d("ASDASD","setScore() =:  ${indexSize}")
    }

    fun getRecord() = sharedPref.getInt("RECORD_GAME_$indexSize",0)
    fun setRecord(newRecord: Int){
        sharedPref.edit().putInt("RECORD_GAME_$indexSize", newRecord).apply()
    }

    fun getRecordLevel3() = sharedPref.getInt("RECORD_GAME_${3}",0)
    fun getRecordLevel4() = sharedPref.getInt("RECORD_GAME_${4}",0)
    fun getRecordLevel5() = sharedPref.getInt("RECORD_GAME_${5}",0)



    fun getLevel() = sharedPref.getInt(LEVEL_SIZE,3)
    fun setLevel(index: Int){
        sharedPref.edit().putInt(LEVEL_SIZE,index).apply()
    }


}