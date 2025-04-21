package com.mamadiyorov_lazizbek.game2048.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.game2048.databinding.DialogRestart2Binding

class RestartGameDialog(context: Context): Dialog(context) {

    private  var canselBtn : (() -> Unit) ?= null
    private  var restartBtn : (() -> Unit) ?= null

    private lateinit var binding: DialogRestart2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogRestart2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.canselBtn.setOnClickListener {
            canselBtn?.invoke()
        }
        binding.restartBtn.setOnClickListener {
            restartBtn?.invoke()
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setCanselBtnClicked(l : (() -> Unit)){
        canselBtn = l
    }
    fun setRestartBtnClicked(l: (() -> Unit)){
        restartBtn = l
    }
}