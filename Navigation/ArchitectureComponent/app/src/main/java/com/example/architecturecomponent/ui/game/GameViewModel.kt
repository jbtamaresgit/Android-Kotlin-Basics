package com.example.architecturecomponent.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var score = 0
    private var currWordCount = 0
    private var _currScrambledWord = "test"
    val currentScrambledWord : String
        get() = _currScrambledWord

}