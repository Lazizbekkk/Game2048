package com.mamadiyorov_lazizbek.game2048.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.mamadiyorov_lazizbek.game2048.databinding.DialogGameOverBinding

class GameOverDialog(context: Context, private val onDismissAction: () -> Unit) : Dialog(context){
    private lateinit var binding: DialogGameOverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        binding.btnRestart.setOnClickListener {
            dismiss()
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
    override fun dismiss() {
        super.dismiss()
        onDismissAction() // Dialog yopilgandan keyin matritsani yangilash
    }

}